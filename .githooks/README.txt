Git hooks (project-local)

To use the pre-commit hook that runs Android Lint + scanHardcodedText:

  git config core.hooksPath .githooks

On Windows without sh, run the same Gradle line from scripts/pre-commit.ps1 or manually:

  .\gradlew.bat :app:lintDebug :app:scanHardcodedText
