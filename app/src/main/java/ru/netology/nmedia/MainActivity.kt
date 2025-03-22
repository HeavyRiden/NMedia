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
        var countLong: Long = 9_991_999

        val post = Post(
            id = 1,
            author = "Автор поста",
            content = "Содержимое поста",
            published = "22.03.2025",
            likeByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            avatar.setImageResource(R.drawable.post_avatar_drawable)



            like.setImageResource(R.drawable.ic_like)
            likeCount.text = "0"
            like.setOnClickListener {
                post.likeByMe = !post.likeByMe

                if(post.likeByMe) likeCount.text = "1"
                else likeCount.text = "0"

                like.setImageResource(
                    if (post.likeByMe) R.drawable.ic_liked_24
                    else R.drawable.ic_like
                )
            }

            share.setImageResource(R.drawable.ic_share)
            shareCount.text = numLogic(countLong)
            share.setOnClickListener {
                countLong ++
                shareCount.text = numLogic(countLong)
                viewsCount.text = numLogic(countLong)

            }

            views.setImageResource(R.drawable.ic_views)
            viewsCount.text = numLogic(countLong)
        }
    }
}