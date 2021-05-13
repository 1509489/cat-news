# cat-news
The implementation of this app focuse mostly on the architecture. 
I didn't spent much on the UI designs so it might be lacking a bit. The text sizes and view sizes, I was eye balling based on the wireframe.

# How to Run

I built the app using multiple product flavors
- Mock: This is where the implementation for fetching the dummy data without backend is implemented. You have to be in the mock flavor to be able to see data on the screen since there is no proper backend communication yet.
- Prod: This flavor is used for fetching real data from backend. The base url for the backend needs to be defined in the `buildConfigField` in build.gradle

# Possible Improvements
- Add in memomery cache implementation to cache data for some time when data is fetched from the api to prevent making a call to the backend every time you switch screens.
- Work on the UI some more
