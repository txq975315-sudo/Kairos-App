# NoteCard 毛玻璃质感改造记录

## 背景

将 TaskItemCard（TodayScreen）的毛玻璃质感统一应用到所有 NoteCard 变体上，实现整个应用的视觉一致性。

## 改动的文件

### 1. NoteCardInbox.kt

**改动内容**：
- 从 `NoteCardFace`（alpha 0.56）实色半透明背景 → 4层毛玻璃叠加效果
- 替换阴影：0.5dp 黑色 → 3.5dp TaskCardShadowColor（蓝色调浮起感）
- 替换描边：1dp CardRimLight → 1.1dp TaskCardGlassHairline（白色半透明发丝）
- 容器色：NoteCardFace → Color.Transparent

### 2. NoteCardProject.kt

**改动内容**：（与 NoteCardInbox 相同）
- 4层毛玻璃叠加
- 阴影、描边、容器色对齐 TaskCard

### 3. NoteCardTimeline.kt

**改动内容**：
- 4层毛玻璃叠加（同 Inbox/Project）
- **删除左侧主题色竖线**：移除 `drawBehind` 绘制的 4dp 颜色条
- 修复左侧 padding：`4.dp + AppSpacing.CardHorizontal` → `AppSpacing.CardHorizontal`
- 移除不再需要的 import：`drawBehind`, `Offset`, `Size`
- 删除 `cardBackgroundOverride` 参数的引用（保留参数签名避免编译错误）

### 4. NoteCardTopic.kt

**状态**：已在此次改造前就使用了完整的 TaskCard 毛玻璃效果，无需改动。

## 毛玻璃结构（4层叠加）

每一层均在 `Box` 容器内，通过 `matchParentSize()` 铺满卡片：

```
Layer 1: 玻璃渐变底座
  - Brush.verticalGradient (TaskCardGlassTop → GlassMid → GlassBottom)
  - 白色透明度仅 5~8%，大幅降低白色底纹

Layer 2: 灰雾叠加（模拟模糊）
  - blur(100.dp) + TaskCardGrayMist (alpha 0.05)
  - 模拟磨砂玻璃的朦胧感

Layer 3: 内发光
  - blur(100.dp) + Brush.verticalGradient (内发光渐变)
  - 上下白色发光，中间透明，增加玻璃立体感

Layer 4: 面纱光晕
  - GlassFill.copy(alpha = 0.07)
  - 极淡的面纱层，增加玻璃通透感和深度
```

## 统一后的视觉效果

| 属性 | 改造前 | 改造后 |
|------|--------|--------|
| 容器色 | `NoteCardFace` (alpha 0.56) | `Color.Transparent` + 4层玻璃 |
| 白色透明度 | 56%（整体面板） | 5~8%（单层）+ 叠加效果 |
| 毛玻璃 | 无 | 有（blur 100dp 灰雾） |
| 发光质感 | 无 | 有（blur + 内发光渐变） |
| 描边 | `CardRimLight` 1dp | `TaskCardGlassHairline` 1.1dp |
| 阴影 | 0.5dp 黑色 | 3.5dp 蓝色调 |
| Timeline 左侧色条 | 4dp `drawBehind` 颜色条 | 已删除 |

## Commit 信息

```
feat(ui): 统一所有NoteCard变体为TaskCard毛玻璃质感，删除Timeline左侧色条
```

涉及 3 个文件，190 行新增，43 行删除。

## 后续注意事项

1. **Timeline 的 `cardBackgroundOverride` 参数**：虽然已被玻璃效果取代，但保留了参数签名，调用方需要确认是否传递该参数。待其他页面统一更新后可考虑删除。
2. **NoteCardTopic 的变量名**：`taskCardGlassBrush` / `taskCardInnerGlowBrush` 与其他三个变体命名不同，应考虑统一。
