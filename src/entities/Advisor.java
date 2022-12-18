package entities;

import java.util.ArrayList;
import java.util.List;

public class Advisor {
    private List<Activity> activityList;

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public List<Activity> getActivityList() {
        if(activityList != null) return activityList;
        return new ArrayList<Activity>();
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
