package ru.netology.nmedia.entity

import ru.netology.nmedia.dto.Post
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

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
    var videoUrl: String? = null
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
		videoUrl = videoUrl)

    companion object {
        fun fromDto(dto: Post)= PostEntity(
            id = dto.id,
            author = dto.author,
            authorAvatar = dto.authorAvatar,
            content = dto.content,
            published = dto.published,
            likedByMe = dto.likedByMe,
            likes = dto.likes
            shares = dto.shares,
            views = dto.views,
            videoUrl = dto.videoUrl
        )
    }
}