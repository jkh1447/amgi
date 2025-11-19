package woowa.myapp.view;

import woowa.myapp.controller.AddCardController;
import woowa.myapp.controller.CardsController;
import woowa.myapp.controller.DeckListController;
import woowa.myapp.controller.DeckScreenController;
import woowa.myapp.controller.DeckSettingController;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;

public class ViewFactory {

    public DeckListPanel getDeckListPanel(DeckManager deckManager, MainFrame mainFrame, DeckListController deckListController) {
        return new DeckListPanel(deckManager, mainFrame, deckListController);
    }

    public DeckScreenPanel getDeckScreenPanel(MainFrame mainFrame, DeckManager deckManager, DeckListPanel deckListPanel, DeckScreenController deckScreenController) {
        return new DeckScreenPanel(mainFrame, deckManager, deckListPanel, deckScreenController);
    }

    public CardsPanel getCardsPanel(Deck deck, DeckManager deckManager, MainFrame mainFrame, CardsController cardsController, MainController mainController) {
        return new CardsPanel(deck, deckManager, mainFrame, cardsController, mainController);
    }

    public AddCardPanel getAddCardPanel(DeckManager deckManager, MainFrame mainFrame, Deck deck, AddCardController addCardController) {
        return new AddCardPanel(deckManager, mainFrame, deck, addCardController);
    }

    public DeckSettingPanel getDeckSettingPanel(MainFrame mainFrame, DeckManager deckManager, Deck deck, DeckSettingController deckSettingController, MainController mainController) {
        return new DeckSettingPanel(mainFrame, deckManager, deck, deckSettingController, mainController);
    }

}
