package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onView(post: Post) {
                viewModel.viewById(post.id)
            }

        })


        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.editionText.setText(it.content)
                binding.editionGroup.visibility = View.VISIBLE
            }
            else
                binding.editionGroup.visibility = View.INVISIBLE
        }

        binding.revertEdit.setOnClickListener {
            binding.content.setText("")
            binding.content.clearFocus()
            binding.editionGroup.visibility = View.INVISIBLE
            AndroidUtils.hideKeyboard(it)
        }

        binding.list.adapter = adapter
        viewModel.edited.observe(this) {
            if(it.id != 0L) {
                binding.content.setText(it.content)
                binding.content.requestFocus()
            }
        }
        binding.add.setOnClickListener {
            val text = binding.content.text.toString()
            if(text.isEmpty()) {
                Toast.makeText(this, R.string.empty_text, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            viewModel.changeContentAndSave(text)
            binding.content.setText("")
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
        }
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if(newPost) binding.list.scrollToPosition(0)
            }
            }
        }
    }
