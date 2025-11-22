package woowa.myapp.view;

import javax.swing.JOptionPane;

public class MessageView {

    public static void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
