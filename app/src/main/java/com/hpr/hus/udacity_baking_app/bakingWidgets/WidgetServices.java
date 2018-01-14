package com.hpr.hus.udacity_baking_app.bakingWidgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.hpr.hus.udacity_baking_app.R;

import java.util.List;

/**
 * Created by hk640d on 12/30/2017.
 */

public class WidgetServices extends RemoteViewsService {
    List<String> ingredientList;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridWidgetView(this.getApplicationContext(),intent);
    }
    class GridWidgetView implements RemoteViewsService.RemoteViewsFactory {
        Context mContext = null;
        public GridWidgetView(Context context, Intent intent) {
            mContext = context;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public void onCreate() {
        }
        @Override
        public void onDataSetChanged() {
            ingredientList = ProviderWidget.ingredientsList;
        }
        @Override
        public void onDestroy() {
        }
        @Override
        public int getCount() {
            return ingredientList.size();
        }
        @Override
        public RemoteViews getLoadingView() {
            return null;
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.widget_itmes);
            view.setTextViewText(R.id.widget_text_view, ingredientList.get(position));
            Intent fillInIntent = new Intent();
            view.setOnClickFillInIntent(R.id.widget_text_view, fillInIntent);
            return view;
        }








    }


}

