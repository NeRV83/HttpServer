package ru.netology.nmedia.entity

import ru.netology.nmedia.dto.Post
import jakarta.persistence.*
import ru.netology.nmedia.dto.Attachment
import ru.netology.nmedia.enumeration.AttachmentType

@Entity
data class PostEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var author: String,
    @Column(columnDefinition = "TEXT")
    var content: String,
    var published: Long,
    var likedByMe: Boolean,
    var likes: Int = 0,
    var shares: Int = 0,
    var views: Int = 0,
	var authorAvatar: String? = null,
    var videoUrl: String? = null,
    @Embedded
    var attachment: AttachmentEmbeddable?
) {
    fun toDto() = Post(
		id = id,
		author = author,
		authorAvatar = authorAvatar,
		content = content, 
		published = published, 
		likedByMe = likedByMe, 
		likes = likes, 
		shares = shares, 
		views = views, 
		videoUrl = videoUrl,
        attachment = attachment?.toDto()
    )

    companion object {
        fun fromDto(dto: Post)= PostEntity(
            id = dto.id,
            author = dto.author,
            authorAvatar = dto.authorAvatar,
            content = dto.content,
            published = dto.published,
            likedByMe = dto.likedByMe,
            likes = dto.likes,
            shares = dto.shares,
            views = dto.views,
            videoUrl = dto.videoUrl,
            attachment = AttachmentEmbeddable.fromDto(dto.attachment)
        )
    }
}

@Embeddable
data class AttachmentEmbeddable(
    var url: String,
    @Column(columnDefinition = "TEXT")
    var description: String?,
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) {
    fun toDto() = Attachment(url, description, type)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            AttachmentEmbeddable(it.url, it.description, it.type)
        }
    }
}