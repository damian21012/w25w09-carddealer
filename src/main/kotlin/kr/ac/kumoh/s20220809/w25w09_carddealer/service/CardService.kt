package kr.ac.kumoh.s20220809.w25w09_carddealer.service

import kr.ac.kumoh.s20220809.w25w09_carddealer.model.Card
import kr.ac.kumoh.s20220809.w25w09_carddealer.repository.CardRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CardService(
    private val repository: CardRepository
) {
    fun getCards(): List<Card> = repository.cards

    fun dealCards(count: Int = 5) {
        val suits = arrayOf("spades", "diamonds", "hearts", "clubs")
        val ranks = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace")

        //5장 전부 삭제 빈리스트
        repository.deleteAll()

        //set은 같은걸 못가지는
        val uniqueCards = mutableSetOf<Card>()
        while (uniqueCards.size < count) {
            val randomSuit = suits[Random.nextInt(suits.size)]
            val randomRank = ranks[Random.nextInt(ranks.size)]
            uniqueCards.add(Card(randomRank, randomSuit))
        }

        val sortedCards = uniqueCards.toList()
            .sortedWith(compareBy({ suits.indexOf(it.suit) }, { ranks.indexOf(it.rank) }))

        sortedCards.forEach { repository.save(it) }
    }
}