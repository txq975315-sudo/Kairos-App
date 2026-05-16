# 原始版本UI托底方案

## 🎯 目标

找回2026年5月11日之前的原始UI版本（白色Material风格），作为"托底"备份。

---

## 📸 原始UI特征

根据您提供的截图，原始UI具有以下特征：

| 特征 | 描述 |
|------|------|
| **背景** | 纯白色，无图片背景 |
| **卡片** | 白色卡片 + 细灰色边框 |
| **时间标签** | 彩色胶囊标签（ANYTIME黄色、MORNING橙色、AFTERNOON粉色、EVENING紫色） |
| **底部导航** | 白色背景，灰色图标，选中项蓝色高亮 |
| **整体风格** | Material Design，清新明亮 |

---

## 🔑 关键Git提交

### 托底版本提交哈希
```
114ebb945c218301afa714e38cd097f1e5ebf026
```

**提交信息**：`feat(ui): 桌面小组件完成度与布局统一、超大周历格居中、底栏下移`

**提交时间**：2026-05-11 02:36:57

**这是您开始改UI之前的最后一个版本！**

---

## 🛠️ 操作步骤

### 步骤1：找到Git仓库位置

在您的电脑上找到Kairos项目的Git仓库位置，通常在：
- `D:\KairosApplication\.git`
- 或其他您存放项目的位置

### 步骤2：打开终端

在项目根目录打开Git Bash或PowerShell

### 步骤3：创建托底分支

```bash
# 确保您在正确的Git仓库目录
cd D:\KairosApplication

# 查看当前分支
git branch

# 创建托底分支（基于5月11日的提交）
git checkout -b backup/original-ui-2026-05-11 114ebb9

# 验证分支创建成功
git branch
```

### 步骤4：保存分支到远程（可选）

```bash
# 推送到远程仓库
git push origin backup/original-ui-2026-05-11
```

### 步骤5：回到主分支继续开发

```bash
# 切换回主分支
git checkout main

# 继续您的Glass UI开发
```

---

## 🔄 如何使用托底版本

### 场景1：想查看原始UI
```bash
git checkout backup/original-ui-2026-05-11
# 在Android Studio中查看/运行
```

### 场景2：想回到原始UI继续开发
```bash
# 方法A：直接切换到托底分支
git checkout backup/original-ui-2026-05-11

# 方法B：基于托底分支创建新分支
git checkout -b feature/new-feature-from-original backup/original-ui-2026-05-11
```

### 场景3：对比两个版本
```bash
# 查看主分支和托底分支的差异
git diff backup/original-ui-2026-05-11 main

# 查看特定文件的差异
git diff backup/original-ui-2026-05-11 main -- app/src/main/java/com/example/kairosapplication/ui/TaskItemCard.kt
```

---

## 📁 关键文件对比

| 文件 | 原始版本 (114ebb9) | 当前版本 |
|------|-------------------|---------|
| `TaskItemCard.kt` | 白色卡片，无模糊 | 玻璃效果，有模糊 |
| `StyleConstants.kt` | 无玻璃常量 | 有玻璃常量 |
| `MainScreen.kt` | 白色底部导航 | 玻璃底部导航 |
| `EssayMainScreen.kt` | 白色背景 | 睡莲背景 |

---

## 🎯 三套UI系统规划

基于您的需求，建议维护三套UI：

```
┌─────────────────────────────────────────────────────────┐
│  1. 原始UI (backup/original-ui-2026-05-11)               │
│     - 白色Material风格                                   │
│     - 无背景图                                           │
│     - 清新明亮                                           │
├─────────────────────────────────────────────────────────┤
│  2. 现有UI (main分支当前)                                │
│     - 睡莲背景（黄绿色调）                                │
│     - 毛玻璃效果                                         │
│     - 雾感氛围                                           │
├─────────────────────────────────────────────────────────┤
│  3. Glass UI (待开发)                                    │
│     - 睡莲背景（紫蓝绿调）                                │
│     - 纯玻璃效果（无实体背景）                             │
│     - 梦幻沉浸                                           │
└─────────────────────────────────────────────────────────┘
```

---

## ⚡ 快速恢复命令

如果您想立即回到原始UI：

```bash
# 保存当前工作（如果有未提交更改）
git stash

# 切换到托底分支
git checkout backup/original-ui-2026-05-11

# 在Android Studio中同步Gradle并运行
```

---

## 📝 注意事项

1. **托底分支是只读的**：建议不要在托底分支上直接修改，而是基于它创建新分支
2. **定期同步**：如果原始UI有bug修复，需要同步到托底分支
3. **文档记录**：在托底分支的README中记录这是"原始版本备份"

---

## ✅ 验证清单

- [ ] 成功创建 `backup/original-ui-2026-05-11` 分支
- [ ] 能够在Android Studio中切换到该分支
- [ ] 能够编译并运行原始版本
- [ ] 确认UI是白色Material风格
- [ ] 已推送到远程仓库（可选）

---

**创建时间**：2026-05-16
**基于提交**：114ebb945c218301afa714e38cd097f1e5ebf026
