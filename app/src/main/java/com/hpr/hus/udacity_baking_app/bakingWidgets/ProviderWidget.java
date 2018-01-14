package com.hpr.hus.udacity_baking_app.bakingWidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.hpr.hus.udacity_baking_app.R;
import com.hpr.hus.udacity_baking_app.graphic.RecipeDetailActivity;

import java.util.ArrayList;

/**
 * Created by hk640d on 12/30/2017.
 */

public class ProviderWidget extends AppWidgetProvider {
    public static String INGREDIENT_LIST ="INGREDIENT_LIST";
    static ArrayList<String> ingredientsList = new ArrayList<>();
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
        appIntent.addCategory(Intent.ACTION_MAIN);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.grid_widget, pendingIntent);
        Intent intent = new Intent(context, WidgetServices.class);
        views.setRemoteAdapter(R.id.grid_widget, intent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    @Override
    public void onEnabled(Context context) {
    }
    @Override
    public void onDisabled(Context context) {
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }
    public static void updatingWidgets(Context context, AppWidgetManager appWidgetManager, int[] widgetId) {
        for (int appWidgetId : widgetId) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] WidgetId = appWidgetManager.getAppWidgetIds(new ComponentName(context, ProviderWidget.class));
        String intentAction = intent.getAction();
        if (intentAction.equals("android.appwidget.action.APPWIDGET_UPDATE2")) {
            ingredientsList = intent.getExtras().getStringArrayList(INGREDIENT_LIST);
            appWidgetManager.notifyAppWidgetViewDataChanged(WidgetId, R.id.grid_widget);
            ProviderWidget.updatingWidgets(context, appWidgetManager, WidgetId);
            super.onReceive(context, intent);
        }
    }

}

