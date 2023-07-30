/***************************************************************************************************
 * Copyright © Aizhe 2023. See LICENSE in the root directory for details.
 **************************************************************************************************/
package com.aizhe.discordhooks

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * A simple library for sending Discord webhook messages with embeds from an Android app.
 * This library allows you to send messages with both plain content and rich embeds to a Discord channel.
 */

object DiscordWebhookSender {

    private val client = OkHttpClient()

    /**
     * Data class representing the Author of an embed.
     * @param name The name of the author.
     * @param url The URL of the author. If specified, the name becomes a hyperlink.
     * @param iconUrl The URL of the author's icon.
     */
    data class EmbedAuthor(val name: String, val url: String?, val iconUrl: String?)

    /**
     * Data class representing a Field in an embed.
     * @param name The name of the field.
     * @param value The value of the field.
     * @param inline If true, the field will be displayed in the same line as other fields (max 3 in a line).
     */
    data class EmbedField(val name: String, val value: String, val inline: Boolean = false)

    /**
     * Data class representing the Thumbnail of an embed.
     * @param url The URL of the thumbnail image.
     */
    data class EmbedThumbnail(val url: String)

    /**
     * Data class representing the Image of an embed.
     * @param url The URL of the image.
     */
    data class EmbedImage(val url: String)

    /**
     * Data class representing the Footer of an embed.
     * @param text The text of the footer. It does not support Markdown.
     * @param iconUrl The URL of the footer's icon.
     */
    data class EmbedFooter(val text: String, val iconUrl: String?)

    /**
     * Data class representing a Discord Embed.
     * An embed allows you to format and structure your messages in a richer way with various elements.
     * @param author The [EmbedAuthor] of the embed.
     * @param title The title of the embed.
     * @param url The URL of the embed. If specified, the title becomes a hyperlink.
     * @param description The main text content of the embed.
     * @param color The color code of the embed. Use the Decimal numeral system, not Hexadecimal.
     *              You can use an online color picker to get the color code.
     * @param fields An array of [EmbedField] objects containing additional information in the form of fields.
     * @param thumbnail The [EmbedThumbnail] of the embed, typically a small image associated with the content.
     * @param image The [EmbedImage] of the embed, typically a larger image associated with the content.
     * @param footer The [EmbedFooter] of the embed, typically used for credits or attributions.
     */
    data class DiscordEmbed(
        val author: EmbedAuthor?,
        val title: String?,
        val url: String?,
        val description: String?,
        val color: Int?,
        val fields: List<EmbedField>?,
        val thumbnail: EmbedThumbnail?,
        val image: EmbedImage?,
        val footer: EmbedFooter?
    )

    /**
     * Data class representing a Discord Webhook Message.
     * This class encapsulates all the data required to send a message to a Discord channel via a webhook.
     * @param username Overrides the default username of the webhook.
     * @param avatarUrl Overrides the default avatar of the webhook.
     * @param content The simple text message to be sent. Limited to 2000 characters.
     * @param embeds An array of [DiscordEmbed] objects, representing rich embeds to be included in the message.
     */
    data class DiscordWebhookMessage(
        val username: String?,
        val avatarUrl: String?,
        val content: String?,
        val embeds: List<DiscordEmbed>?
    )

    /**
     * Sends a Discord webhook message with the provided [message] data.
     * @param webhookUrl The URL of the Discord webhook to send the message to.
     * @param message The [DiscordWebhookMessage] object containing the message data.
     */
    fun sendWebhook(webhookUrl: String, message: DiscordWebhookMessage) {
        val jsonObject = JSONObject()
        message.username?.let { jsonObject.put("username", it) }
        message.avatarUrl?.let { jsonObject.put("avatar_url", it) }
        message.content?.let { jsonObject.put("content", it) }

        message.embeds?.let {
            val embedsArray = JSONArray()
            for (embed in it) {
                val embedObject = JSONObject()
                embed.author?.let { author ->
                    val authorObject = JSONObject()
                    authorObject.put("name", author.name)
                    author.url?.let { authorObject.put("url", it) }
                    author.iconUrl?.let { authorObject.put("icon_url", it) }
                    embedObject.put("author", authorObject)
                }
                embed.title?.let { embedObject.put("title", it) }
                embed.url?.let { embedObject.put("url", it) }
                embed.description?.let { embedObject.put("description", it) }
                embed.color?.let { embedObject.put("color", it) }
                embed.fields?.let {
                    val fieldsArray = JSONArray()
                    for (field in it) {
                        val fieldObject = JSONObject()
                        fieldObject.put("name", field.name)
                        fieldObject.put("value", field.value)
                        fieldObject.put("inline", field.inline)
                        fieldsArray.put(fieldObject)
                    }
                    embedObject.put("fields", fieldsArray)
                }
                embed.thumbnail?.let {
                    val thumbnailObject = JSONObject()
                    thumbnailObject.put("url", it.url)
                    embedObject.put("thumbnail", thumbnailObject)
                }
                embed.image?.let {
                    val imageObject = JSONObject()
                    imageObject.put("url", it.url)
                    embedObject.put("image", imageObject)
                }
                embed.footer?.let {
                    val footerObject = JSONObject()
                    footerObject.put("text", it.text)
                    it.iconUrl?.let { iconUrl -> footerObject.put("icon_url", iconUrl) }
                    embedObject.put("footer", footerObject)
                }
                embedsArray.put(embedObject)
            }
            jsonObject.put("embeds", embedsArray)
        }

        val json = jsonObject.toString()
        val requestBody = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(webhookUrl)
            .post(requestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Unexpected HTTP response: ${response.code}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
