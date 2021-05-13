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
import com.sky.catnews.common.CommonUtils
import com.sky.catnews.dto.NewsDataDto

class NewsListAdapter : ListAdapter<NewsDataDto, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsDataDto>() {
            override fun areItemsTheSame(oldItem: NewsDataDto, newItem: NewsDataDto): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsDataDto, newItem: NewsDataDto): Boolean {
                return oldItem == newItem
            }
        }

        private const val HEADER_VIEW_TYPE = 0
        private const val STORY_VIEW_TYPE = 1
        private const val ADVERT_VIEW_TYPE = 2
    }

    var itemViewClickListener: ((data: NewsDataDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE -> StoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list_header,
                    parent,
                    false
                )
            )
            ADVERT_VIEW_TYPE -> AdvertViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_advert,
                    parent,
                    false
                )
            )
            else -> StoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER_VIEW_TYPE -> (holder as StoryViewHolder).bind(
                getItem(position),
                itemViewClickListener
            )
            ADVERT_VIEW_TYPE -> (holder as AdvertViewHolder).bind(getItem(position))
            else -> (holder as StoryViewHolder).bind(getItem(position), itemViewClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> HEADER_VIEW_TYPE
            getItem(position).type == "advert" -> ADVERT_VIEW_TYPE
            else -> STORY_VIEW_TYPE
        }
    }

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val storyImage: ImageView = itemView.findViewById(R.id.ivStoryImage)
        private val headlineText: TextView = itemView.findViewById(R.id.tvHeadline)
        private val teaserText: TextView = itemView.findViewById(R.id.tvTeaserText)
        private val durationText: TextView = itemView.findViewById(R.id.tvDuration)

        fun bind(data: NewsDataDto, clickListener: ((data: NewsDataDto) -> Unit)?) {
            headlineText.text = data.headline
            teaserText.text = data.teaserText
            durationText.text = CommonUtils.getFormattedDate(data.modifiedDate)
            data.teaserImage?.let {
                storyImage.contentDescription = it.accessibilityText
                Glide.with(itemView.context)
                    .load(it.links.url.href)
                    .centerCrop()
                    .placeholder(R.drawable.nyan_cat)
                    .into(storyImage)

            }
            itemView.setOnClickListener { clickListener?.invoke(data) }
        }
    }

    inner class AdvertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val advertText: TextView = itemView.findViewById(R.id.tvAdvert)

        fun bind(data: NewsDataDto) {
            // Set advert data here
            advertText.text = data.url
        }
    }
}
