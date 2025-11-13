package kr.ac.kumoh.s20220809.w25w09_carddealer.model

enum class Suit(val text: String) {
    SPADES("spades"),
    DIAMONDS("diamonds"),
    HEARTS("hearts"),
    CLUBS("clubs")
}

enum class Rank(val value: Int, val text: String) {
    ACE(14, "ace"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "jack"),
    QUEEN(12, "queen"),
    KING(13, "king")
}

data class Card(
    val rank: Rank,
    val suit: Suit,
) {
    val imageName: String
        get() {
            val name = "${rank.text}_of_${suit.text}"

            return if (rank in setOf(Rank.JACK, Rank.QUEEN, Rank.KING)) {
                "${name}2.png"
            } else {
                "${name}.png"
            }
        }
}
