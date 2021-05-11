package com.sky.catnews.models

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("data")
    val newsData: List<NewsData>,
    val title: String
)

data class NewsData(
    val creationDate: String?,
    val headline: String?,
    val id: String?,
    val modifiedDate: String?,
    val teaserImage: TeaserImage?,
    val teaserText: String?,
    val type: String,
    val url: String?,
    val weblinkUrl: String?
)

data class TeaserImage(
    @SerializedName("_links")
    val links: ImageLinks,
    val accessibilityText: String
)

data class ImageLinks(
    val url: ImageUrl
)

data class ImageUrl(
    val href: String,
    val templated: Boolean,
    val type: String
)