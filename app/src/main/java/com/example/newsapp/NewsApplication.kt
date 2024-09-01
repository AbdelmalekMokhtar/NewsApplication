package com.example.newsapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any dependencies or configurations here
        Log.d(TAG, "Application created")
    }
    companion object{
        const val TAG = "NewsApplication"
    }

}
