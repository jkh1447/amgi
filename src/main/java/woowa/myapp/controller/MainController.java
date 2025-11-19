package woowa.myapp.controller;

import javax.swing.JPanel;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.MainFrame;
import woowa.myapp.view.ViewFactory;

public class MainController {
    private MainFrame mainFrame;
    private DeckManager deckManager;
    private JPanel deckScreenPanel;
    private DeckListPanel deckListPanel;

    private DeckScreenController deckScreenController;
    private DeckListController deckListController;
    private AddCardController addCardController;

    private ViewFactory viewFactory;

    public MainController() {
        init();

    }

    public void init() {
        this.deckManager = new DeckManager();
        this.mainFrame = new MainFrame(deckManager);
        this.viewFactory = new ViewFactory();

        // controller
        this.deckScreenController = new DeckScreenController(deckManager, mainFrame);
        this.addCardController = new AddCardController(deckManager, this);
        this.deckListController = new DeckListController(mainFrame, deckManager, this,
                addCardController, viewFactory);
    }

    public void getMainButtonEvent() {
        mainFrame.setPanel(deckScreenPanel);
    }

}
