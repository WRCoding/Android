package com.example.simplelogin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LogOutBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        ActivityCollector.finishAll();
        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity(intent1);
    }
}
