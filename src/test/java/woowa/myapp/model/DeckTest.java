package woowa.myapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드를 추가하면 메모리 상에서 즉시 리스트에 반영된다")
    void addCard_shouldUpdateInMemoryList() {
        Deck deck = new Deck("test-" + UUID.randomUUID());

        deck.addCard(new Card("front", "back"));

        assertEquals(1, deck.getCards().size());
        assertEquals("front", deck.getCards().get(0).getFront());
    }

    @Test
    @DisplayName("덱 데이터를 저장하면 파일이 생성되고 삭제하면 제거된다")
    void saveDeckData_createsFileAndDeleteRemovesIt() throws Exception {
        String name = "test-" + UUID.randomUUID();
        Deck deck = new Deck(name);
        deck.addCard(new Card("front", "back"));

        deck.saveDeckData();

        Path deckFile = Path.of("save", "decks", name + ".dat");
        assertTrue(Files.exists(deckFile));

        deck.deleteDeckData();
        assertFalse(Files.exists(deckFile));
    }
}

