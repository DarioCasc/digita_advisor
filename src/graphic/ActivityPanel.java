package graphic;
import entities.Activity;
import entities.Advisor;
import entities.Review;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActivityPanel extends JPanel {

    // Advisor che contiene la lista di attività
    Advisor advisor = new Advisor();

    // Modello per la tabella che mostra le attività
    private ActivityTableModel tableModel;

    // Costruttore
    public ActivityPanel() {
        // Inizializza la lista delle attività (array vuoto)
        advisor.setActivityList(new ArrayList<Activity>());

        // Imposta il layout del pannello
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crea il modello per la tabella e la tabella stessa
        tableModel = new ActivityTableModel(advisor);
        JTable table = new JTable(tableModel);

        // Aggiungi la tabella al pannello
        add(new JScrollPane(table));

        // Crea un pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Crea il pulsante per aggiungere una nuova attività
        JButton addButton = new JButton("Aggiungi attività");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra una finestra di dialogo per inserire i dettagli della nuova attività
                AddActivityDialog dialog = new AddActivityDialog(ActivityPanel.this);
                dialog.setVisible(true);
            }
        });

        // Crea il pulsante per aggiungere una recensione ad una attività esistente
        JButton reviewButton = new JButton("Inserisci recensione");
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra una finestra di dialogo per selezionare l'attività a cui aggiungere la recensione e inserire i dettagli della recensione
                ReviewActivityDialog dialog = new ReviewActivityDialog(ActivityPanel.this);
                dialog.setVisible(true);
            }
        });

        // Crea il campo di testo per il nome da cercare
        JTextField searchField = new JTextField(20);

        // Crea il pulsante per avviare la ricerca
        JButton searchButton = new JButton("Cerca");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ottieni il testo inserito nel campo di testo
                String searchText = searchField.getText();

                // Filtra la lista delle attività utilizzando il testo di ricerca
                List<Activity> filteredActivities = new ArrayList<>();
                for (Activity activity : advisor.getActivityList()) {
                    if (activity.getName().contains(searchText)) {
                        filteredActivities.add(activity);
                    }
                }

                // Aggiorna il modello della tabella utilizzando la lista delle attività filtrata
                tableModel.updateActivities(filteredActivities);
            }
        });

        // Aggiungi i pulsanti e il campo di testo al pannello dei pulsanti
        buttonPanel.add(addButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);

        // Aggiungi il pannello dei pulsanti al pannello principale
        add(buttonPanel);
    }

    // Aggiunge una nuova attività alla lista delle attività
    public void addActivity(Activity activity) {
        advisor.addActivity(activity);
        // Aggiorna il modello della tabella per mostrare la nuova attività nella tabella
        tableModel.fireTableDataChanged();
    }

    // Aggiunge una recensione ad una attività esistente
    public void addReview(int activityIndex, Review review) {
        Activity activity = advisor.getActivityList().get(activityIndex);
        activity.addReview(review);
        // Aggiorna il modello della tabella per mostrare il nuovo punteggio dell'attività nella tabella
        tableModel.fireTableDataChanged();
    }
}
