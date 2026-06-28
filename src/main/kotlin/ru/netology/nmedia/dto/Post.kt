package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: Long,
    val isLiked: Boolean,
    val likes: Int = 0,
)