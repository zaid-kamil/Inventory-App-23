package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

}