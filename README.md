# Build an Android app using the GitHub API (hard)
##### Build an Android app using the GitHub API

1) Create main screen with list of users from this request [https://api.github.com/users](https://api.github.com/users)
   List item have to display such data:
    - avatar
    - login

   After click on a user it has to transfer you to screen with user's repositories
2) Create user details screen with user’s repositories from this request [https://api.github.com/users/{login}/repos](https://api.github.com/users/{login}/repos)
   List item have to display such data:
    - name
    - updated_at
    - stargazers_count
    - language

   After click on a repository it has to transfer you to the repository at github.com
    3) Save ll the data in database
       Data have to be shown in this order: loader → data from database → data from API

Tech stack:

- Jetpack Compose
- Kotlin
- MVVM Architecture
- Kotlin coroutines
- Retrofit
- Dagger 2 or Dagger Hilt
- Single Activity Design Pattern
- Clean Architecture
## Project Structure


Dillinger uses a number of open source projects to work properly:

- ```components``` Contain all custom reusable composable
- ```data``` Contain all model, data source, repository and Room
    - ```model``` Contain entities
    -  ```repository``` Contain repositories
- ```di``` Contain all module for Dependency Injection
- ```navigation```  Contain NavGraph and Screen .
- ```network```  Retrofit config
- ```repository``` Contain repository
- ```ui.theme``` theme of app
-  ```utils``` Contain extension
- ```views``` the views
- ```vm``` the viewmodel

## Release
You can find the apk  [here](https://github.com/zack2/android-app-github-api/tree/main/app/release)