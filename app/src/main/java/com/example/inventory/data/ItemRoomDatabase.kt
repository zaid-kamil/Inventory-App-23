package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class, Customer::class],
    version = 1,
    exportSchema = false
)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ItemRoomDatabase::class.java,
                "my_database"
            ).fallbackToDestructiveMigration().build()
            INSTANCE = instance
            return instance
        }
    }
}