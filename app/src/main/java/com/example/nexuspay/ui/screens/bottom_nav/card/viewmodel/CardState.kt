import com.example.nexuspay.domain.model.response.CardEntity

data class CardState(
    var card: List<CardEntity> = emptyList(),
    var cardError: String? = null,
    var formatedExpireDate : String?= null,
    var cardEntity: CardEntity = CardEntity(),
    var isLoading : Boolean = false
)