package graphic;

import entities.Review;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReviewTableModel extends AbstractTableModel {

    // Lista delle recensioni mostrate nella tabella
    private List<Review> reviewList;

    // Costruttore
    public ReviewTableModel(List<Review> reviewList) {
        if(reviewList != null && reviewList.size() > 0) {
            this.reviewList = reviewList;
        } else {
            this.reviewList = new ArrayList<Review>();
        }
    }

    // Aggiorna la lista delle recensioni mostrate nella tabella
    public void updateReviews(List<Review> reviewList) {
        this.reviewList = new ArrayList<>(reviewList);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return reviewList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Review review = reviewList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return review.getComment();
            case 1:
                return review.getScore();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Testo";
            case 1:
                return "Voto";
            default:
                return null;
        }
    }
}
