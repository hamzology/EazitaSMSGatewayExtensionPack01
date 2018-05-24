package com.yeleman.fondasms.pack01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class QueryExpansionPacksReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(App.QUERY_EXPANSION_PACKS_INTENT)) {
            return;
        }

        //Toast.makeText(context, context.getPackageName() + " queried", Toast.LENGTH_SHORT).show();
        try {
            getResultExtras(true).getStringArrayList(App.QUERY_EXPANSION_PACKS_EXTRA_PACKAGES).add(context.getPackageName());
        } catch (Exception e) {
            Log.d(App.TAG, e.toString());
        }
    }

}
