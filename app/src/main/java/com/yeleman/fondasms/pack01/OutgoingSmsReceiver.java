
package com.yeleman.fondasms.pack01;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;


public class OutgoingSmsReceiver extends BroadcastReceiver {
    public static final String TAG = OutgoingSmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String expectedIntent = context.getPackageName() + App.OUTGOING_SMS_INTENT_SUFFIX;
        if (!intent.getAction().equals(expectedIntent)) {
            return;
        }

        Toast.makeText(context, context.getPackageName() + " received SMS request", Toast.LENGTH_SHORT).show();

        //Log.d(TAG, context.getPackageName() + " received SMS request");

        String to = intent.getStringExtra(App.OUTGOING_SMS_EXTRA_TO);
        ArrayList<String> bodyParts = intent.getStringArrayListExtra(App.OUTGOING_SMS_EXTRA_BODY);
        String serverId = intent.getStringExtra(App.OUTGOING_SMS_EXTRA_SERVERID);
        boolean deliveryReport = intent.getBooleanExtra(App.OUTGOING_SMS_EXTRA_DELIVERY_REPORT, false);

        SmsManager smgr = SmsManager.getDefault();

        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveryIntents = null;

        if (deliveryReport)
        {
            deliveryIntents = new ArrayList<PendingIntent>();
        }

        int numParts = bodyParts.size();

        for (int i = 0; i < numParts; i++)
        {
            Intent statusIntent = new Intent(App.MESSAGE_STATUS_INTENT, intent.getData());
            statusIntent.putExtra(App.STATUS_EXTRA_INDEX, i);
            statusIntent.putExtra(App.STATUS_EXTRA_NUM_PARTS, numParts);

            sentIntents.add(PendingIntent.getBroadcast(
                    context,
                    0,
                    statusIntent,
                    PendingIntent.FLAG_ONE_SHOT));

            if (deliveryReport)
            {
                Intent deliveryIntent = new Intent(App.MESSAGE_DELIVERY_INTENT, intent.getData());
                deliveryIntent.putExtra(App.STATUS_EXTRA_INDEX, i);
                deliveryIntent.putExtra(App.STATUS_EXTRA_NUM_PARTS, numParts);
                deliveryIntent.putExtra(App.STATUS_EXTRA_SERVER_ID, serverId);

                deliveryIntents.add(PendingIntent.getBroadcast(
                        context,
                        0,
                        deliveryIntent,
                        PendingIntent.FLAG_ONE_SHOT));
            }
        }

        smgr.sendMultipartTextMessage(to, null, bodyParts, sentIntents, deliveryIntents);
    }
}
