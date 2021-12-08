package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//^version^Whenever you change the schema of the database table, you'll have to increase the version number.
//^exportSchema^ as not to keep schema version history backups.

@Database(entities = [Item::class], version = 1 , exportSchema = false)
abstract class ItemRoomDatabase:RoomDatabase() {

    //declare an abstract function that returns the ItemDao.
    abstract fun itemDao():ItemDao

    companion object{
        //@Volatile@ It means that changes made by one thread to INSTANCE are visible to all other threads immediately.
        @Volatile
        private var INSTANCE:ItemRoomDatabase? = null
        fun getDatabase(context: Context):ItemRoomDatabase{

            // synchronized makes sure the database only gets initialized once.
            return INSTANCE ?: synchronized(this){
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
