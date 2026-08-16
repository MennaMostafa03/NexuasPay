package com.example.nexuspay.domain.model.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nexuspay.data.setup.api.USER_IDENTIFIER


//  data to send money request put in db
@Entity
data class RequestEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int ? = null,

    @ColumnInfo
    var receiverIdentifier: String? = null,

    @ColumnInfo
    var amount: Int? = null,

    @ColumnInfo
    var senderIdentifier: String = USER_IDENTIFIER,

    @ColumnInfo
    var currency: String? = null,

    @ColumnInfo
    var title: String? = null,

    @ColumnInfo
    var status : String = "pending"
)
