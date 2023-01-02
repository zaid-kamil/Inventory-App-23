package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customers WHERE id = :id")
    fun getCustomer(id: Int): Flow<Customer>

    @Query("SELECT * FROM customers WHERE name = :name")
    fun getCustomerByName(name: String): Flow<Customer>

    @Query("SELECT * FROM customers WHERE email = :email")
    fun getCustomerByEmail(email: String): Flow<Customer>

}