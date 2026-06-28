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
    var videoUrl: String? = null
) {
    fun toDto() = Post(id, author, content, published, likedByMe, likes, shares, views, videoUrl)

    companion object {
        fun fromDto(dto: Post): PostEntity {
            return PostEntity(
                dto.id,
                dto.author,
                dto.content,
                dto.published,
                dto.likedByMe,
                dto.likes,
                dto.shares,
                dto.views,
                dto.videoUrl
            )
        }
    }
}