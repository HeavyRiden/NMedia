package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.numLogic

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                
            }
            binding.like.setImageResource(
                if (post.likeByMe) R.drawable.ic_liked_24
                else R.drawable.ic_like
            )
            binding.likeCount.text = numLogic(post.countLikes)
            binding.avatar.setImageResource(R.drawable.post_avatar_drawable)

            binding.share.setImageResource(R.drawable.ic_share)
            binding.shareCount.text = numLogic(post.countShare)

            binding.views.setImageResource(R.drawable.ic_views)
            binding.viewsCount.text = numLogic(post.countViews)

            binding.like.setOnClickListener() {
                viewModel.like()
            }

            binding.share.setOnClickListener() {
                viewModel.share()
            }

            binding.views.setOnClickListener() {
                viewModel.view()
            }
        }
    }
}