package com.example.nexuspay.domain.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


// to get my data
@Entity
@Serializable
data class UserResponse(

    @PrimaryKey
    val identifier: String?,
    @ColumnInfo
    val balance: Int? = null,
    @ColumnInfo
    val name: String? = null,
    @ColumnInfo
    val currency: String? = null,
    @ColumnInfo
    val avatar: String? = null,
    )
