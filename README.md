# Pebble Hello World Companion App

This simple app works with my [Pebble Hello World app](https://github.com/rolandtritsch/pebble-watchface-tutorial). It allows to send a text to the Pebble app and will change the text that is displayed on the watch.

To build/install it you need to ...

* build/install the Pebble HW app on your watch
* install git and sbt (using macports or brew)
* clone this repo
* edit the code and update the Pebble app UUID with the UUID of your own Pebble app (in MainActivity.scala)
* run `adb devices` to check that your phone is connected to your laptop
* run `sbt install` to build and install the app on your phone (right now android-19 is required, you can change it to whatever you like)
* you should then be able to start the app on you phone and click on `Check it!` to see, if your Pebble is connected. If not, you have to start the Pebble Managment app on your phone and have to make sure your phone is connected to your Pebble
* last but not least, you can then type in a text and use the `Send it` button to send it to the watch (max length on the text is 5 chars)

The [blog post](http://blog.tritsch.org/2014/12/x-mas-project-1-pebble-hello-world-20.html) has a couple of screen shots in it.
