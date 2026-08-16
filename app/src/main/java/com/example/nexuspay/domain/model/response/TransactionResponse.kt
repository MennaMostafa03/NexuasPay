package com.example.nexuspay.domain.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Entity
@Serializable
data class TransactionResponse(
    @ColumnInfo
    val date: String? = null,

    @ColumnInfo
    val amount: Int? = null,

    @ColumnInfo
    @JsonNames("description", "title")
    val description: String? = null,

    @ColumnInfo
    val currency: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo
    val state: String? = null,

    @ColumnInfo
    val time: String? = null,

    @ColumnInfo
    val type: String? = null,

)