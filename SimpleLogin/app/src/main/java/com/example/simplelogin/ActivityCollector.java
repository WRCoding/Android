package com.example.simplelogin;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public ActivityCollector() {
    }
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity act : activityList) {
            if (!act.isFinishing()) {
                act.finish();
            }
        }
        activityList.clear();
    }

}
