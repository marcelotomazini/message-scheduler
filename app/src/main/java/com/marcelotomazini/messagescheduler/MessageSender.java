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

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(MSG);
        Intent sendMessage = intent.getParcelableExtra(SEND_MESSAGE);

        context.startService(sendMessage.setAction(msg));
    }
}
