package test;

import graphic.ActivityPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crea il pannello per le attività
        ActivityPanel activityPanel = new ActivityPanel();

        // Crea la finestra principale e aggiungi il pannello delle attività
        JFrame frame = new JFrame("DIGITAdvisor");
        frame.setContentPane(activityPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
