package woowa.myapp.controller;

import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import woowa.myapp.model.Deck;
import woowa.myapp.model.DeckManager;
import woowa.myapp.view.DeckSettingPanel;

public class DeckSettingController {
    private DeckManager deckManager;

    public final String DECK_SAVE_MESSAGE = "설정이 저장되었습니다!";
    public final String DECK_SAVE_ERROR_MESSAGE = "저장 중 오류 발생: ";
    public final String DELETE_CARD_MESSAGE = "선택한 카드를 삭제하시겠습니까?";
    public final String DELETE_CARD_TITLE = "삭제 확인";
    public final String DELETE_CARD_SUCCESS_MESSAGE = "삭제 완료!";
    public final String DELETE_CARD_ERROR_MESSAGE = "삭제 중 오류 발생: ";

    public DeckSettingController(DeckManager deckManager) {
        this.deckManager = deckManager;
    }

    public void getSaveButtonEvent(Deck deck, List<JCheckBox> checkBoxes, DeckSettingPanel deckSettingPanel) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            deck.getCards().get(i).setTarget(checkBoxes.get(i).isSelected());
        }

        try {
            deck.saveDeckData();
            JOptionPane.showMessageDialog(deckSettingPanel, DECK_SAVE_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(deckSettingPanel, DECK_SAVE_ERROR_MESSAGE + ex.getMessage());
        }
    }

    public void getDeleteButtonEvent(List<JCheckBox> deleteBoxes, DeckSettingPanel deckSettingPanel, Deck deck) {
        int confirm = JOptionPane.showConfirmDialog(deckSettingPanel, DELETE_CARD_MESSAGE, DELETE_CARD_TITLE,
                JOptionPane.YES_NO_OPTION);

        if(confirm !=  JOptionPane.YES_OPTION) return;

        for (int i = deleteBoxes.size() - 1; i >= 0; i--) {
            if (deleteBoxes.get(i).isSelected()) {
                deck.getCards().remove(i);
            }
        }
        try {
            deck.saveDeckData();
            JOptionPane.showMessageDialog(deckSettingPanel, DELETE_CARD_SUCCESS_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(deckSettingPanel, DELETE_CARD_ERROR_MESSAGE + ex.getMessage());
        }
    }

    public void getDeleteAllCheckBoxEvent(List<JCheckBox> deleteBoxes, JCheckBox deleteAllCheckBox) {
        boolean selected = deleteAllCheckBox.isSelected();
        for (JCheckBox box : deleteBoxes) {
            box.setSelected(selected);
        }
    }

    public void getTargetAllCheckBoxEvent(List<JCheckBox> checkBoxes, JCheckBox targetAllCheckBox) {
        boolean selected = targetAllCheckBox.isSelected();
        for (JCheckBox box : checkBoxes) {
            box.setSelected(selected);
        }
    }
}
