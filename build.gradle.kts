import buildSrc.src.main.java.DaggerHilt
import buildSrc.src.main.java.Room
import buildSrc.src.main.java.Versions

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("androidx.room") version "2.6.1" apply false
//    id(DaggerHilt.hiltRoot) version Versions.dagHilt apply false
//    id(Room.roomRoot) version Versions.room apply false



}