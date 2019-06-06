package com.spark.biben.custome.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollection{
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishOther(Activity exceptActivity) {
        for (Activity activity : activities) {
            if (activity.hashCode() == exceptActivity.hashCode()) {
                continue;
            }
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishOtherNoMain() {
        for (Activity activity : activities) {
            if ("MainActivity".equals(activity.getClass().getSimpleName())) {
                continue;
            }
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

