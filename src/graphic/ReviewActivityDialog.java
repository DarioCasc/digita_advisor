package graphic;

import entities.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewActivityDialog extends JDialog {
    private ActivityPanel activityPanel;

    public ReviewActivityDialog(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;

        setTitle("Inserisci recensione");
        setModal(true);

        // Crea una lista con i nomi delle attività
        String[] activityNames = new String[activityPanel.advisor.getActivityList().size()];
        for (int i = 0; i < activityPanel.advisor.getActivityList().size(); i++) {
            activityNames[i] = activityPanel.advisor.getActivityList().get(i).getName();
        }

        // Crea una casella di selezione per scegliere l'attività a cui aggiungere la recensione
        JLabel activityLabel = new JLabel("Attività:");
        JComboBox<String> activityComboBox = new JComboBox<>(activityNames);

        // Crea un pulsante per indicare se la recensione è positiva o negativa
        JLabel positiveLabel = new JLabel("Positiva:");
        JRadioButton positiveButton = new JRadioButton();
        JLabel negativeLabel = new JLabel("Negativa:");
        JRadioButton negativeButton = new JRadioButton();

        // Crea un gruppo di pulsanti per la scelta tra recensione positiva o negativa
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(positiveButton);
        buttonGroup.add(negativeButton);

        // Crea un campo per inserire il commento della recensione
        JLabel commentLabel = new JLabel("Commento:");
        JTextField commentField = new JTextField(20);

        JLabel scoreLabel = new JLabel("Punteggio:");
        JTextField scoreField = new JTextField(1);

        // Crea il pulsante per confermare l'inserimento della recensione
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nuova recensione con i dettagli inseriti e aggiungila all'attività selezionata
                int selectedIndex = activityComboBox.getSelectedIndex();
                boolean isPositive = positiveButton.isSelected();
                String comment = commentField.getText();
                String score = scoreField.getText();
                Review review = new Review();
                review.setComment(comment);
                review.setPositive(isPositive);
                review.setScore(Integer.parseInt(score));
                activityPanel.addReview(selectedIndex, review);

                // Chiudi la finestra di dialogo
                setVisible(false);
            }
        });

        // Crea il pannello per i campi e il pulsante
        JPanel fieldPanel = new JPanel();
        fieldPanel.add(activityLabel);
        fieldPanel.add(activityComboBox);
        fieldPanel.add(positiveLabel);
        fieldPanel.add(positiveButton);
        fieldPanel.add(negativeLabel);
        fieldPanel.add(negativeButton);
        fieldPanel.add(commentLabel);
        fieldPanel.add(commentField);
        fieldPanel.add(scoreLabel);
        fieldPanel.add(scoreField);
        fieldPanel.add(okButton);

        // Aggiungi il pannello al frame
        add(fieldPanel, BorderLayout.CENTER);

        // Imposta le dimensioni della finestra di dialogo
        pack();
        setLocationRelativeTo(null);
    }
}
