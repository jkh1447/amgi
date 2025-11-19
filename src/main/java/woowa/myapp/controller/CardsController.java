package woowa.myapp.controller;

import java.util.List;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.CardsPanel;

public class CardsController {
    private DeckManager deckManager;

    public CardsController(DeckManager deckManager) {
        this.deckManager = deckManager;
    }

    public void getEasyButtonEvent(List<Card> targetCards, int currentIndex, Deck deck) {
        if (targetCards.isEmpty()) {
            return;
        }
        Card currentCard = targetCards.get(currentIndex);
        currentCard.setTarget(false); // 쉬움이면 다시 안나오게
        currentCard.easyCountup();
        deck.saveDeckData();
    }

    public void getMediumButtonEvent(List<Card> targetCards, int currentIndex, Deck deck) {
        if (targetCards.isEmpty()) {
            return;
        }
        Card currentCard = targetCards.get(currentIndex);
        currentCard.mediumCountup();
        deck.saveDeckData();
    }

    public void getHardButtonEvent(List<Card> targetCards, int currentIndex, Deck deck) {
        if (targetCards.isEmpty()) {
            return;
        }
        Card currentCard = targetCards.get(currentIndex);
        currentCard.hardCountup();
        deck.saveDeckData();
    }


}
