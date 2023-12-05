package com.example.catatanku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catatanku.dao.CatatanDao
import com.example.catatanku.entities.Catatan
import com.example.catatanku.ui.HomeFragment


@Database(entities = arrayOf(Catatan::class), version = 2)
abstract class CatatanDatabase : RoomDatabase() {
    abstract fun dao() : CatatanDao

    companion object{
        private const val DB_NAME = "databse.db"
        @Volatile
        private var instance: CatatanDatabase?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CatatanDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }



}