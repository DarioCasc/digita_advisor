package graphic;
import entities.Activity;
import entities.Advisor;
import entities.Review;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActivityPanel extends JPanel {

    // Advisor che contiene la lista di attività
    Advisor advisor = new Advisor();
    boolean reviewDialogOpened = false;

    // Modello per la tabella che mostra le attività
    private ActivityTableModel activityTableModel;
    // Modello per la tabella che mostra le recensioni di un'attività selezionata
    private ReviewTableModel reviewTableModel;

    // Costruttore
    public ActivityPanel() {
        // Inizializza la lista delle attività (array vuoto)
        advisor.setActivityList(new ArrayList<Activity>());

        // Imposta il layout del pannello
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crea il modello per la tabella delle attività e la tabella stessa
        activityTableModel = new ActivityTableModel(advisor);
        JTable activityTable = new JTable(activityTableModel);

        // Aggiungi la tabella al pannello
        add(new JScrollPane(activityTable));

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
                activityTableModel.updateActivities(filteredActivities);
            }
        });

        // Aggiungi i pulsanti e il campo di testo al pannello dei pulsanti
        buttonPanel.add(addButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);

        // Aggiungi il pannello dei pulsanti al pannello principale
        add(buttonPanel);

        // Aggiungi un listener alla tabella delle attività per rilevare quando viene selezionata una nuova attività
        activityTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verifica se una finestra di dialogo per le recensioni è già aperta
                if(!e.getValueIsAdjusting()) {
                    if (!reviewDialogOpened) {
                        // Imposta reviewDialogOpened su true per indicare che la finestra di dialogo è aperta

                        // Ottieni l'indice della riga selezionata nella tabella delle attività
                        int selectedRow = activityTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            reviewDialogOpened = true;
                            // Ottieni l'attività selezionata dal modello della tabella
                            Activity selectedActivity = activityTableModel.getActivityAt(selectedRow);

                            // Mostra la finestra di dialogo con la tabella delle recensioni
                            ReviewDialog dialog = new ReviewDialog(ActivityPanel.this, selectedActivity.getReviewList(), activityTable);
                            dialog.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    // Aggiunge una nuova attività alla lista delle attività
    public void addActivity(Activity activity) {
        advisor.addActivity(activity);
        // Aggiorna il modello della tabella per mostrare la nuova attività nella tabella
        activityTableModel.fireTableDataChanged();
    }

    // Aggiunge una recensione ad una attività esistente
    public void addReview(int activityIndex, Review review) {
        Activity activity = advisor.getActivityList().get(activityIndex);
        activity.addReview(review);
        // Aggiorna il modello della tabella per mostrare il nuovo punteggio dell'attività nella tabella
        activityTableModel.fireTableDataChanged();
    }
}
