# Qats: The quiz game with cat pictures, and sometimes cat questions.##
## PAUSE
I have currently paused this project, because my M1 mac is being annoyingly slow (unusable) and I don't like using Android Studio on my Ubuntu machine. I hope Android Studio optimized for M1 Silicon comes out soon.

## Getting started
- `git clone git@github.com:ben-xD/Qats.git`
- `studio android` or `studio.sh android` or open with Android Studio **Arctic Fox 2020.3.1 Canary 5** or newer.
- Open with Android Studio, and run

## Technology
This repo uses relatively new android/ kotlin techniques:
- Coroutines. I previously used RxJava, including Flowables/ backpressure and concurrency. In Swift, I literally use semaphores and callbacks. Both Coroutines and RxJava are better, and have their place.
- Hilt. I previously used Dagger, refactoring the .java classes in .kt ones and adding a feature module to a mostly monolith app.
- Single activity app/ Fragments.
- Kotlin DSL for Gradle & :buildSrc module. Groovy lets you do the same thing multiple ways. That's bad. This is why The Zen of Python has: "There should be one-- and preferably only one --obvious way to do it.". The additional autocomplete was interesting!
- Navigation: using the Jetpack Navigation Component
- Not yet: Jetpack Compose