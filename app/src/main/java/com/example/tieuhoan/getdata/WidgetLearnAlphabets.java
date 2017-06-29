package com.example.tieuhoan.getdata;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Alphabet;
import ulti.Json;

/**
 * Created by TieuHoan on 28/06/2017.
 */

public class WidgetLearnAlphabets extends AppWidgetProvider {
    private static final String CLICK_NEXT =
            "NEXT", CLICK_PREVIOUS = "PREVIOUS";
    private static ArrayList<Alphabet> alphabets;
    private static boolean isHiragana;
    private static int position = 0;
    private static int size;

    private ArrayList<Alphabet> alphabetsNotSpace;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Json json = new Json(context);
        alphabets = json.getAlphabets();
        alphabetsNotSpace = new ArrayList<>();

        for (int i = 0; i < alphabets.size(); i++) {
            if (!alphabets.get(i).getRomaji().equals("")) {
                alphabetsNotSpace.add(alphabets.get(i));
            }
        }
        size = alphabetsNotSpace.size();


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_learn_alphabet);

        InputStream ims = null;
        AssetManager assetManager = context.getAssets();
        try {

            if (getSelectAlphabet()) {
                ims = assetManager.open("image/hiragana/" + alphabetsNotSpace.get(position).getRomaji() + ".PNG");
            } else {
                ims = assetManager.open("image/katakana/" + alphabetsNotSpace.get(position).getRomaji() + ".PNG");
            }



            Bitmap bmp = BitmapFactory.decodeStream(ims);
            views.setImageViewBitmap(R.id.imgWidget, bmp);

        } catch (IOException e) {
            e.printStackTrace();
        }
        views.setOnClickPendingIntent(R.id.imgNextInWidget, getPendingSelfIntent(context, CLICK_NEXT));
        views.setOnClickPendingIntent(R.id.imgPreviousInWidget, getPendingSelfIntent(context, CLICK_PREVIOUS));
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }


    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public boolean getSelectAlphabet() {
        //get isHiragana from share refrence
        isHiragana = true;
        return isHiragana;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);
        if (CLICK_NEXT.equals(intent.getAction())) {
            if (position < size) {
                position++;
            }
            onMyUpdate(context);
        } else if (CLICK_PREVIOUS.equals(intent.getAction())) {
            if (position > 0) {
                position--;
            }
            onMyUpdate(context);
        }


    }

    private void onMyUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(), getClass().getName()
                );
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                thisAppWidgetComponentName);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
