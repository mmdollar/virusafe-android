# ViruSafe for Android

ViruSafe aims to help fight with COVID-19 by offering people to share their symptoms as well as track the spread of COVID-19 with an interactive map, that shows how the infection spreads throughout Bulgaria.

ViruSafe mobile app provides access to the following:
- Receive up-to-date information regarding COVID-19
- Regular symptoms sharing
- Sharing your location, in order to compare your location to all users who have developed symptoms
- Option to be warned if you have been in close proximity to other symptomatic users
- Option to receive location-based notifications and alerts

<a href='https://play.google.com/store/apps/details?id=bg.government.virusafe&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' height="90"/></a>

Overview:
- [ViruSafe for Android](#virusafe-for-android)
  - [Build Instructions](#build-instructions)
  - [Code Styleguide](#code-styleguide)
    - [Language](#language)
    - [Formatting](#formatting)
    - [Static code analysis](#static-code-analysis)
  - [Using the REST API](#using-the-rest-api)
    - [Using a Mock API](#using-a-mock-api)
  - [Contributing](#contributing)
  - [Security](#security)
  - [Contacts](#contacts)
  - [License](#license)

## Build Instructions

1. Make sure you've installed [Android Studio](https://developer.android.com/studio/index.html).
2. Setup your dev environment by running `sh setup-dev.sh` from the root directory of the project.
3. In Android Studio, open the project from the local repository.
4. Go to the [Use a Mock API](Using-Mock-API.md) and create your own Mock API, after that put the newly created endpoint in the `gradle.properties` file in the `default_endpoint` property.
5. Connect a device or emulator and run the project.

> **Note:** The project depends on different build variants, which are configured in the `build.gradle` files of the project. There are 3 build types:
> 	1. Debug - The preferred build variant for development.
> 	2. Release - This one is for building the release apks for the store.
> 	3. Mk - This one is for switching to other locales/regions apart from the BG version.
> It is preferred to use the **Debug** type for making any new changes, but make sure when you introduce them that all of the other build variants are still working.

## Code Styleguide

### Language
We use [Kotlin](https://kotlinlang.org/).

In order for code changes to be accepted, they should use the Kotlin programming language, or provide a detailed and justified explanation why Kotlin was not used.

### Formatting
We use our own `android-code-formatter.xml` file, which can be found in the repo.
The formatting is build upon the official coding conventions from [Google](https://source.android.com/setup/contribute/code-style) and [JetBrains](https://kotlinlang.org/docs/reference/coding-conventions.html), so you should use it too.

### Static code analysis
For static code analysis we use the ```checkstyle``` and ```detekt``` plugins. 
Before committing any new changes it is a good practice to run both of the plugins to check for any issues, other ways your changes may be declined.
To run both of the plugins use the following commands:

```$ ./gradlew checkstyle```

```$ ./gradlew detekt```

> **Note:** Currently we have some issues in the project which are coming from both of these static analysis tools. 
We are aware of that and we are working on fixing them - [virusafe-android#3](https://github.com/scalefocus/virusafe-android/issues/3#issue-598955030).
If you want to help us resolve them you are more then welcome.

## Using the REST API

Swagger Documentation for the ViruSafe REST API is available at the [ViruSafe SwaggerHub](https://app.swaggerhub.com/apis-docs/ViruSafe/viru-safe_backend_rest_api/1.0.0).

Also, the ViruSafe Swagger API Docs are available for [download as JSON](https://api.swaggerhub.com/apis/ViruSafe/viru-safe_backend_rest_api/1.0.0) and [as YAML](https://api.swaggerhub.com/apis/ViruSafe/viru-safe_backend_rest_api/1.0.0/swagger.yaml) files. These become useful when setting up your Mock API.

### Using a Mock API

To develop the mobile app against a Mock API, please check the guidelines on how to [Use a Mock API](Using-Mock-API.md).

## Contributing

Read our [Contributing Guide](CONTRIBUTING.md) to learn about reporting issues, contributing code, and more ways to contribute.

## Security

If you happen to find a security vulnerability, we would appreciate you letting us know by contacting us on - virusafe.support (at) scalefocus.com and allowing us to respond before disclosing the issue publicly.

## Contacts

Feel free to checkout our [Slack Team](https://join.slack.com/t/virusafe/shared_invite/zt-dthph60w-KGyk_s6rjoGa6WjR7~tCAg) and join the discussion there ✌️

## License

Copyright 2020 SCALE FOCUS AD

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
