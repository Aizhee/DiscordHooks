
[![](https://jitpack.io/v/Aizhee/DiscordHooks.svg)](https://jitpack.io/#Aizhee/DiscordHooks) 
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


# DiscordHooks

![Logo](https://cdn.discordapp.com/attachments/986206581169463339/1135244456736604331/Untitledd-1.png)

DiscordHooks is a user-friendly Android-Kotlin library designed for easy integration with Discord webhook URLs. It enables real-time messaging and notifications, allowing developers to engage with Discord channels effortlessly. Simplify your communication process and enhance user experiences with DiscordHooks. 

This is my first library so any feedback is highly appreciated ╰(▔∀▔)╯ 

_This software is not affiliated to, nor has it been authorized, sponsored or otherwise approved by Discord or Google LLC._

## Features

- Send Discord webhook messages with plain content and rich embeds.
- Support for formatting and structuring messages with various elements using embeds.
- Customize the webhook username and avatar for each message.

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

## Usage

### Sending a Simple Text Message

```kotlin
val webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token"
val message = DiscordWebhookSender.DiscordWebhookMessage(content = "Hello, this is a simple text message.")
DiscordWebhookSender.sendWebhook(webhookUrl, message)
```

### Sending a Rich Embed Message

```kotlin
val webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token"
val author = DiscordWebhookSender.EmbedAuthor("John Doe", "https://example.com", "https://example.com/avatar.png")
val field1 = DiscordWebhookSender.EmbedField("Field 1", "Value 1", true)
val field2 = DiscordWebhookSender.EmbedField("Field 2", "Value 2", true)
val fields = listOf(field1, field2)
val embed = DiscordWebhookSender.DiscordEmbed(
    author = author,
    title = "Rich Embed Title",
    description = "This is a rich embed message with fields.",
    color = 16711680, // Decimal color code for red: 255 Red, 0 Green, 0 Blue
    fields = fields
)
val message = DiscordWebhookSender.DiscordWebhookMessage(embeds = listOf(embed))
DiscordWebhookSender.sendWebhook(webhookUrl, message)
```

## Data Classes

### `DiscordEmbed`

Represents a Discord Embed containing rich content.

| Parameter     | Type                     | Description                                                                                                                                                                         |
|---------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| author        | `EmbedAuthor?`           | The [EmbedAuthor](#embedauthor) of the embed.                                                                                                                                       |
| title         | `String?`                | The title of the embed.                                                                                                                                                            |
| url           | `String?`                | The URL of the embed. If specified, the title becomes a hyperlink.                                                                                                                 |
| description   | `String?`                | The main text content of the embed.                                                                                                                                                |
| color         | `Int?`                   | The color code of the embed. Use the Decimal numeral system, not Hexadecimal.                                                                                                       |
| fields        | `List<EmbedField>?`      | An array of [EmbedField](#embedfield) objects containing additional information in the form of fields.                                                                              |
| thumbnail     | `EmbedThumbnail?`        | The [EmbedThumbnail](#embedthumbnail) of the embed, typically a small image associated with the content.                                                                           |
| image         | `EmbedImage?`            | The [EmbedImage](#embedimage) of the embed, typically a larger image associated with the content.                                                                                  |
| footer        | `EmbedFooter?`           | The [EmbedFooter](#embedfooter) of the embed, typically used for credits or attributions.                                                                                          |

### `EmbedAuthor`

Represents the Author of an embed.

| Parameter     | Type         | Description                                                                                                                                                         |
|---------------|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| name          | `String`     | The name of the author.                                                                                                                                             |
| url           | `String?`    | The URL of the author. If specified, the name becomes a hyperlink.                                                                                                  |
| iconUrl       | `String?`    | The URL of the author's icon.                                                                                                                                       |

### `EmbedField`

Represents a Field in an embed.

| Parameter     | Type       | Description                                                                                                                                                         |
|---------------|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| name          | `String`   | The name of the field.                                                                                                                                              |
| value         | `String`   | The value of the field.                                                                                                                                             |
| inline        | `Boolean`  | If true, the field will be displayed in the same line as other fields (max 3 in a line).                                                                           |

### `EmbedThumbnail`

Represents the Thumbnail of an embed.

| Parameter     | Type       | Description                                                                                                                                                         |
|---------------|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| url           | `String`   | The URL of the thumbnail image.                                                                                                                                     |

### `EmbedImage`

Represents the Image of an embed.

| Parameter     | Type       | Description                                                                                                                                                         |
|---------------|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| url           | `String`   | The URL of the image.                                                                                                                                               |

### `EmbedFooter`

Represents the Footer of an embed.

| Parameter     | Type       | Description                                                                                                                                                         |
|---------------|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| text          | `String`   | The text of the footer. It does not support Markdown.                                                                                                               |
| iconUrl       | `String?`  | The URL of the footer's icon.                                                                                                                                       |

### `DiscordWebhookMessage`

Represents a Discord Webhook Message.

| Parameter     | Type              | Description                                                                                                                                                         |
|---------------|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| username      | `String?`         | Overrides the default username of the webhook.                                                                                                                      |
| avatarUrl     | `String?`         | Overrides the default avatar of the webhook.                                                                                                                        |
| content       | `String?`         | The simple text message to be sent. Limited to 2000 characters.                                                                                                     |
| embeds        | `List<DiscordEmbed>?` | An array of [DiscordEmbed](#discordembed) objects, representing rich embeds to be included in the message.                                                           |

## Notes

- The library uses the OkHttp library for making HTTP requests. Make sure to include the OkHttp library in your project.


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

