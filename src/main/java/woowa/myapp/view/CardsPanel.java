package woowa.myapp.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import woowa.myapp.controller.CardsController;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.DeckManager;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;

public class CardsPanel extends JPanel {

    private CardsController cardsController;
    private MainController mainController;

    private MainFrame mainFrame;
    private DeckManager deckManager;

    private List<Card> targetCards;

    private JLabel frontLabel;
    private JLabel backLabel;

    private JScrollPane frontScrollPane;
    private JScrollPane backScrollPane;

    private JPanel cardPanel;      // front/back 패널 묶음
    private CardLayout cardLayout;  // front/back 전환용

    private JButton showAnswerButton;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton mainButton;

    private JPanel buttonPanel;

    private final String FONT = "SansSerif";
    private int currentIndex = 0;
    private final String TARGET_CARD_EMPTY_MESSAGE = "모든 카드를 다 외웠어요!";

    public CardsPanel(Deck deck, DeckManager deckManager, MainFrame mainFrame,
                      CardsController cardsController, MainController mainController) {

        this.deckManager = deckManager;
        this.mainFrame = mainFrame;
        this.cardsController = cardsController;
        this.mainController = mainController;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        targetCards = deck.getCards().stream()
                .filter(Card::isTarget)
                .collect(Collectors.toList());

        frontLabel = new JLabel("", SwingConstants.CENTER);
        frontLabel.setFont(new Font(FONT, Font.BOLD, 22));
        frontLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frontScrollPane = new JScrollPane(frontLabel);
        frontScrollPane.setBorder(null);
        frontScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        backLabel = new JLabel("", SwingConstants.CENTER);
        backLabel.setFont(new Font(FONT, Font.PLAIN, 18));
        backLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backScrollPane = new JScrollPane(backLabel);
        backScrollPane.setBorder(null);
        backScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(frontScrollPane, "FRONT");
        cardPanel.add(backScrollPane, "BACK");

        add(cardPanel, BorderLayout.CENTER);

        showAnswerButton = new JButton("정답 보기");
        showAnswerButton.setFont(new Font(FONT, Font.BOLD, 16));
        addShowAnswerButtonEvent(showAnswerButton);

        mainButton = new JButton("메인으로");
        mainButton.setFont(new Font(FONT, Font.BOLD, 16));
        addMainButtonEvent(mainButton);

        easyButton = new JButton("쉬움");
        easyButton.setFont(new Font(FONT, Font.BOLD, 16));
        addEasyButtonEvent(easyButton, deck);

        mediumButton = new JButton("보통");
        mediumButton.setFont(new Font(FONT, Font.BOLD, 16));
        addMediumButtonEvent(mediumButton, deck);

        hardButton = new JButton("어려움");
        hardButton.setFont(new Font(FONT, Font.BOLD, 16));
        addHardButtonEvent(hardButton, deck);

        // 버튼 패널
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.add(mainButton);
        if (!targetCards.isEmpty()) {
            buttonPanel.add(showAnswerButton);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        // 첫 카드 표시
        showCurrentCard();
    }


    private void onShowAnswer() {
        cardLayout.show(cardPanel, "BACK"); // back 화면으로 전환

        Card card = targetCards.get(currentIndex);

        String combined = "<html><div style='text-align:center; width:350px;'>"
                + card.getFront() + "<br><br>" + card.getBack() + "</div></html>";
        backLabel.setText(combined);
        backScrollPane.setVisible(true);

        buttonPanel.removeAll();
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void onAgain() {
        nextCard(false);
    }

    private void nextCard(boolean isEasy) {
        targetCards = targetCards.stream()
                .filter(Card::isTarget)
                .collect(Collectors.toList());

        if (targetCards.isEmpty()) {
            frontLabel.setText(TARGET_CARD_EMPTY_MESSAGE);
            cardLayout.show(cardPanel, "FRONT");

            buttonPanel.removeAll();
            buttonPanel.add(mainButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            return;
        }

        if (!isEasy) {
            currentIndex = (currentIndex + 1) % targetCards.size();
        } else {
            if (currentIndex == targetCards.size()) {
                currentIndex = 0;
            }
        }

        showCurrentCard();
    }

    private void showCurrentCard() {
        if (targetCards.isEmpty()) {
            frontLabel.setText(TARGET_CARD_EMPTY_MESSAGE);
            cardLayout.show(cardPanel, "FRONT");
            return;
        }

        Card card = targetCards.get(currentIndex);

        String frontHTML = "<html><div style='text-align:center; width:350px;'>"
                + card.getFront() + "</div></html>";
        String backHTML = "<html><div style='text-align:center; width:350px;'>"
                + card.getBack() + "</div></html>";

        frontLabel.setText(frontHTML);
        backLabel.setText(backHTML);

        cardLayout.show(cardPanel, "FRONT"); // 항상 FRONT로 시작

        buttonPanel.removeAll();
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(mainButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // =====================================================
    // 이벤트 등록
    // =====================================================
    void addShowAnswerButtonEvent(JButton btn) {
        btn.addActionListener(e -> onShowAnswer());
    }

    void addEasyButtonEvent(JButton btn, Deck deck) {
        btn.addActionListener(e -> {
            cardsController.getEasyButtonEvent(targetCards, currentIndex, deck);
            nextCard(true);
        });
    }

    void addMediumButtonEvent(JButton btn, Deck deck) {
        btn.addActionListener(e -> {
            onAgain();
            cardsController.getMediumButtonEvent(targetCards, currentIndex, deck);
        });
    }

    void addHardButtonEvent(JButton btn, Deck deck) {
        btn.addActionListener(e -> {
            onAgain();
            cardsController.getHardButtonEvent(targetCards, currentIndex, deck);
        });
    }

    void addMainButtonEvent(JButton btn) {
        btn.addActionListener(e -> mainController.getMainButtonEvent());
    }
}
