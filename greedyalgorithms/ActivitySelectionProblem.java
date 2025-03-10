package greedyalgorithms;

import java.util.*;

class Activity {
    public String name;
    public int startTime;
    public int finishTime;

    public Activity(String name, int startTime, int finishTime) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Activity: " + name + ", Start time: " + startTime + ", Finish time: " + finishTime;
    }
}

public class ActivitySelectionProblem {
    
    public static void activitySelection(ArrayList<Activity> activityList) {
        // sort by finish time
        Comparator<Activity> comparator = (Activity a1, Activity a2) -> {
            return a1.finishTime - a2.finishTime;
        };

        Collections.sort(activityList, comparator);

        System.out.println("\nRecommended Schedule:\n" + activityList.get(0));
        
        Activity prevActivity = activityList.get(0);

        for (int i = 1; i < activityList.size(); i++) {
            Activity activity = activityList.get(i);
            if (activity.startTime >= prevActivity.finishTime) {
                System.out.println(activity);
                prevActivity = activity;
            }
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Activity> activityList = new ArrayList<>();

        activityList.add(new Activity("A1", 0, 6));
        activityList.add(new Activity("A2", 3, 4));
        activityList.add(new Activity("A3", 1, 2));
        activityList.add(new Activity("A4", 5, 8));
        activityList.add(new Activity("A5", 5, 7));
        activityList.add(new Activity("A6", 8, 9));

        ActivitySelectionProblem.activitySelection(activityList);
    }
}
