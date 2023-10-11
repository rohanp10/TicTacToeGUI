import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToeFrame extends JFrame {

    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JLabel titleLbl;
    static TicTacToeTile[][] board = new TicTacToeTile[3][3];

    final static int ROW = 3;
    final static int COL = 3;
    private static String[][] gameBoard = new String[ROW][COL];

    static JOptionPane displayMessage;
    JButton quit;

    boolean finished = false;
    static boolean playing = true;
    static String player = "X";
    static int moveCnt = 0;
    int row = -1;
    int col = -1;
    static final int MOVES_FOR_WIN = 5;
    static final int MOVES_FOR_TIE = 7;


    public TicTacToeFrame() {

        mainPnl = new JPanel();

        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePnl();
        mainPnl.add(middlePnl, BorderLayout.CENTER);


        add(mainPnl);

        clearBoard();


    }

    private void createTopPnl() {
        topPnl = new JPanel();

        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);

        titleLbl.setFont(new Font("Roboto", Font.PLAIN, 36));

        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        topPnl.setBackground(new Color(198, 226, 255));

        topPnl.add(titleLbl);
    }

    private void createMiddlePnl() {
        middlePnl = new JPanel();

        JButton quit = new JButton("Quit");

        quit.addActionListener((ActionEvent ae) -> System.exit(0));

        quit.setPreferredSize(new Dimension(200, 50));


        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                (board[row][col]).setPreferredSize(new Dimension(250, 250));
                buttonPanel.add(board[row][col]);
            }


        ActionListener listenerButton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                if (source == board[0][0]) {
                    changeBoard(0, 0);
                }
                else if (source == board[0][1]) {
                    changeBoard(0, 1);
                }
                else if (source == board[0][2]) {
                    changeBoard(0, 2);
                }
                else if (source == board[1][0]) {
                    changeBoard(1, 0);
                }
                else if (source == board[1][1]) {
                    changeBoard(1, 1);
                }
                else if (source == board[1][2]) {
                    changeBoard(1, 2);
                }
                else if (source == board[2][0]) {
                    changeBoard(2, 0);
                }
                else if (source == board[2][1]) {
                    changeBoard(2, 1);
                }
                else if (source == board[2][2]) {
                    changeBoard(2, 2);
                }

            }
        };

        board[0][0].addActionListener(listenerButton);
        board[0][1].addActionListener(listenerButton);
        board[0][2].addActionListener(listenerButton);
        board[1][0].addActionListener(listenerButton);
        board[1][1].addActionListener(listenerButton);
        board[1][2].addActionListener(listenerButton);
        board[2][0].addActionListener(listenerButton);
        board[2][1].addActionListener(listenerButton);
        board[2][2].addActionListener(listenerButton);

        middlePnl.add(buttonPanel);

        middlePnl.add(quit);

        middlePnl.setBackground(new Color(198, 226, 255));

    }

    private void createBottomPnl() {


    }

    private static void changeBoard(int row, int col) {

        if (playing) {

            if (isValidMove(row, col)) {
                gameBoard[row][col] = player;
                board[row][col].setText(player);
                moveCnt++;

                if (moveCnt >= MOVES_FOR_WIN) {
                    if (isWin(player)) {
                        displayMessage.showMessageDialog(null, "Player " + player + " wins!", "Message", JOptionPane.PLAIN_MESSAGE);
                        playing = false;
                    }
                }
                if (moveCnt >= MOVES_FOR_TIE) {
                    if (isTie()) {
                        displayMessage.showMessageDialog(null, "It's a Tie!", "Message", JOptionPane.PLAIN_MESSAGE);;
                        playing = false;
                    }
                }
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }
            } else {
                displayMessage.showMessageDialog(null, "Please select a valid unused square for your move!", "Message", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (!playing) {

            int answer = displayMessage.showConfirmDialog(null, "Do you want to play again?", "Message", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                System.exit(0);
            } else {
                restartGame();
            }

        }

    }

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(gameBoard[row][col].equals(" "))
            retVal = true;
        return retVal;
    }

    private static void clearBoard()
    {
// sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                gameBoard[row][col] = " ";
                board[row][col].setText(" ");
            }
        }
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }
    private static boolean isColWin(String player)
    {
// checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(gameBoard[0][col].equals(player) &&
                    gameBoard[1][col].equals(player) &&
                    gameBoard[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player)
    {
// checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(gameBoard[row][0].equals(player) &&
                    gameBoard[row][1].equals(player) &&
                    gameBoard[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagnalWin(String player)
    {
// checks for a diagonal win for the specified player
        if(gameBoard[0][0].equals(player) &&
                gameBoard[1][1].equals(player) &&
                gameBoard[2][2].equals(player) )
        {
            return true;
        }
        if(gameBoard[0][2].equals(player) &&
                gameBoard[1][1].equals(player) &&
                gameBoard[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }
    // checks for a tie before board is filled.
// check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
// Check all 8 win vectors for an X and O so
// no win is possible
// Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(gameBoard[row][0].equals("X") ||
                    gameBoard[row][1].equals("X") ||
                    gameBoard[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(gameBoard[row][0].equals("O") ||
                    gameBoard[row][1].equals("O") ||
                    gameBoard[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            xFlag = oFlag = false;
        }
// Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(gameBoard[0][col].equals("X") ||
                    gameBoard[1][col].equals("X") ||
                    gameBoard[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(gameBoard[0][col].equals("O") ||
                    gameBoard[1][col].equals("O") ||
                    gameBoard[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
// Now check for the diagonals
        xFlag = oFlag = false;
        if(gameBoard[0][0].equals("X") ||
                gameBoard[1][1].equals("X") ||
                gameBoard[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(gameBoard[0][0].equals("O") ||
                gameBoard[1][1].equals("O") ||
                gameBoard[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;
        if(gameBoard[0][2].equals("X") ||
                gameBoard[1][1].equals("X") ||
                gameBoard[2][0].equals("X") )
        {
            xFlag = true;
        }
        if(gameBoard[0][2].equals("O") ||
                gameBoard[1][1].equals("O") ||
                gameBoard[2][0].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
// Checked every vector so I know I have a tie
        return true;
    }

    public static void restartGame() {
        clearBoard();
        player = "X";
        playing = true;
        moveCnt = 0;

    }


}

