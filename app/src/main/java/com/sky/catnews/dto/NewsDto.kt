package com.sky.catnews.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("data")
    val newsData: List<NewsDataDto>,
    val title: String
)

data class NewsDataDto(
    val creationDate: String,
    val headline: String,
    val id: String,
    val modifiedDate: String,
    val teaserImage: TeaserImageDto?,
    val teaserText: String,
    val type: String,
    val url: String,
    val weblinkUrl: String
)

data class TeaserImageDto(
    @SerializedName("_links")
    val links: ImageLinksDto,
    val accessibilityText: String
)

data class ImageLinksDto(
    val url: ImageUrlDto
)

data class ImageUrlDto(
    val href: String,
    val templated: Boolean,
    val type: String
)
