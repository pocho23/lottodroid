Lottodroid
=========

Spanish lottery android application ([Google Play link](https://play.google.com/store/apps/details?id=com.androidsx.lottodroid)). 

This is the first application we published back in 2009, for the version 1.5 of Android. YAY!

### Client: android application

It provides up-to-date information about the following lotteries: Euromillón, Bonoloto, Once, 7/39 de la Once, Cuponazo de la Once, Quiniela, Primitiva, Gordo Primitiva, Lotería Nacional, Quinigol, Lototurf, Quintuple plus, Loto Cataluña 6/49.

### Server: JSON API

The first version of Lottodroid uses an old-style JSON notreallyRESTful API, fetching daily all  the lottery information from the official API of the Spanish government ([Hacienda y administraciones públicas](http://www.minhap.gob.es/en-GB/Paginas/Home.aspx)) and saving it in a MySQL database.

The backend is done in PHP and it uses a custom MVC mini-framework developed in-house.

Later in 2011, we partnered with [Lotoluck](http://lotoluck.com/) to get the lottery information from them, not using anymore our API.

## Screenshots

[![img1](https://github.com/androidsx/lottodroid/raw/master/img/screenshot1.png)]()
[![img2](https://github.com/androidsx/lottodroid/raw/master/img/screenshot2.png)]()

## Video

[![ScreenShot](https://github.com/androidsx/lottodroid/raw/master/img/youtube.png)](http://www.youtube.com/watch?v=AwKkB9oBpPU)


## Contributors

The first version of 2009 was developed by [espinchi](github.com/espinchi) and [ompemi](github.com/ompemi), and in 2011 [hudomjo](https://github.com/hudomju) did a refactoring using Lotoluck as the lottery provider.
