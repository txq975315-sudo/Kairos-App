/**
 * Builds merged res/values/strings.xml (ZH catalog + existing) and res/values-en/strings.xml (EN catalog + existing).
 * Source: translation_extract.txt + LocalizedStrings.kt (else branches).
 */
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const ROOT = path.join(__dirname, "..");
const EXTRACT = path.join(ROOT, "translation_extract.txt");
const LS = path.join(ROOT, "app/src/main/java/com/example/kairosapplication/i18n/LocalizedStrings.kt");
const VALUES_STR = path.join(ROOT, "app/src/main/res/values/strings.xml");
const OUT_ZH = path.join(ROOT, "app/src/main/res/values/strings.xml");
const OUT_EN_DIR = path.join(ROOT, "app/src/main/res/values-en");
const OUT_EN = path.join(OUT_EN_DIR, "strings.xml");

function sanitizeResName(id) {
  let n = id.toLowerCase().replace(/[^a-z0-9_]/g, "_").replace(/_+/g, "_");
  if (/^[0-9]/.test(n)) n = "x_" + n;
  if (n.length > 120) n = n.slice(0, 120);
  return n;
}

function unescapeKotlin(s) {
  return s.replace(/\\n/g, "\n").replace(/\\t/g, "\t").replace(/\\"/g, '"').replace(/\\\\/g, "\\");
}

function escapeXmlAttr(s) {
  return s
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/"/g, '\\"')
    .replace(/\r/g, "")
    .replace(/\n/g, "\\n");
}

/** Map {n} %d ${x} style to %1$d / %1$s in order of appearance (best-effort). */
function normalizePlaceholders(zh, en) {
  const apply = (s) => {
    if (!s) return s;
    let n = 0;
    const use = () => ++n;
    s = s.replace(/\{n\}/g, () => `%${use()}$d`);
    s = s.replace(/\{done\}/g, () => `%${use()}$d`);
    s = s.replace(/\{total\}/g, () => `%${use()}$d`);
    s = s.replace(/%d/g, () => `%${use()}$d`);
    s = s.replace(/\$\{monthValue\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{count\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{completed\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{total\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{prefix\}/g, () => `%${use()}$s`);
    s = s.replace(/\$lines/g, () => `%${use()}$s`);
    s = s.replace(/\$notes/g, () => `%${use()}$d`);
    s = s.replace(/\$tasks/g, () => `%${use()}$d`);
    s = s.replace(/\$moods/g, () => `%${use()}$d`);
    s = s.replace(/\$conflicts/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.PERFECT\)\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.EXCELLENT\)\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.CHEER\)\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.SEEDLING\)\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.WRITER\)\}/g, () => `%${use()}$d`);
    s = s.replace(/\$\{n\(PastCheckInDisplay\.EMPTY\)\}/g, () => `%${use()}$d`);
    return s;
  };
  return { zh: apply(zh), en: apply(en) };
}

