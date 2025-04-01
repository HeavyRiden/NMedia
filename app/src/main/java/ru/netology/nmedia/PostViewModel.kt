package ru.netology.nmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImp

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    countLikes = 0,
    likedByMe = false,
    countShare = 0,
    countViews = 0,
)

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImp()

    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun viewById(id: Long) = repository.viewById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContentAndSave(text: String) {
        edited.value?.let {
            if(it.content != text) {
                repository.save(it.copy(content = text))
            }
        }
        edited.value = empty
    }

    fun reverseEdit() {
        edited.value = empty
    }

}