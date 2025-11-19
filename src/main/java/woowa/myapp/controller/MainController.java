package woowa.myapp.controller;

import javax.swing.JPanel;
import woowa.myapp.PresetDeckGenerator;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.DeckScreenPanel;
import woowa.myapp.view.MainFrame;
import woowa.myapp.view.ViewFactory;

public class MainController {

    private MainFrame mainFrame;
    private DeckManager deckManager;
    private JPanel deckScreenPanel;
    private DeckListPanel deckListPanel;

    private DeckScreenController deckScreenController;
    private DeckListController deckListController;
    private DeckSettingController deckSettingController;
    private AddCardController addCardController;
    private CardsController cardsController;

    private ViewFactory viewFactory;

    public MainController() {
        init();
        mainFrame.setPanel(deckScreenPanel);
    }

    public void init() {
        this.deckManager = new DeckManager();
        this.mainFrame = new MainFrame(deckManager);
        this.viewFactory = new ViewFactory();

        // controller
        this.deckScreenController = new DeckScreenController(deckManager, mainFrame);
        this.deckSettingController = new DeckSettingController(deckManager);
        this.addCardController = new AddCardController(deckManager, this);
        this.cardsController = new CardsController(deckManager);
        this.deckListController = new DeckListController(mainFrame, deckManager, deckSettingController, this,
                addCardController, cardsController, viewFactory);

        // panel
        this.deckListPanel = viewFactory.getDeckListPanel(deckManager, mainFrame, deckListController);
        this.deckScreenPanel = viewFactory.getDeckScreenPanel(mainFrame, deckManager, deckListPanel, deckScreenController);

//        PresetDeckGenerator.addEssentialVocabularyDeck(deckManager);
//        PresetDeckGenerator.addChineseDeck(deckManager);
//        PresetDeckGenerator.addJapaneseDeck(deckManager);
//        PresetDeckGenerator.addITDeck(deckManager);

    }

    public void getMainButtonEvent() {
        mainFrame.setPanel(deckScreenPanel);
    }



}
