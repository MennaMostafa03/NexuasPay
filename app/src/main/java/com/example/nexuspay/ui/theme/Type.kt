import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Milky
import com.example.nexuspay.ui.theme.White


val AppTypography = Typography(

    displayLarge = TextStyle(
        color = LightBlue,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),

    displayMedium = TextStyle(
        color = Milky,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),

    titleSmall = TextStyle(
        color = LightGray,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),

    bodyLarge = TextStyle(
        color = White,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    ),

    bodyMedium = TextStyle(
        color = Milky,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),

    bodySmall = TextStyle(
        color = LightGray,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)