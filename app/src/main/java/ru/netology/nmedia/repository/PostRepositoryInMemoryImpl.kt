package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImp : PostRepository {

    private var post = Post(
        id = 1,
        author = "Автор поста",
        content = "Содержимое поста",
        published = "22.03.2025",
        likeByMe = false,
        countLikes = 0,
        countShare = 9_999,
        countViews = 10_000
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data


    override fun like() {
        post = post.copy(
            likeByMe = !post.likeByMe,
            countLikes = if (post.likeByMe) post.countLikes - 1 else post.countLikes + 1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(countShare = post.countShare +1)
        data.value = post
    }

    override fun view() {
        post = post.copy(countViews = post.countViews +1)
        data.value = post
    }
}