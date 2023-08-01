/***************************************************************************************************
 * Copyright © Aizhe 2023. See LICENSE in the root directory for details.
 **************************************************************************************************/
package com.aizhe.discordhooks

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
    data class EmbedAuthor(val name: String, val url: String? = null, val iconUrl: String? = null)

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
    data class EmbedFooter(val text: String, val iconUrl: String? = null)

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
     * @param images An array of [EmbedImage] objects of the embed, typically a larger image associated with the content. Up to 4 images
     * @param footer The [EmbedFooter] of the embed, typically used for credits or attributions.
     * @param timestamp The timestamp to display in the embed.If null, the timestamp will be omitted.
     *                  If set to "now", the current date will be used.
     */
    data class DiscordEmbed(
        val author: EmbedAuthor? = null,
        val title: String? = null,
        val url: String? = null,
        val description: String? = null,
        val color: Int? = 16711680,
        val fields: List<EmbedField>? = null,
        val thumbnail: EmbedThumbnail? = null,
        val images: List<EmbedImage>? = null,
        val footer: EmbedFooter?  = null,
        val timestamp: Any? = null
    )

    /**
     * Data class representing a Discord Webhook Message.
     * This class encapsulates all the data required to send a message to a Discord channel via a webhook.
     * @param username Overrides the default username of the webhook.
     * @param avatarUrl Overrides the default avatar of the webhook.
     * @param content The simple text message to be sent. Limited to 2000 characters.
     * @param thread Name of thread to create (requires the webhook channel to be a forum channel).
     * @param notify Trigger push and desktop notifications.
     * @param embeds An array of [DiscordEmbed] objects, representing rich embeds to be included in the message.
     */
    data class DiscordWebhookMessage(
        val username: String? = null,
        val avatarUrl: String? = null,
        val content: String? = null,
        val thread: String? = null,
        val notify: Boolean? = true,
        val embeds: List<DiscordEmbed>? = null
    )

    /**
     * Sends a Discord webhook message with the provided [message] data.
     * @param webhookUrl The URL of the Discord webhook to send the message to.
     * @param message The [DiscordWebhookMessage] object containing the message data.
     */
    fun sendWebhook(webhookUrl: String, message: DiscordWebhookMessage) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val json = createJsonBody(webhookUrl, message)
                val requestBody = json.toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(webhookUrl)
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    throw IOException("Unexpected HTTP response: ${response.code}")
                }

                response.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun createJsonBody(webhookUrl: String, message: DiscordWebhookMessage): String = withContext(Dispatchers.Default) {
        val jsonObject = JSONObject()
        message.content?.let { jsonObject.put("content", it) }
        message.thread?.let { jsonObject.put("thread_name", it) }
        message.notify?.let {if(!it){jsonObject.put("flags", 4096)} }
        message.embeds?.let { it ->
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
                embed.description?.let { embedObject.put("description", it) }
                embed.url?.let { embedObject.put("url", it) }
                embed.color?.let { embedObject.put("color", it) }

                val fieldsArray = JSONArray()
                embed.fields?.let {
                    for (field in it) {
                        val fieldObject = JSONObject()
                        fieldObject.put("name", field.name)
                        fieldObject.put("value", field.value)
                        fieldObject.put("inline", field.inline)
                        fieldsArray.put(fieldObject)
                    }
                }
                embedObject.put("fields", fieldsArray)

                embed.thumbnail?.let {
                    val thumbnailObject = JSONObject()
                    thumbnailObject.put("url", it.url)
                    embedObject.put("thumbnail", thumbnailObject)
                }
                embed.footer?.let {
                    val footerObject = JSONObject()
                    footerObject.put("text", it.text)
                    it.iconUrl?.let { iconUrl -> footerObject.put("icon_url", iconUrl) }
                    embedObject.put("footer", footerObject)
                }

                embed.timestamp?.let { timestamp ->
                    val timestampString = when (timestamp) {
                        is String -> if (timestamp.equals("now", ignoreCase = true)) Date().toIso8601String() else timestamp
                        is Date -> timestamp.toIso8601String()
                        else -> throw IllegalArgumentException("Invalid timestamp format.")
                    }
                    embedObject.put("timestamp", timestampString)
                }
                embed.images?.let {
                    if (it.size > 4) {
                        throw IllegalArgumentException("You can only have up to 4 embed images.")
                    }

                    if (embed.url.isNullOrEmpty() && it.size > 1) {
                        throw IllegalArgumentException("Cannot add more than 1 image if the embed URL is empty.")
                    }

                    if (embed.url.isNullOrEmpty() && it.size == 1) {
                        val imageObject = JSONObject()
                        for (image in it) {
                            imageObject.put("url", image.url)
                        }
                        embedObject.put("url", imageObject)
                    } else {
                        val firstImage = it.getOrNull(0)

                        if (firstImage != null) {
                            val imageObject = JSONObject()
                            imageObject.put("url", firstImage.url)
                            val newEmbedObject = JSONObject(embedObject.toString())
                            newEmbedObject.put("image", imageObject)
                            newEmbedObject.put("url", embed.url)
                            embedsArray.put(newEmbedObject)
                        }

                        for (i in 1 until it.size) {
                            val image = it[i]
                            val imagesObject = JSONObject()
                            imagesObject.put("url", image.url)
                            val imagesGroupObject = JSONObject()
                            imagesGroupObject.put("image", imagesObject)
                            imagesGroupObject.put("url", embed.url)
                            embedsArray.put(imagesGroupObject)
                        }
                    }
                }

                embedsArray.put(embedObject)
            }
            jsonObject.put("embeds", embedsArray)
        }
        jsonObject.toString()
    }

    //get time
    private val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    /**
     * Extension function to convert a Date object to its ISO 8601 formatted string representation.
     * @return The ISO 8601 formatted string representation of the Date.
     */
    private fun Date.toIso8601String(): String {
        return iso8601Format.format(this)
    }
}
