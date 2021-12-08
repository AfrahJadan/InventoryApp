package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    //@Query that is a SQLite query to retrieve an item from the item table.
    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id:Int):Flow<Item>
    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getItems():Flow<List<Item>>

//When you call insert() from your Kotlin code, Room executes a SQL query to insert the entity into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)
    @Update
    suspend fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)
}

//NOTE:they should run on a separate thread. Make the function a suspend function,
// so that this function can be called from a coroutine.