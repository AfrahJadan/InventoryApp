package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat


//1-This class will represent a database entity in your app
//2-Convert the Item class to a data class
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "name")
    val itemName:String,
    @ColumnInfo(name = "price")
    val itemPrice:Double,
    @ColumnInfo(name = "quantity")
    val quantityInStock:Int
)
fun Item.getFormattedPrice():String = NumberFormat.getCurrencyInstance().format(itemPrice)

//primary constructor: () part of the class header