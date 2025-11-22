package woowa.myapp.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.DeckManager;

public class MainFrame extends JFrame {

    private JPanel mainContainer;

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;

    public MainFrame(DeckManager deckManager) {

        setTitle("암기빵");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); // 화면 중앙 배치
        setLayout(new BorderLayout());

        mainContainer = new JPanel(new BorderLayout());
        add(mainContainer, BorderLayout.CENTER);
        setVisible(true);

    }

    public void setPanel(JPanel panel) {
        mainContainer.removeAll();
        mainContainer.add(panel, BorderLayout.CENTER);
        mainContainer.revalidate();
        mainContainer.repaint();
    }




}
