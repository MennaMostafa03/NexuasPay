package com.example.nexuspay.domain.model.response

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo
    var cardHolderName : String= "",
    @ColumnInfo
    var cardNumber : String= "",
    @ColumnInfo
    var expireDate : String= "",
    @ColumnInfo
    var cvv : String= "",
    @ColumnInfo
    var cardName: String= "",
    @ColumnInfo
    var cardType: String = ""
)
