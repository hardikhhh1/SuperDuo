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

    }

    public static void updateWidget(String packageName,  AppWidgetManager appWidgetManager,
                                    int[] appWidgetIds, String homeTeamName,
                                    String awayTeamName, String homeScore, String awayScore,
                                    String matchTime){
        int layoutId = R.layout.football_scores_widget;

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(packageName, layoutId);

            views.setImageViewResource(R.id.widget_home_icon,
                    Utilies.getTeamCrestByTeamName(homeTeamName));
            views.setTextViewText(R.id.widget_home_name, homeTeamName);

            views.setTextViewText(R.id.score_textview,
                    Utilies.getScores(Integer.parseInt(homeScore),
                            Integer.parseInt(awayScore)));

            views.setTextViewText(R.id.widget_time, matchTime);

            views.setImageViewResource(R.id.widget_away_icon,
                    Utilies.getTeamCrestByTeamName(awayTeamName));
            views.setTextViewText(R.id.widget_away_name, awayTeamName);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


}
