package com.sky.catnews.models

data class NewsStory(
    val contents: List<StoryContent>,
    val creationDate: String,
    val headline: String,
    val heroImage: HeroImage,
    val id: String,
    val modifiedDate: String
)

data class StoryContent(
    val accessibilityText: String,
    val text: String,
    val type: String,
    val url: String
)

data class HeroImage(
    val accessibilityText: String,
    val imageUrl: String
)