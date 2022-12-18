package graphic;

import entities.Activity;
import entities.Review;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ReviewDialog extends JDialog {
    private ReviewTableModel reviewTableModel;
    private JTable reviewTable;
    private ActivityPanel activityPanel;

    public ReviewDialog(ActivityPanel activityPanel, List<Review> reviews, JTable activityTable) {
        this.activityPanel = activityPanel;

        // Crea il modello per la tabella delle recensioni e la tabella stessa
        reviewTableModel = new ReviewTableModel(reviews);
        reviewTable = new JTable(reviewTableModel);

        // Aggiungi la tabella delle recensioni al pannello
        add(new JScrollPane(reviewTable));

        // Imposta le dimensioni della finestra di dialogo
        setSize(500, 300);
        // Imposta la posizione della finestra di dialogo al centro dello schermo
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // rimuovo la selection pending
                activityTable.getSelectionModel().clearSelection();
                // Imposta reviewDialogOpened su false per indicare che la finestra di dialogo è stata chiusa
                activityPanel.reviewDialogOpened = false;

            }
        });
    }


}