function parseLocalizedStrings(raw) {
  const enMap = {};
  const re1 =
    /"([a-zA-Z0-9_-]+)"\s*->\s*if\s*\(\s*zh\s*\)\s*"((?:[^"\\]|\\.)*)"\s*else\s*"((?:[^"\\]|\\.)*)"/gs;
  const re2 =
    /"([a-zA-Z0-9_-]+)"\s*->\s*if\s*\(\s*zh\s*\)\s*\{\s*"((?:[^"\\]|\\.)*)"\s*\}\s*else\s*\{\s*"((?:[^"\\]|\\.)*)"\s*\}/gs;
  let m;
  while ((m = re1.exec(raw)) !== null) {
    enMap[m[1]] = unescapeKotlin(m[3]);
  }
  while ((m = re2.exec(raw)) !== null) {
    enMap[m[1]] = unescapeKotlin(m[3]);
  }
  return enMap;
}

/** Manual EN (and optional ZH fix) for extract IDs not in LocalizedStrings when() map */
const OVERRIDE_EN = {
  app_han_75286237663579f0_36501: "Nickname",
  app_han_7b804f534e2d6587_2401: "Simplified Chinese",
  app_han_81ea62115b9e73b0_36901: "Self-actualization",
  app_monthvalue_48001: "Mood overview · month %1$d",
  app_count_48601: "Notes (%1$d)",
  app_count_49201: "Tasks (%1$d)",
  app_count_49801: "Mood entries (%1$d)",
  app_notestasksmoodsconflicts_51001:
    "Import done: +%1$d notes, +%2$d tasks, +%3$d moods, %4$d conflicts",
  app_prefixlines_52201: "%1$s mood: %2$s",
  app_han_6bcf65e55f85529e_3501: "Daily to-do reminder",
  app_han_6bcf65e55f85529e_3801: "Daily to-do reminder",
  app_han_6bcf65e553cd601d_4301: "Daily reflection reminder",
  app_han_6bcf65e553cd601d_4601: "Daily reflection reminder",
  app_han_6bcf65e55f85529e_6401: "Daily to-do reminder",
  app_han_4eca59298fd86709_6501: "You still have tasks today — tap to view",
  app_han_6bcf65e553cd601d_8501: "Daily reflection reminder",
  app_han_4eca59298bb05f55_8601: "Journaled today? Tap to write a reflection",
  app_yyyymd_48201: "yyyy/M/d",
  app_yyyym_7801: "yyyy/M",
  app_yyyym_6201: "yyyy/M",
  app_yyyym_84001: "yyyy/M",
  app_yyyym_17601: "yyyy/M",
  app_yyyym_125601: "yyyy/M",
  app_md_8501: "MMM d",
  app_md_82101: "MMM d",
  app_yyyymd_3701: "yyyy/M/d",
  app_yyyymd_22601: "yyyy/M/d",
  app_yyyymd_82001: "yyyy/M/d",
  app_yyyymd_69701: "yyyy/M/d",
  app_npastcheckindisplayperfe_23301:
    "Perfect this month: %1$d days    Great: %2$d days\n",
  app_npastcheckindisplaycheer_23401:
    "Cheer: %1$d days        Seedling: %2$d days\n",
  app_npastcheckindisplaywrite_23501:
    "Writer: %1$d days  Blank: %2$d days",
  app_han_5f005fc3_701: "Happy",
  app_han_5f97610f_801: "Smug",
  app_han_601d8003_901: "Thoughtful",
  app_han_5e739759_1001: "Calm",
  app_han_56f060d1_1101: "Confused",
  app_han_96be8fc7_1201: "Sad",
  app_han_62c55fc3_1301: "Worried",
  app_han_96be53d7_1401: "Unwell",
  app_han_832b7136_1501: "Blank",
  app_han_66688dd1_20701: "Morning run",
  app_han_96058bfb_20801: "Reading",
  app_han_51994f5c_20901: "Writing",
  app_han_51a560f3_2101: "Meditation",
  app_han_957f8dd18bad7ec3_21301: "Long run training",
  app_60506_21901: "6 · Today · Wed · 05-06",
  app_3_22101: "3 more — open the app to view",
  app_completedtotal_52901: "Done %1$d / total %2$d",
  app_han_8ba165707c7b_1301: "Counter",
  app_todo_1401: "Todo",
  app_todo_1501: "Todo showcase",
  app_todo_1601: "Todo showcase",
  app_han_7edf8ba17c7b_1701: "Stats",
  app_han_4e0054684efb52a1_1801: "Week task strip",
  app_han_54684efb52a12b_2001: "Week tasks + quote",
  app_han_5c55793a4eca65e5_2101: "Today only",
  app_han_5c55793a516890e8_2201: "Show all",
  app_app_2601: "Update each time you open the app",
  app_han_6bcf5c0f65f666f4_2701: "Update every hour",
  app_han_6bcf592966f465b0_2801: "Update once a day",
  xml_ui_han_661f671f4e09_46: "Wednesday",
  xml_ui_38_57: "Done 3 / total 8",
  xml_ui_han_668265e04efb52a1_81: "No tasks yet",
  xml_ui_han_668265e04efb52a1_57: "No tasks yet",
  xml_ui_20265_112: "May 2026",
  xml_ui_60506_75: "6 · Today · Wed · 05-06",
  xml_ui_han_54684e8c_76: "Tuesday",
  xml_ui_35_141: "3-day streak · 5 days total",
  xml_ui_han_4e00_44: "Mon",
  xml_ui_han_4e8c_68: "Tue",
  xml_ui_han_4e09_92: "Wed",
  xml_ui_han_56db_116: "Thu",
  xml_ui_han_4e94_140: "Fri",
  xml_ui_han_516d_164: "Sat",
  xml_ui_han_65e5_188: "Sun",
  xml_ui_han_4e00_94: "Mon",
  xml_ui_han_4e8c_104: "Tue",
  xml_ui_han_4e09_114: "Wed",
  xml_ui_han_56db_124: "Thu",
  xml_ui_han_4e94_134: "Fri",
  xml_ui_han_516d_144: "Sat",
  xml_ui_han_65e5_154: "Sun",
  xml_ui_20265_54: "May 2026",
  xml_ui_han_4e00_95: "Mon",
  xml_ui_han_4e8c_105: "Tue",
  xml_ui_han_4e09_115: "Wed",
  xml_ui_han_56db_125: "Thu",
  xml_ui_han_4e94_135: "Fri",
  xml_ui_han_516d_145: "Sat",
  xml_ui_han_65e5_155: "Sun",
  xml_ui_han_4eca65e54e0053e5_179:
    "Today’s line: A bright heart, a steady stride",
  xml_ui_1_29: "Today’s task 1",
  taskmodel_han_75286237663579f0_401: "Nickname",
};

function parseExtract() {
  const text = fs.readFileSync(EXTRACT, "utf8");
  const rows = [];
  const seen = new Set();
  const fieldSep = " | ";
  for (const line of text.split(/\r?\n/)) {
    if (!line.trim() || line.startsWith("#")) continue;
    // id | zh | path — split on first and last " | " so zh keeps leading/trailing spaces
    const i0 = line.indexOf(fieldSep);
    if (i0 === -1) continue;
    const id = line.slice(0, i0).trim();
    const afterId = line.slice(i0 + fieldSep.length);
    const i1 = afterId.lastIndexOf(fieldSep);
    if (i1 === -1) continue;
    const zh = afterId.slice(0, i1);
    const name = sanitizeResName(id);
    if (seen.has(name)) continue;
    seen.add(name);
    rows.push({ id, zh, name });
  }
  return rows;
}

function resolveEnglish(id, name, zh, enMap) {
  if (OVERRIDE_EN[id] !== undefined) return OVERRIDE_EN[id];
  if (id.startsWith("i18n_")) {
    const key = id.slice("i18n_".length);
    if (enMap[key]) return enMap[key];
    const keyLower = key.toLowerCase();
    for (const [k, v] of Object.entries(enMap)) {
      if (k.toLowerCase() === keyLower) return v;
    }
  }
  if (enMap[name]) return enMap[name];
  return zh;
}

function main() {
  const lsRaw = fs.readFileSync(LS, "utf8");
  const enMap = parseLocalizedStrings(lsRaw);
  const rows = parseExtract();

  const catalogZh = [];
  const catalogEn = [];
  for (const { id, zh, name } of rows) {
    let en = resolveEnglish(id, name, zh, enMap);
    const norm = normalizePlaceholders(zh, en);
    catalogZh.push(`    <string name="${name}">${escapeXmlAttr(norm.zh)}</string>`);
    catalogEn.push(`    <string name="${name}">${escapeXmlAttr(norm.en)}</string>`);
  }

  const legacy = fs.readFileSync(VALUES_STR, "utf8");
  let legacyInner = legacy.replace(/<\?xml[^>]*>\s*/, "").trim();
  const catalogMarker = "<!-- translation_extract.txt catalog";
  const cut = legacyInner.indexOf(catalogMarker);
  if (cut !== -1) legacyInner = legacyInner.slice(0, cut).trimEnd();
  let legacyStrings = legacyInner.replace(/^<resources>\s*/, "").replace(/\s*<\/resources>\s*$/, "").trim();
  legacyStrings = legacyStrings.replace(/(<!-- Legacy \/ non-extract strings -->\s*)+/g, "");

  const header = `<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Legacy / non-extract strings -->
`;
  const mid = `
    <!-- translation_extract.txt catalog (Chinese default) -->
`;
  const footer = `
</resources>
`;

  const mergedZh =
    header +
    legacyStrings +
    "\n" +
    mid +
    catalogZh.join("\n") +
    footer;
  fs.writeFileSync(OUT_ZH, mergedZh, "utf8");

  if (!fs.existsSync(OUT_EN_DIR)) fs.mkdirSync(OUT_EN_DIR, { recursive: true });
  const mergedEn =
    `<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Same keys as values/strings.xml — English -->
` +
    legacyStrings +
    "\n" +
    catalogEn.join("\n") +
    "\n</resources>\n";
  fs.writeFileSync(OUT_EN, mergedEn, "utf8");

  console.log(`Wrote ${rows.length} catalog strings + legacy into:`);
  console.log(OUT_ZH);
  console.log(OUT_EN);
}

main();
