package graphic;

import entities.Activity;
import entities.Advisor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ActivityTableModel extends AbstractTableModel {

    Advisor advisor;

    public ActivityTableModel(Advisor advisor) {
        this.advisor = advisor;
    }

    // Aggiorna la lista delle attività mostrate nella tabella
    public void updateActivities(List<Activity> activities) {
        advisor.setActivityList(new ArrayList<Activity>());
        advisor.getActivityList().addAll(activities);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return advisor.getActivityList().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Activity activity = advisor.getActivityList().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return activity.getName();
            case 1:
                return activity.getType();
            case 2:
                return activity.getDescription();
            default:
                return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Tipo";
            case 2:
                return "Descrizione";
            default:
                return null;
        }
    }
}
