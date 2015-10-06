package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.service.myFetchService;

/**
 * Created by hardikarora on 7/20/15.
 */
public class SoccerWidgetProvider extends AppWidgetProvider {

    private static final String UPDATE_TAG = SoccerWidgetProvider.class.getSimpleName() + ":UPDATE";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

//        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.soccer_widget_layout);
            // initiate widget update request
            Intent intent = new Intent();
            intent.setAction(UPDATE_TAG);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.layout.soccer_widget_layout, pendingIntent);


        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        /**************************************************

         ***************************************************/
        super.onReceive(context, intent);
        if(intent.getAction().equals(UPDATE_TAG)){
            context.startService(new Intent(context, myFetchService.class));
        }
    }
}
