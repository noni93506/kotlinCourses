package com.example.fragments

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.widget.RemoteViews
import android.widget.Toast


class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        Toast.makeText(context,"Thx for adding",Toast.LENGTH_LONG).show()
    }

    override fun onDisabled(context: Context) {
        Toast.makeText(context,"Thx for using",Toast.LENGTH_LONG).show()
    }
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == "myButtonClicked") {
            Toast.makeText(context,"thx for this click, its important for me",Toast.LENGTH_LONG).show()
        }
    }
}


internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {


    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setImageViewIcon(R.id.widgetImageView , Icon.createWithResource(context , R.mipmap.ic_launcher_foreground))

    views.setOnClickPendingIntent(R.id.widgetImageView, getPendingSelfIntent(context, "myButtonClicked"))


    appWidgetManager.updateAppWidget(appWidgetId, views)
}

private fun getPendingSelfIntent(context: Context, action: String): PendingIntent {
    val intent = Intent(context, NewAppWidget::class.java)
    intent.action = action
    return PendingIntent.getBroadcast(context, 0, intent, 0)
}