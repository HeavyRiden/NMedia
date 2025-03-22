package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Автор поста",
            content = "Соедржимое поста",
            published = "22.03.2025",
            likeByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            avatar.setImageResource(R.drawable.post_avatar_drawable)

            var shareCountLong: Long = 0

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
            shareCount.text = shareCountLong.toString()
            share.setOnClickListener {
                shareCountLong ++
                shareCount.text = shareCountLong.toString()

            }

            views.setImageResource(R.drawable.ic_views)
            viewsCount.text = "0"
        }
    }
}