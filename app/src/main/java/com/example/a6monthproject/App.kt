package com.example.a6monthproject

import android.app.Application
import androidx.room.Room
import com.example.a6monthproject.data.db.AppDataBase

class App:Application() {
    companion object{
        lateinit var db : AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "DB"
        ).allowMainThreadQueries().build()
    }
}