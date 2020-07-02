package by.service.tp.OneCBase

import android.app.Application
import android.database.sqlite.SQLiteDatabase

class MyApp(): Application() {

    companion object{
        lateinit var myDatabase: MyDbHelper
        lateinit var instance: MyApp
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        myDatabase = MyDbHelper(this)
       // myDatabase.createDB()
    }
}