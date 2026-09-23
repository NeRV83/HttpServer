package ru.netology.nmedia.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.netology.nmedia.enumeration.AttachmentType

data class Post(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String? = null,
    val content: String,
    val published: Long,
    val likedByMe: Boolean,
    val likes: Int = 0,
    val shares: Int = 0,
    val views: Int = 0,
    val videoUrl: String? = null,
    val attachment: Attachment? = null,
    val ownedByMe: Boolean = false,
) {
    @JsonCreator
    constructor(
        @JsonProperty("id") id: Long?,
        @JsonProperty("authorId") authorId: Long?,
        @JsonProperty("author") author: String?,
        @JsonProperty("authorAvatar") authorAvatar: String?,
        @JsonProperty("content") content: String,
        @JsonProperty("published") published: Long,
        @JsonProperty("likedByMe") likedByMe: Boolean?,
        @JsonProperty("likes") likes: Int?,
        @JsonProperty("shares") shares: Int? = 0,
        @JsonProperty("views") views: Int? = 0,
        @JsonProperty("videoUrl") videoUrl: String?,
        @JsonProperty("attachment") attachment: Attachment?,
        @JsonProperty("ownedByMe") ownedByMe: Boolean?,
    ) : this(
        id = id ?: 0,
        author = author ?: "",
        content = content,
        published = published,
        likedByMe = likedByMe ?: false,
        authorAvatar = authorAvatar,
        attachment = attachment,
        likes = likes ?: 0,
        shares = shares ?: 0,
        views = views ?: 0,
        videoUrl = videoUrl,
        authorId = authorId ?: 0,
        ownedByMe = ownedByMe ?: false,
    )
}

data class Attachment(
    val url: String,
    val description: String?,
    val type: AttachmentType,
)
