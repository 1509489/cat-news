package com.sky.catnews.dto

import com.sky.catnews.models.*

fun News.toNewsDto(): NewsDto {
    return NewsDto(
        newsData = newsData.toNewsDataDto(),
        title = title
    )
}

fun List<NewsData>.toNewsDataDto(): List<NewsDataDto> {
    return map { newsData ->
        NewsDataDto(
            creationDate = newsData.creationDate.orEmpty(),
            headline = newsData.headline.orEmpty(),
            id = newsData.id.orEmpty(),
            modifiedDate = newsData.modifiedDate.orEmpty(),
            teaserImage = newsData.teaserImage?.toTeaserImageDto(),
            teaserText = newsData.teaserText.orEmpty(),
            type = newsData.type,
            url = newsData.url.orEmpty(),
            weblinkUrl = newsData.weblinkUrl.orEmpty()
        )
    }
}

fun TeaserImage.toTeaserImageDto(): TeaserImageDto {
    return TeaserImageDto(
        links = links.toImageLinksDto(),
        accessibilityText = accessibilityText
    )
}

fun ImageLinks.toImageLinksDto(): ImageLinksDto {
    return ImageLinksDto(
        url = url.toImageUrlDto()
    )
}

fun ImageUrl.toImageUrlDto(): ImageUrlDto {
    return ImageUrlDto(
        href = href,
        templated = templated,
        type = type
    )
}

/**
 * News Story Mapper
 */

fun NewsStory.toNewsStoryDto(): NewsStoryDto {
    return NewsStoryDto(
        contents = contents.toStoryContentDto(),
        creationDate = creationDate,
        headline = headline,
        heroImage = heroImage.toHeroImageDto(),
        id = id,
        modifiedDate = modifiedDate
    )
}

fun HeroImage.toHeroImageDto(): HeroImageDto {
    return HeroImageDto(
        accessibilityText = accessibilityText,
        imageUrl = imageUrl
    )
}

fun List<StoryContent>.toStoryContentDto(): List<StoryContentDto> {
    return map { content ->
        StoryContentDto(
            accessibilityText = content.accessibilityText.orEmpty(),
            text = content.text.orEmpty(),
            type = content.type,
            url = content.url.orEmpty()
        )
    }
}
