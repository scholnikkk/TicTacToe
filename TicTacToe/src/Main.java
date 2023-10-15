import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start game!");
        JFrame window = new JFrame("TicTacToe"); // new major window for project
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// "X" button, which close our window
        window.setSize(400,400); //window size
        window.setLayout(new BorderLayout()); // manager Border Layout
        window.setLocationRelativeTo(null); //set our game window to the center
        window.setVisible(true); // Turn on visibility of our window
        TicTacToe ticTacToe =  new TicTacToe();
        window.add(ticTacToe);
        System.out.println("The End!");
    }
}