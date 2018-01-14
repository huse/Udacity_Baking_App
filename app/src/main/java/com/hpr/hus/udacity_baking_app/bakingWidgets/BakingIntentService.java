package com.hpr.hus.udacity_baking_app.bakingWidgets;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by hk640d on 12/30/2017.
 */

public class BakingIntentService extends IntentService {

    public static String INGREDIENT_LIST ="INGREDIENT_LIST";

    public BakingIntentService() {
        super("UpdateBakingService");
    }

    private void updatngWidget(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(INGREDIENT_LIST,fromActivityIngredientsList);
        sendBroadcast(intent);
    }
    public static void startWidget(Context context, ArrayList<String> stringArrayList) {
        Intent intent = new Intent(context, BakingIntentService.class);
        intent.putExtra(INGREDIENT_LIST,stringArrayList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent widgetIntent) {
        if (widgetIntent != null) {
            ArrayList<String> fromActivityIngredientsList = widgetIntent.getExtras().getStringArrayList(INGREDIENT_LIST);
            updatngWidget(fromActivityIngredientsList);

        }
    }





}
