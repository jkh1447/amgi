package woowa.myapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 시 기본 상태가 올바르게 초기화된다")
    void card_initial_state_is_correct() {
        Card card = new Card("front", "back");

        assertEquals("front", card.getFront());
        assertEquals("back", card.getBack());
        assertTrue(card.isTarget());
        assertEquals(0.0, card.getDifficulty());
    }

    @Test
    @DisplayName("난이도 카운트가 증가하면 difficulty 계산이 반영된다")
    void difficulty_is_calculated_from_counters() {
        Card card = new Card("f", "b");

        card.easyCountup();
        card.mediumCountup();
        card.mediumCountup();
        card.hardCountup();

        assertEquals(1 * 2 + 2, card.getDifficulty());
    }
}

