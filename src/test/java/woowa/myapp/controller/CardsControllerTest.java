package woowa.myapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;

class CardsControllerTest {

    private static class InMemoryDeck extends Deck {
        private boolean saved;

        InMemoryDeck(String name) {
            super(name);
        }

        @Override
        public void saveDeckData() {
            saved = true;
        }

        boolean isSaved() {
            return saved;
        }
    }

    @Test
    @DisplayName("쉬움 버튼 이벤트는 카드를 완료 처리하고 저장한다")
    void getEasyButtonEvent_marksCardAsLearned() {
        CardsController controller = new CardsController(null);
        InMemoryDeck deck = new InMemoryDeck("deck-" + UUID.randomUUID());
        Card card = new Card("f", "b");
        deck.getCards().add(card);
        List<Card> targetCards = new ArrayList<>(deck.getCards());

        controller.getEasyButtonEvent(targetCards, 0, deck);

        assertFalse(card.isTarget());
        assertEquals(0.0, card.getDifficulty());
        assertTrue(deck.isSaved());
    }

    @Test
    @DisplayName("보통 버튼 이벤트는 medium 카운트를 증가시키지만 target은 유지한다")
    void getMediumButtonEvent_incrementsMediumCount() {
        CardsController controller = new CardsController(null);
        InMemoryDeck deck = new InMemoryDeck("deck-" + UUID.randomUUID());
        Card card = new Card("front", "back");
        deck.getCards().add(card);
        List<Card> targetCards = new ArrayList<>(deck.getCards());

        controller.getMediumButtonEvent(targetCards, 0, deck);

        assertTrue(card.isTarget());
        assertEquals(1.0, card.getDifficulty()); // hard=0, medium=1
        assertTrue(deck.isSaved());
    }

    @Test
    @DisplayName("어려움 버튼 이벤트는 hard 카운트를 증가시킨다")
    void getHardButtonEvent_incrementsHardCount() {
        CardsController controller = new CardsController(null);
        InMemoryDeck deck = new InMemoryDeck("deck-" + UUID.randomUUID());
        Card card = new Card("front", "back");
        deck.getCards().add(card);
        List<Card> targetCards = new ArrayList<>(deck.getCards());

        controller.getHardButtonEvent(targetCards, 0, deck);

        assertTrue(card.isTarget());
        assertEquals(2.0, card.getDifficulty());
        assertTrue(deck.isSaved());
    }
}

