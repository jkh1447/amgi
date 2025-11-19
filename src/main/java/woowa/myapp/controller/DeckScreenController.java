package woowa.myapp.controller;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckListPanel;
import woowa.myapp.view.MainFrame;

public class DeckScreenController {
    private DeckManager deckManager;
    private MainFrame mainFrame;

    public final String CREATE_DECK_MESSAGE = "이름을 입력해주세요.";

    public DeckScreenController(DeckManager deckManager, MainFrame mainFrame) {
        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
    }

}
