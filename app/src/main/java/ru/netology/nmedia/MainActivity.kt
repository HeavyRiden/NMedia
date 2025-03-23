package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.numLogic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var countShare: Long = 9_999
        var countLikes: Long = 9

        val post = Post(
            id = 1,
            author = "Автор поста",
            content = "Содержимое поста",
            published = "22.03.2025",
            likeByMe = true
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            avatar.setImageResource(R.drawable.post_avatar_drawable)

            like.setImageResource(
                if (post.likeByMe) R.drawable.ic_liked_24
                else R.drawable.ic_like
            )
            likeCount.text = numLogic(countLikes)

            like.setOnClickListener {
                post.likeByMe = !post.likeByMe

                if (post.likeByMe) countLikes++
                else countLikes--

                likeCount.text = numLogic(countLikes)

                like.setImageResource(
                    if (post.likeByMe) R.drawable.ic_liked_24
                    else R.drawable.ic_like
                )
            }

            share.setImageResource(R.drawable.ic_share)
            shareCount.text = numLogic(countShare)
            share.setOnClickListener {
                countShare++
                shareCount.text = numLogic(countShare)
                viewsCount.text = numLogic(countShare)

            }

            views.setImageResource(R.drawable.ic_views)
            viewsCount.text = numLogic(countShare)
        }
    }
}