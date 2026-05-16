# Glass UI 系统实现计划

## 概述

基于对图12参考UI的深度分析，开发一套全新的 Glass UI 系统，与现有UI并存。

**核心原则**：
- 背景图无任何效果，保持原始清晰度
- 所有功能模块（TaskCard、BottomNav）都是"透明玻璃片"
- 玻璃效果 = 背景模糊 + 极淡填充 + 细边框 + 边缘发光

---

## 背景图选择

| 系统 | 背景图 | 色调 | 状态 |
|------|--------|------|------|
| 现有UI | 当前睡莲（黄绿色调） | 暖黄绿 | 保留 |
| Glass UI | 503901ldsdl.jpg（睡莲） | 紫蓝绿 | 新增 |

---

## Glass UI 效果规范

### 1. TaskCard（聊天气泡风格）

| 效果 | 数值 | 说明 |
|------|------|------|
| 背景模糊 | 25-35dp | 对背后睡莲图的高斯模糊 |
| 半透明填充 | 白色 5-8% | 极淡，保持透明感 |
| 边框 | 1px 白色 20-30% | 细边框定义边界 |
| 边缘发光 | 轻微外发光 | 让卡片从背景"浮"出 |
| 圆角 | 20dp | 柔和现代 |

### 2. BottomNavigation（底部玻璃栏）

| 效果 | 数值 | 说明 |
|------|------|------|
| 背景模糊 | 35-45dp | 比气泡更强，突出底部 |
| 半透明填充 | 极淡 5-10% | 几乎透明，依赖背景色调 |
| 顶部边缘 | 渐变模糊过渡 | 与内容柔和融合 |
| 选中发光 | 白色外发光 | 高亮当前项 |
| 未选中态 | 低透明度白色 | 50% 透明度 |

---

## 文件结构

```
app/src/main/java/com/example/kairosapplication/
├── core/
│   └── ui/
│       ├── theme/
│       │   ├── GlassColor.kt          # Glass UI 色彩系统
│       │   └── GlassShape.kt          # Glass UI 形状定义
│       └── constants/
│           └── GlassConstants.kt      # Glass 效果数值常量
├── ui/
│   ├── glass/                         # Glass UI 组件包
│   │   ├── GlassTaskCard.kt           # 玻璃效果任务卡片
│   │   ├── GlassBottomNav.kt          # 玻璃效果底部导航
│   │   ├── GlassTimeGroupHeader.kt    # 玻璃效果时间分组头部
│   │   └── GlassEssayMainScreen.kt    # Glass 版本主屏幕
│   └── ...                            # 现有UI保持不变
└── ...

app/src/main/res/
├── drawable/
│   ├── kairos_atmosphere_bg.jpg       # 现有背景（保留）
│   └── glass_waterlily_bg.jpg         # Glass UI 背景（新增）
└── ...
```

---

## 实现步骤

### Phase 1: 基础设置

**1.1 添加睡莲背景图**
- 源文件：`C:\Users\Hasee\Pictures\KAIROS\503901ldsdl.jpg`
- 目标：`res/drawable/glass_waterlily_bg.jpg`
- 处理：复制并重命名

**1.2 创建 Glass 色彩系统**
文件：`core/ui/theme/GlassColor.kt`

```kotlin
object GlassColors {
    // 基于睡莲紫蓝绿色调
    val Primary = Color(0xFF6B7B8C)      // 蓝灰主色
    val Secondary = Color(0xFF8B9A8B)    // 绿灰辅色
    val Accent = Color(0xFF9B8B9C)       // 紫灰强调色
    
    // Glass 效果颜色
    val GlassFillLight = Color.White.copy(alpha = 0.06f)  // 6% 白填充
    val GlassBorder = Color.White.copy(alpha = 0.25f)     // 25% 白边框
    val TextPrimary = Color.White
    val TextSecondary = Color.White.copy(alpha = 0.7f)
    val TextMuted = Color.White.copy(alpha = 0.5f)
}
```

**1.3 创建 Glass 常量**
文件：`core/ui/constants/GlassConstants.kt`

