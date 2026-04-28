# Kairos Global UI Standards

This directory defines reusable UI standards for the Kairos Android app.

## Structure

- `app/src/main/java/com/example/kairosapplication/core/ui/StyleConstants.kt`
  - Global style constants (colors, spacing, typography, shape, interaction)
- `app/src/main/java/com/example/kairosapplication/core/ui/CommonComponents.kt`
  - Reusable Compose components (Back button, TimeBlock, Card, Clickable item)
- `app/src/main/java/com/example/kairosapplication/core/CodeStandard.kt`
  - Naming/state/comment standards and anti-hardcode warning annotation
- `core/README.md`
  - Usage guide (this file)

## Purpose

1. Replace hardcoded visual values with centralized constants.
2. Keep Today visual behavior unchanged while standardizing references.
3. Make new pages (`Essay`, `View`, `Widget`, `Mine`) plug in quickly.

## Quick Usage Examples

### 1) Replace hardcoded color/spacing in Today page

Before:

```kotlin
Modifier
    .background(Color(0xFFF9F9F9))
    .padding(horizontal = 16.dp)
```

After:

```kotlin
Modifier
    .background(AppColors.ScreenBackground)
    .padding(horizontal = AppSpacing.PageHorizontal)
```

### 2) Reuse common back button

```kotlin
import com.example.kairosapplication.core.ui.CommonBackButton

CommonBackButton {
    navController.popBackStack()
}
```

### 3) Reuse common card container

```kotlin
CommonCard(
    containerColor = AppColors.SurfaceWhite
) {
    // content
}
```

### 4) Reuse common time block

```kotlin
CommonTimeBlock(
    label = "MORNING",
    count = tasks.size,
    icon = Icons.Default.WbTwilight,
    backgroundColor = AppColors.MorningBackground,
    expanded = isExpanded,
    onToggle = { isExpanded = !isExpanded },
    onCreateClick = { /* open sheet */ }
) {
    // task list content
}
```

## Integration Steps

1. Import constants and components:
   - `com.example.kairosapplication.core.ui.StyleConstants.*`
   - `com.example.kairosapplication.core.ui.*`
2. Replace hardcoded values in `TodayScreen.kt` and `MainScreen.kt` incrementally:
   - colors -> `AppColors`
   - spacing/size -> `AppSpacing` / `AppSize`
   - radius -> `AppShapes`
   - typography -> `AppTypography`
3. Keep behavior logic unchanged while replacing only style references.
4. Build and verify:
   - `./gradlew.bat :app:assembleDebug`

## Extension Rules

1. New visual token must be added to `StyleConstants.kt` first.
2. Shared UI pattern must be added to `CommonComponents.kt`.
3. Keep naming and state rules aligned with `CodeStandard.kt`.
4. No direct hardcoded style values in new feature pages unless temporary and explicitly annotated.

## Git Commit Commands

```bash
git add "app/src/main/java/com/example/kairosapplication/core/ui/StyleConstants.kt" "app/src/main/java/com/example/kairosapplication/core/ui/CommonComponents.kt" "app/src/main/java/com/example/kairosapplication/core/CodeStandard.kt" "core/README.md"
git commit -m "add reusable global UI standards library for Kairos"
```

