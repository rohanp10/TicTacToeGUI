import javax.swing.*;
import java.awt.*;

public class TicTacToeRunner {

    public static void main(String[] args) {

        JFrame frame = new TicTacToeFrame();

        frame.setTitle("Tic Tac Toe Game");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth * 3/4, screenHeight * 3/4);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
