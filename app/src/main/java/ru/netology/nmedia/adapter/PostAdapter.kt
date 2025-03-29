package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.numLogic

typealias OnLikeListener = (Post) -> Unit
typealias OnShareListener = (Post) -> Unit
typealias OnViewListener = (Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onViewListener: OnViewListener
) : RecyclerView.Adapter<PostViewHolder>() {

    var list: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, onLikeListener, onShareListener, onViewListener)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onViewListener: OnViewListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) = with(binding) {
        author.text = post.author
        published.text = post.published
        content.text = post.content
        like.setImageResource(
            if (post.likedByMe) R.drawable.ic_liked_24
            else R.drawable.ic_like
        )

        likeCount.text = numLogic(post.countLikes)
        avatar.setImageResource(R.drawable.post_avatar_drawable)

        share.setImageResource(R.drawable.ic_share)
        shareCount.text = numLogic(post.countShare)

        views.setImageResource(R.drawable.ic_views)
        viewsCount.text = numLogic(post.countViews)

        like.setOnClickListener() {
            onLikeListener(post)
        }

        share.setOnClickListener() {
            onShareListener(post)
        }

        views.setOnClickListener() {
            onViewListener(post)
        }
    }
}
