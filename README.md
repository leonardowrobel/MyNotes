
<a name="top"></a>  
![language](https://img.shields.io/badge/language-Kotlin-239120) ![OS](https://img.shields.io/badge/OS-Android-0078D4) ![GitHub last commit](https://img.shields.io/github/last-commit/leonardowrobel/MyNotes)
# MyNotes
*MyNotes* is an simple notes Android project for personal studies and development practice.

## Technology Stack and tools
Some of the technologies and tools used:

* Android Jetpack
  * Navigation
  * Room
  * Compose
  * JUnit
* Google Firebase
  * App Distribution
  * Authentication
  * Firestore
* [Gradle](https://gradle.org/)
* [Hilt](https://dagger.dev/hilt/)
* [Shields.io](https://shields.io/badges)
* [EasyAppIcon](https://easyappicon.com/)
* [UxWing](https://uxwing.com/)
* [Coolors](https://coolors.co/)

## App Distribution

run: <br/>
`./gradlew assembleDevelopment appDistributionUploadDevelopment`

## Test Reports for Development
For **Unit Tests** run:<br/>
`./gradlew :app:createDevelopmentUnitTestCoverageReport`

Report location:

    path-to-your-project/module-name/build/reports/coverage/test/variant/index.html

For **Instrumented Tests** run:<br/>
`./gradlew :module-name:createDevelopmentAndroidTestCoverageReport`

Report location:

    path-to-your-project/module-name/build/reports/coverage/androidTest/variant/connected/index.html