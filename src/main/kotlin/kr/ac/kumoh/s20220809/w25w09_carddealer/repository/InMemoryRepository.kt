package kr.ac.kumoh.s20220809.w25w09_carddealer.repository

import kr.ac.kumoh.s20220809.w25w09_carddealer.model.Card
import org.springframework.stereotype.Repository

@Repository
class InMemoryRepository : CardRepository {
    private val _cards: MutableList<Card> = mutableListOf()

    override val cards
        get() = _cards.toList()

    override fun save(card: Card) {
        if (!_cards.contains(card)) {
            _cards.add(card)
        }
    }

    override fun deleteAll() {
        _cards.clear()
    }
}