"# Google-News-Kt" 

A sample project using News API. It fetches data from News API, saves them to the SQLite database. And shows data from the SQLite database. MVVM has been used as architecture.


MVVM + Kotlin + SQLite + Room + Retrofit + Dagger 2 -> Google News App


Google News Features:

- Consumed newsapi.org API instead of Google News API as it's no longer available.

- Used retrofit for making api calls.

- Stored these responses in SQLite database.

- Used Room persistence library to provide an abstraction layer over SQLite.

- Showed response data in a recycler view inside the app main page.

- Added Swipe Refresh to reload the contents from the screen.

- Recycler view data have faced from the Database and not from API.

- When clicked, it browse to the news site to show the news details.

- Browser have implemented inside the app.

- Browser is able to play videos in full screen. [Special case]

- Used the MVVM pattern with Kotlin and Android Architecture Components.



Source code: https://github.com/fruzelee/Google-News-Kt

APK: https://drive.google.com/file/d/1THzjTx6nDSMg0CYZjLFhr2qYFks4keDF/view?usp=sharing

App Demo Video: https://drive.google.com/file/d/165nSAbyYcpm_16tODmtVHcMIxVNbtsOP/view?usp=sharing

Screenshots: https://drive.google.com/drive/folders/1liHlIMQ_6l1G9LXKqYQg0tUHuJVLorGt



