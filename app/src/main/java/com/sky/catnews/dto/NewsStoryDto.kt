package com.sky.catnews.dto

data class NewsStoryDto(
    val contents: List<StoryContentDto>,
    val creationDate: String,
    val headline: String,
    val heroImage: HeroImageDto,
    val id: String,
    val modifiedDate: String
)

data class StoryContentDto(
    val accessibilityText: String,
    val text: String,
    val type: String,
    val url: String
)

data class HeroImageDto(
    val accessibilityText: String,
    val imageUrl: String
)
