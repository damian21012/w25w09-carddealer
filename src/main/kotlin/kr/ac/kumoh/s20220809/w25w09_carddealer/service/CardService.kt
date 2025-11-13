package kr.ac.kumoh.s20220809.w25w09_carddealer.service

import kr.ac.kumoh.s20220809.w25w09_carddealer.model.Card
import kr.ac.kumoh.s20220809.w25w09_carddealer.model.Rank
import kr.ac.kumoh.s20220809.w25w09_carddealer.model.Suit
import kr.ac.kumoh.s20220809.w25w09_carddealer.repository.CardRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CardService(
    private val repository: CardRepository
) {
    fun getCards(): List<Card> = repository.cards

    fun dealCards(count: Int = 5) {
        val suits = Suit.entries
        val ranks = Rank.entries

        repository.deleteAll()

        val uniqueCards = mutableSetOf<Card>()
        while (uniqueCards.size < count) {
            val randomSuit = suits.random()
            val randomRank = ranks.random()
            uniqueCards.add(Card(randomRank, randomSuit))
        }

        // .ordinal은 enum 선언된 순서
        val sortedCards = uniqueCards.toList()
            .sortedWith(compareBy({ it.suit.ordinal }, { it.rank.value }))

        sortedCards.forEach { repository.save(it) }
    }
}