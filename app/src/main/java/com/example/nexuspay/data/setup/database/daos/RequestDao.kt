package com.example.nexuspay.data.setup.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nexuspay.domain.model.request.RequestEntity

@Dao
interface RequestDao {

    @Insert
    suspend fun saveRequest(entity : RequestEntity) : Long

    @Delete
    suspend fun deleteRequest(entity: RequestEntity)

    @Query("Select * From RequestEntity where id == :transactionId")
    suspend fun getRequestById(transactionId: Int) : RequestEntity

}