```kotlin
object GlassConstants {
    // 模糊半径
    val TaskCardBlurRadius = 30.dp
    val BottomNavBlurRadius = 40.dp
    
    // 填充透明度
    val TaskCardFillAlpha = 0.06f  // 6%
    val BottomNavFillAlpha = 0.08f // 8%
    
    // 边框
    val GlassBorderWidth = 1.dp
    val GlassBorderAlpha = 0.25f
    
    // 圆角
    val TaskCardCornerRadius = 20.dp
    val BottomNavCornerRadius = 24.dp
    
    // 发光
    val GlowElevation = 4.dp
    val SelectedGlowElevation = 8.dp
}
```

### Phase 2: 核心组件开发

**2.1 GlassTaskCard**
文件：`ui/glass/GlassTaskCard.kt`

实现要点：
- 使用 `Modifier.blur()` 对背景模糊
- 极淡白色填充（6%）
- 细白边框（25%透明度）
- 轻微阴影发光
- 文字置顶对齐

**2.2 GlassBottomNav**
文件：`ui/glass/GlassBottomNav.kt`

实现要点：
- 更强模糊（40dp）
- 极淡填充（8%）
- 顶部渐变边缘过渡
- 选中项白色发光
- 未选中项50%透明度

**2.3 GlassTimeGroupHeader**
文件：`ui/glass/GlassTimeGroupHeader.kt`

实现要点：
- 胶囊形状
- 与TaskCard一致的玻璃效果
- 时间图标 + 文字

### Phase 3: 主屏幕组装

**3.1 GlassEssayMainScreen**
文件：`ui/glass/GlassEssayMainScreen.kt`

结构：
```
Box {
    // 1. 睡莲背景图（无任何效果）
    Image(painter = glass_waterlily_bg)
    
    // 2. Scaffold
    Scaffold(
        topBar = { GlassTopBar() },
        bottomBar = { GlassBottomNav() }
    ) { padding ->
        // 3. 内容区域
        LazyColumn {
            items { GlassTaskCard(...) }
        }
    }
}
```

### Phase 4: TDD 测试

**4.1 测试文件**
文件：`src/test/java/.../ui/glass/GlassStyleTest.kt`

测试项：
- Glass 模糊常量存在且数值正确
- Glass 填充透明度正确
- Glass 边框宽度正确
- Glass 圆角半径正确

---

## 与现有UI的对比

| 特性 | 现有UI | Glass UI |
|------|--------|----------|
| 背景图 | 睡莲（黄绿） | 睡莲（紫蓝绿） |
| TaskCard背景 | 白色半透明 | 纯玻璃效果（模糊+极淡填充） |
| BottomNav背景 | 白色半透明 | 纯玻璃效果（强模糊+极淡填充） |
| 视觉风格 | 清新明亮 | 梦幻沉浸 |
| 技术实现 | 传统遮罩 | 纯效果层 |

---

## 验证清单

- [ ] 睡莲背景图成功添加到项目
- [ ] GlassColor.kt 色彩系统定义完成
- [ ] GlassConstants.kt 常量定义完成
- [ ] GlassTaskCard 组件实现并通过测试
- [ ] GlassBottomNav 组件实现并通过测试
- [ ] GlassEssayMainScreen 主屏幕组装完成
- [ ] TDD 测试全部通过
- [ ] 现有UI未被破坏，两套系统并存
- [ ] 编译成功，无错误

---

## 技术难点与解决方案

### 难点1: blur() Modifier 的层级问题
**问题**：`blur()` 会模糊自身内容，而不是背后内容
**解决**：使用 `graphicsLayer { renderEffect = BlurEffect(...) }` 或正确控制绘制顺序

### 难点2: 底部导航的模糊边缘
**问题**：底部需要与内容柔和过渡
**解决**：顶部使用渐变遮罩，从透明到半透明

### 难点3: 两套UI并存
**问题**：需要避免命名冲突和重复代码
**解决**：使用 `glass` 包隔离，共享通用工具类

---

## 下一步行动

1. 用户确认计划
2. 开始 Phase 1 实施
3. 按TDD流程逐个组件开发
4. 最终验证两套UI并存
