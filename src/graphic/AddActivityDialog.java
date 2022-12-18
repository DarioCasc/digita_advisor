package graphic;

import entities.Activity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Finestra di dialogo per inserire i dettagli di una nuova attività
public class AddActivityDialog extends JDialog {
    private ActivityPanel activityPanel;

    public AddActivityDialog(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;

        setTitle("Aggiungi attività");
        setModal(true);

        // Crea i campi per inserire il nome, il tipo e la descrizione dell'attività
        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(20);

        JLabel typeLabel = new JLabel("Tipologia:");
        JTextField typeField = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Descrizione:");
        JTextField descriptionField = new JTextField(20);

        // Crea il pulsante per confermare l'inserimento della nuova attività
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nuova attività con il nome inserito e aggiungila alla lista delle attività
                String name = nameField.getText();
                String type = typeField.getText();
                String description = descriptionField.getText();
                Activity activity = new Activity(name, type, description);
                activityPanel.addActivity(activity);

                // Chiudi la finestra di dialogo
                setVisible(false);
            }
        });

        // Crea il pannello per i campi e il pulsante
        JPanel fieldPanel = new JPanel();
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(typeLabel);
        fieldPanel.add(typeField);
        fieldPanel.add(descriptionLabel);
        fieldPanel.add(descriptionField);
        fieldPanel.add(okButton);

        // Aggiungi il pannello al frame
        add(fieldPanel, BorderLayout.CENTER);

        // Imposta le dimensioni della finestra di dialogo
        pack();
        setLocationRelativeTo(null);
    }
}