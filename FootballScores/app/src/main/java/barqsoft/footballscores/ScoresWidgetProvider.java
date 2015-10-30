package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by harora on 10/28/15.
 */
public class ScoresWidgetProvider extends AppWidgetProvider{

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        String scores = "123 - 213";

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.football_scores_widget);
            views.setTextViewText(R.id.widget_scores, scores);

            Intent launchIntent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.football_scores_widget, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public static void updateWidget(String packageName,  AppWidgetManager appWidgetManager,
                                    int[] appWidgetIds, String homeTeamName,
                                    String awayTeamName, String homeScore, String awayScore){
        int layoutId = R.layout.football_scores_widget;

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(packageName, layoutId);

            views.setImageViewResource(R.id.widget_home_icon, Utilies.getTeamCrestByTeamName(homeTeamName));
//            views.setTextViewText(R.id.home_name, homeTeamName);

            views.setTextViewText(R.id.score_textview,
                    Utilies.getScores(Integer.parseInt(homeScore), Integer.parseInt(awayScore)));
//            views.setTextViewText(R.id.match_time_textview, mTime);

            views.setImageViewResource(R.id.widget_away_icon, Utilies.getTeamCrestByTeamName(awayTeamName));
//            views.setTextViewText(R.id.wi, awayTeamName);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


}
