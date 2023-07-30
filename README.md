
[![](https://jitpack.io/v/Aizhee/DiscordHooks.svg)](https://jitpack.io/#Aizhee/DiscordHooks) 
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


# DiscordHooks

![Logo](https://cdn.discordapp.com/attachments/986206581169463339/1135244456736604331/Untitledd-1.png)

DiscordHooks is a user-friendly Android-Kotlin library designed for easy integration with Discord webhook URLs. It enables real-time messaging and notifications, allowing developers to engage with Discord channels effortlessly. Simplify your communication process and enhance user experiences with DiscordHooks. 

It uses okHttp3 and json to post messages to the desired URLs.
This is my first library so any feedback is highly appreciated ╰(▔∀▔)╯ 

_This software is not affiliated to, nor has it been authorized, sponsored or otherwise approved by Discord or Google LLC._


## How to use

1. Add the [jitpack.io](https://jitpack.io) repository to your project's level `build.gradle`:
    ```groovy
    allprojects {
        repositories {
            // ... more repos
            maven { url "https://jitpack.io" }
        }
    }
    ```
   or if you are using `settings.gradle`:
    ```groovy
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            // ... more repos
            maven { url "https://jitpack.io" }
        }
    }
    ```
2. Add the dependency to your module's `build.gradle` file:
    ```groovy
    dependencies {
       implementation 'com.github.Aizhee:DiscordHooks:1.0'
    }
    ```



## Documentation 
TBA

## License
[MIT License](https://choosealicense.com/licenses/mit/)

Copyright (c) 2023 Aizhe

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

