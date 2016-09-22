package com.bikash.student;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bikash on 9/18/16.
 */
public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent intent1 = new Intent(context, MyNewIntentService.class);
        context.startService(intent1);
    }
}