package com.sky.catnews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sky.catnews.R
import com.sky.catnews.dto.StoryContentDto

class NewsStoryAdapter : ListAdapter<StoryContentDto, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryContentDto>() {
            override fun areItemsTheSame(
                oldItem: StoryContentDto,
                newItem: StoryContentDto
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: StoryContentDto,
                newItem: StoryContentDto
            ): Boolean {
                return oldItem == newItem
            }
        }

        private const val STORY_PARAGRAPH = 0
        private const val STORY_IMAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            STORY_PARAGRAPH -> ParagraphViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.story_paragraph,
                    parent,
                    false
                )
            )
            else -> ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.story_image,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STORY_PARAGRAPH -> (holder as ParagraphViewHolder).bind(getItem(position))
            else -> (holder as ImageViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            "paragraph" -> STORY_PARAGRAPH
            else -> STORY_IMAGE
        }
    }

    inner class ParagraphViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val paragraphText: TextView = itemView.findViewById(R.id.tvParagraph)

        fun bind(data: StoryContentDto) {
            paragraphText.text = data.text
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.ivStoryImage)

        fun bind(data: StoryContentDto) {
            imageView.apply {
                Glide.with(context)
                    .load(data.url)
                    .placeholder(R.drawable.nyan_cat)
                    .centerCrop()
                    .into(this)
                contentDescription = data.accessibilityText
            }
        }
    }
}
