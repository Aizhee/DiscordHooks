
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
       implementation 'com.github.Aizhee:DiscordHooks:1.3'
    }
    ```
## Wiki
[Full Documentation](https://github.com/Aizhee/DiscordHooks/wiki/Documentation)
## Usage

### Sending a Simple Text Message
- For Kotlin:
```kotlin
val webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token"
val message = DiscordWebhookSender.DiscordWebhookMessage(content = "Hello, kotlin.")
sendWebhook(webhookUrl, message)
```
Output:

![image](https://media.discordapp.net/attachments/1006249405013307413/1135867495241228378/image.png?width=406&height=83)
- For Java:
```java
String webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token";
DiscordWebhookSender.DiscordWebhookMessage message = new DiscordWebhookSender.DiscordWebhookMessage();
message.setContent("Hello, java.");
sendWebhook(webhookUrl, message);
```
![image](https://media.discordapp.net/attachments/1006249405013307413/1135869159012565022/image.png?width=383&height=97)
### Sending a Rich Embed Message
- For Kotlin:
```kotlin
val webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token"
        val author = DiscordWebhookSender.EmbedAuthor("John Doe", "https://example.com", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803")
        val field1 = DiscordWebhookSender.EmbedField("Field 1", "Value 1", true)
        val field2 = DiscordWebhookSender.EmbedField("Field 2", "Value 2", true)
        val fields = listOf(field1, field2)
        val thumbnail = DiscordWebhookSender.EmbedThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803")
        val image1= DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/9322322/pexels-photo-9322322.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        val image2 = DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/133689/pexels-photo-133689.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        val image3 = DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/2798477/pexels-photo-2798477.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        val image4 = DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/16821447/pexels-photo-16821447/free-photo-of-facade-of-the-fort-santiago-manila-philippines.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        val images = listOf(image1, image2, image3, image4)
        val footer = DiscordWebhookSender.EmbedFooter("This is the footer text", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803")
        val timestamp = "now" // You can also use a Date object here to set a specific timestamp.
        val embed = DiscordWebhookSender.DiscordEmbed(
            author = author,
            title = "Hello, Kotlin!",
            url = "https://example.com",
            description = "This is a rich embed message with fields.",
            color = 16711680, // Decimal color code for red: 255 Red, 0 Green, 0 Blue
            fields = fields,
            thumbnail = thumbnail,
            images = images,
            footer = footer,
            timestamp = timestamp
        )

        val message = DiscordWebhookSender.DiscordWebhookMessage(embeds = listOf(embed), notify = false)
        sendWebhook(webhookUrl, message)
```
Output:

![image](https://media.discordapp.net/attachments/1006249405013307413/1136003905025749063/image.png?width=632&height=670)
- For Java:
```java
String webhookUrl = "https://discord.com/api/webhooks/your_webhook_id/your_webhook_token";
DiscordWebhookSender.EmbedAuthor author = new DiscordWebhookSender.EmbedAuthor("John Doe", "https://example.com", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803");
DiscordWebhookSender.EmbedField field1 = new DiscordWebhookSender.EmbedField("Field 1", "Value 1", true);
DiscordWebhookSender.EmbedField field2 = new DiscordWebhookSender.EmbedField("Field 2", "Value 2", true);
List<DiscordWebhookSender.EmbedField> fields = Arrays.asList(field1, field2);
DiscordWebhookSender.EmbedThumbnail thumbnail = new DiscordWebhookSender.EmbedThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803");
DiscordWebhookSender.EmbedImage image1 = new DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/9322322/pexels-photo-9322322.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
DiscordWebhookSender.EmbedImage image2 = new DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/133689/pexels-photo-133689.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
DiscordWebhookSender.EmbedImage image3 = new DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/2798477/pexels-photo-2798477.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
DiscordWebhookSender.EmbedImage image4 = new DiscordWebhookSender.EmbedImage("https://images.pexels.com/photos/16821447/pexels-photo-16821447/free-photo-of-facade-of-the-fort-santiago-manila-philippines.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
List<DiscordWebhookSender.EmbedImage> images = Arrays.asList(image1, image2, image3, image4);
DiscordWebhookSender.EmbedFooter footer = new DiscordWebhookSender.EmbedFooter("This is the footer text", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/48px-Minecraft_missing_texture_block.svg.png?20230311130803");
String timestamp = "now"; // You can also use a Date object here to set a specific timestamp.
DiscordWebhookSender.DiscordEmbed embed = new DiscordWebhookSender.DiscordEmbed(
    author,
    "Hello, Java!",
    "https://example.com",
    "This is a rich embed message with fields.",
    16711680, // Decimal color code for red: 255 Red, 0 Green, 0 Blue
    fields,
    thumbnail,
    images,
    footer,
    timestamp
);
DiscordWebhookSender.DiscordWebhookMessage message = new DiscordWebhookSender.DiscordWebhookMessage(Arrays.asList(embed), false);

sendWebhook(webhookUrl, message);
```
Output:

![image](https://media.discordapp.net/attachments/1006249405013307413/1136003449331388466/image.png?width=631&height=670)
## Data Classes
### `sendWebhook` Function

Sends a Discord webhook message with the provided [message] data.

| Parameter     | Type                           | Description                                                                                       |
|---------------|--------------------------------|---------------------------------------------------------------------------------------------------|
| webhookUrl    | `String`                       | The URL of the Discord webhook to send the message to. Don't know how to get it? [read this](https://www.svix.com/resources/guides/how-to-make-webhook-discord/).                                           |
| message       | `DiscordWebhookMessage`        | The [DiscordWebhookMessage](#discordwebhookmessage) object containing the message data.          |
### `DiscordWebhookMessage`

Represents a Discord Webhook Message.

| Parameter     | Type              | Description                                                                                                                                                         |
|---------------|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| username      | `String?`         | Overrides the default username of the webhook.                                                                                                                      |
| avatarUrl     | `String?`         | Overrides the default avatar of the webhook.                                                                                                                        |
| content       | `String?`         | The simple text message to be sent. Limited to 2000 characters.                                                                                                     |
| notify       | `Boolean?`         | Trigger push and desktop notifications.                                                                                                    |
| thread       | `String?`         | Name of thread to create (requires the webhook channel to be a forum channel).                                                                                                     |
| embeds        | `List<DiscordEmbed>?` | An array of [DiscordEmbed](#discordembed) objects, representing rich embeds to be included in the message. Max of 10 Embeds                                                          |


### `DiscordEmbed`

Represents a Discord Embed containing rich content.

| Parameter     | Type                     | Description                                                                                                                                                                         |
|---------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| author        | `EmbedAuthor?`           | The [EmbedAuthor](#embedauthor) of the embed.                                                                                                                                       |
| title         | `String?`                | The title of the embed.                                                                                                                                                            |
| url           | `String?`                | The URL of the embed. If specified, the title becomes a hyperlink.                                                                                                                 |
| description   | `String?`                | The main text content of the embed.                                                                                                                                                |
| color         | `Int?`                   | The color code of the embed. Use the Decimal numeral system, not Hexadecimal. You may use this [converter](https://www.binaryhexconverter.com/hex-to-decimal-converter) to find the equivalent of your hex code                                                                                                  |
| fields        | `List<EmbedField>?`      | An array of [EmbedField](#embedfield) objects containing additional information in the form of fields.                                                                              |
| thumbnail     | `EmbedThumbnail?`        | The [EmbedThumbnail](#embedthumbnail) of the embed, typically a small image associated with the content.                                                                           |
| images         | `List<EmbedImage?>?`            | An array of [EmbedImage](#embedimage) objects of the embed, typically a larger image associated with the content. Up to 4 images . NOTE: if the Embed's URL is null or empty, you may not add more than one image.                                                                               |
| footer        | `EmbedFooter?`           | The [EmbedFooter](#embedfooter) of the embed, typically used for credits or attributions.                                                                                          |
| timestamp     | `Date?` or `String?`                | The timestamp to display in the embed.  If set to a string `"now"`, the current date will be used.                                                   |

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
- The timestamp can be set to "now" to use the current date and time, or you can provide a Date object to set a specific timestamp. 


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

