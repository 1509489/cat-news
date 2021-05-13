package com.sky.catnews

import com.sky.catnews.models.*

val newsTestData = News(
    title = "Cat News",
    newsData = listOf(
        NewsData(
            headline = "Headline 1",
            creationDate = "",
            modifiedDate = "",
            id = "",
            teaserText = "Text 1",
            teaserImage = null,
            type = "",
            url = "",
            weblinkUrl = ""
        ),
        NewsData(
            headline = "Headline 2",
            creationDate = "",
            modifiedDate = "",
            id = "",
            teaserText = "Text 2",
            teaserImage = null,
            type = "",
            url = "",
            weblinkUrl = ""
        ),
        NewsData(
            headline = "Headline 3",
            creationDate = "",
            modifiedDate = "",
            id = "",
            teaserText = "Text 3",
            teaserImage = null,
            type = "",
            url = "",
            weblinkUrl = ""
        )
    )
)

val newsStoryTestData = NewsStory(
    headline = "Headline 1",
    heroImage = HeroImage(accessibilityText = "", imageUrl = ""),
    creationDate = "",
    modifiedDate = "",
    id = "",
    contents = listOf(
        StoryContent(
            accessibilityText = null,
            text = "some story 1",
            type = "paragraph",
            url = null
        ),
        StoryContent(
            accessibilityText = null,
            text = null, type = "image",
            url = "some url"
        ),
        StoryContent(
            accessibilityText = null,
            text = "some story 2",
            type = "paragraph",
            url = null
        )
    )
)
