package ru.netology.nmedia.service

import com.github.javafaker.Faker
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.netology.nmedia.dto.Post
import java.time.OffsetDateTime
import jakarta.transaction.Transactional
import java.util.concurrent.atomic.AtomicLong

@Service
class ScheduledPostGeneratorService(
    private val userService: UserService,
    private val postService: PostService,
) {
    private val faker = Faker()
    private val counter = AtomicLong(0)

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    fun generate() {
        val user = userService.getByLogin("student") ?: return
        val number = counter.incrementAndGet()

        postService.saveInitial(
            Post(
                id = 0,
                authorId = user.id,
                author = user.name,
                authorAvatar = user.avatar,
                content = "#$number " + faker.gameOfThrones().quote(),
                published = OffsetDateTime.now().toEpochSecond(),
                likedByMe = false,
                likes = 0,
            )
        )
    }
}