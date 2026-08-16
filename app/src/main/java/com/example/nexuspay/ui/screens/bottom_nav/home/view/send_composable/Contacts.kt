package com.example.nexuspay.ui.screens.bottom_nav.home.view.send_composable

import AppTypography
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.Milky

@Composable
fun Contacts(
    contacts: List<CurrentUserItem?>,
    onItemClicked : (item : CurrentUserItem?) -> Unit
) {
    val selectedItem = remember {  mutableStateOf(CurrentUserItem())}
    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(contacts, key = {it?.id!!}){ item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable{
                    onItemClicked(item)
                    Log.e("contacts", "${item?.identifier}")
                }
            )
            {
                Spacer(Modifier.height(4.dp))
                AsyncImage(
                    model = item?.avatar ?: "" ,
                    contentDescription = "",
                    modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(50.dp))
                    ,
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = item?.name!!.substringBefore(" "),
                    style = AppTypography.titleSmall.copy(color = Milky),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}