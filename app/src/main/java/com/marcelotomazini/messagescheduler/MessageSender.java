package com.marcelotomazini.messagescheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;

public class MessageSender extends BroadcastReceiver {

    public static final String MSG = "MSG";
    public static final String SEND_MESSAGE = "SEND_MESSAGE";
    public static final int ONE_MINUTE = 60000;

    @Override
    public void onReceive(Context context, Intent intent) {
        openApp(context, "sneer.main");
        SystemClock.sleep(ONE_MINUTE);

        String msg = intent.getStringExtra(MSG);
        Intent sendMessage = intent.getParcelableExtra(SEND_MESSAGE);

        context.startService(sendMessage.setAction(msg));
    }

    public static void openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null)
                throw new PackageManager.NameNotFoundException();

            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(R.string.message_not_sent)
                .setPositiveButton("Ok", null)
                .show();
        }
    }
}
