import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Buttons extends JButton {
    Buttons[][] buttons = new Buttons[7][7];
    void addButton(Buttons button, int i, int j) {
        button.setBackground(new Color(255, 206, 158));
        buttons[i][j] = button;
    }
}
class Gameplay extends JFrame implements ActionListener {
    Buttons button = new Buttons();
    boolean player1_Turn;
    boolean did_P1_block;
    boolean did_P2_block;
    int p2_Prev_Row_Pos;
    int p2_Prev_Col_Pos;
    int p1_Prev_Row_Pos;
    int p1_Prev_Col_Pos;
    public Gameplay() {
        //Creating Frame
        this.setTitle("The Isola Game"); //sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop running the code on pressing close button
        this.setResizable(false); //prevents frame from being resized
        this.setSize(600, 600); //sets size of frame
        this.setBackground(Color.YELLOW);
        ImageIcon imageIcon = new ImageIcon("logo.png"); //importing logo for game
        this.setIconImage(imageIcon.getImage()); //setting logo for game
        this.setLayout(new GridLayout(7, 7, 2, 2)); // creates a 7x7 grid layout
        for (int i = 0; i < 7; i++) {  //Adding Buttons
            for (int j = 0; j < 7; j++) {
                Buttons addingButton = new Buttons();
                button.addButton(addingButton, i, j);
                this.add(addingButton);
            }
        }
        // Enabling Action Listener on all the buttons
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                button.buttons[i][j].addActionListener(this);
            }
        }
        this.setVisible(true);
        gamePlay();
    }
    void gamePlay() {
        button.buttons[0][3].setText("P-2");
        p2_Prev_Row_Pos = 0;
        p2_Prev_Col_Pos = 3;
        button.buttons[6][3].setText("P-1");
        p1_Prev_Row_Pos = 6;
        p1_Prev_Col_Pos = 3;
        player1_Turn = true;
        did_P1_block = true;
        did_P2_block = true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (e.getSource() == button.buttons[i][j]) {
                    if (player1_Turn && did_P1_block) {  // To Move Player 1
                        if (restrictedMoves(i, j)) {
                            button.buttons[i][j].setText("P-1");
                            button.buttons[p1_Prev_Row_Pos][p1_Prev_Col_Pos].setText("X");
                            button.buttons[p1_Prev_Row_Pos][p1_Prev_Col_Pos].setEnabled(false);
                            p1_Prev_Row_Pos = i;
                            p1_Prev_Col_Pos = j;
                            player1_Turn = true;
                            did_P1_block = false;
                        }
                    } 
                    else if (player1_Turn) { //player 1 disabling a block
                        if (button.buttons[i][j].isEnabled()) {
                            button.buttons[i][j].setText("X");
                            button.buttons[i][j].setEnabled(false);
                            player1_Turn = false;
                            did_P1_block = true;
                            check();
                        }
                    } 
                    else if (did_P2_block) {   // To Move Player 2
                        if (restrictedMoves(i, j)) {
                            button.buttons[i][j].setText("P-2");
                            button.buttons[p2_Prev_Row_Pos][p2_Prev_Col_Pos].setText("X");
                            button.buttons[p2_Prev_Row_Pos][p2_Prev_Col_Pos].setEnabled(false);
                            p2_Prev_Row_Pos = i;
                            p2_Prev_Col_Pos = j;
                            player1_Turn = false;
                            did_P2_block = false;
                        }
                    } 
                    else {  // Player 2 disabling a block
                        button.buttons[i][j].setText("X");
                        button.buttons[i][j].setEnabled(false);
                        player1_Turn = true;
                        did_P2_block = true;
                        check();
                    }
                }
            }
        }
    }
    boolean restrictedMoves(int i, int j) {
        //To Move Up
        if (player1_Turn && (p1_Prev_Row_Pos - 1) == i && p1_Prev_Col_Pos == j && button.buttons[i][j].isEnabled() &&
                button.buttons[i][j].getText() != "P-2") {
            return true;
        }
        else if (!player1_Turn && (p2_Prev_Row_Pos + 1) == i && p2_Prev_Col_Pos == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-1") {
            return true;
        }

        //To Move Up Left
        else if (player1_Turn && (p1_Prev_Row_Pos - 1) == i && (p1_Prev_Col_Pos - 1) == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-2") {
            return true;
        } 
        else if (!player1_Turn && (p2_Prev_Row_Pos + 1) == i && (p2_Prev_Col_Pos - 1) == j && button.buttons[i][j].isEnabled()
                &&button.buttons[i][j].getText() != "P-1") {
            return true;
        }

        //To Move Up Right
        else if (player1_Turn && (p1_Prev_Row_Pos - 1) == i && (p1_Prev_Col_Pos + 1) == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-2") {
            return true;
        } 
        else if (!player1_Turn && (p2_Prev_Row_Pos + 1) == i && (p2_Prev_Col_Pos + 1) == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-1") {
            return true;
        }
        //To Move Down
        else if (player1_Turn && (p1_Prev_Row_Pos + 1) == i && p1_Prev_Col_Pos == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-2") {
            return true;
        } 
        else if (!player1_Turn && (p2_Prev_Row_Pos - 1) == i && p2_Prev_Col_Pos == j && button.buttons[i][j].isEnabled()
                && button.buttons[i][j].getText() != "P-1") {
            return true;
        }
        return false;
    }
    boolean check() {
        int p1_possibleMoves = 0;
        int p2_possibleMoves = 0;
        if(isValidMove((p1_Prev_Row_Pos-1),p1_Prev_Col_Pos) && button.buttons[p1_Prev_Row_Pos-1][p1_Prev_Col_Pos].isEnabled()
                && button.buttons[p1_Prev_Row_Pos-1][p1_Prev_Col_Pos].getText() != "P-2"){
            p1_possibleMoves++;
        }
        if (isValidMove((p1_Prev_Row_Pos - 1),(p1_Prev_Col_Pos - 1))
                && button.buttons[p1_Prev_Row_Pos - 1][p1_Prev_Col_Pos - 1].isEnabled()
                && button.buttons[p1_Prev_Row_Pos-1][p1_Prev_Col_Pos -1].getText() != "P-2"){
            p1_possibleMoves++;
        }
        if (isValidMove((p1_Prev_Row_Pos - 1),(p1_Prev_Col_Pos + 1))
                && button.buttons[p1_Prev_Row_Pos - 1][p1_Prev_Col_Pos + 1].isEnabled()
                && button.buttons[p1_Prev_Row_Pos-1][p1_Prev_Col_Pos+1].getText() != "P-2") {
            p1_possibleMoves++;
        }
        if (isValidMove((p1_Prev_Row_Pos + 1),p1_Prev_Col_Pos)
                && button.buttons[p1_Prev_Row_Pos + 1][p1_Prev_Col_Pos].isEnabled()
                && button.buttons[p1_Prev_Row_Pos+1][p1_Prev_Col_Pos].getText() != "P-2") {
            p1_possibleMoves++;
        }
        if (isValidMove((p2_Prev_Row_Pos + 1),(p2_Prev_Col_Pos)) &&
                button.buttons[p2_Prev_Row_Pos + 1][p2_Prev_Col_Pos].isEnabled()
                && button.buttons[p2_Prev_Row_Pos+1][p2_Prev_Col_Pos].getText() != "P-1") {
            p2_possibleMoves++;
        }
        if (isValidMove((p2_Prev_Row_Pos + 1),(p2_Prev_Col_Pos - 1)) &&
                button.buttons[p2_Prev_Row_Pos + 1][p2_Prev_Col_Pos - 1].isEnabled()
                && button.buttons[p2_Prev_Row_Pos+1][p2_Prev_Col_Pos-1].getText() != "P-1"){
            p2_possibleMoves++;
        }
        if (isValidMove((p2_Prev_Row_Pos + 1),(p2_Prev_Col_Pos+1)) &&
                button.buttons[p2_Prev_Row_Pos + 1][p2_Prev_Col_Pos+1].isEnabled()
                && button.buttons[p2_Prev_Row_Pos+1][p2_Prev_Col_Pos+1].getText() != "P-1") {
            p2_possibleMoves++;
        }
        if (isValidMove((p2_Prev_Row_Pos - 1),p2_Prev_Col_Pos) &&
                button.buttons[p2_Prev_Row_Pos - 1][p2_Prev_Col_Pos].isEnabled()
                && button.buttons[p2_Prev_Row_Pos-1][p2_Prev_Col_Pos].getText() != "P-1") {
            p2_possibleMoves++;
        }
        if (p1_possibleMoves == 0 && p2_possibleMoves == 0){
            System.out.println("Match Is Tied");
        }
        else if (p1_possibleMoves == 0) {
            this.dispose();
            try {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Player 2 is the winner");
        } 
        else if (p2_possibleMoves == 0){
            this.dispose();
            try {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Player 1 is the winner");
        }
        return true;
    }
    boolean isValidMove(int i, int j){
        if (i < 0 || i > 6 || j < 0 || j > 6) {
            return false;
        }
        if (!button.buttons[i][j].isEnabled()){
            return false;
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        new Gameplay();
    }
}
