package com.eazita.smsgateway.pack01;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static final String TAG = "RENO";
    public static final String QUERY_EXPANSION_PACKS_INTENT = "com.eazita.smsgateway.QUERY_EXPANSION_PACKS";
    public static final String QUERY_EXPANSION_PACKS_EXTRA_PACKAGES = "packages";

    public static final String OUTGOING_SMS_INTENT_SUFFIX = ".OUTGOING_SMS";
    public static final String OUTGOING_SMS_EXTRA_TO = "to";
    public static final String OUTGOING_SMS_EXTRA_SIM = "smssim";
    public static final String OUTGOING_SMS_EXTRA_BODY = "body";
    public static final String OUTGOING_SMS_EXTRA_SERVERID = "serverid";
    public static final String OUTGOING_SMS_EXTRA_DELIVERY_REPORT = "delivery";

    public static final String MESSAGE_STATUS_INTENT = "com.eazita.smsgateway.MESSAGE_STATUS";
    public static final String MESSAGE_DELIVERY_INTENT = "com.eazita.smsgateway.MESSAGE_DELIVERY";

    public static final String STATUS_EXTRA_INDEX = "status";
    public static final String STATUS_EXTRA_NUM_PARTS = "num_parts";
    public static final String STATUS_EXTRA_SERVER_ID = "server_id";

    public static List<SimInfo> getSIMInfo(Context context){
        List<SimInfo> simInfoList = new ArrayList();
        Uri URI_TELEPHONY = Uri.parse("content://telephony/siminfo/");
        Cursor c = context.getContentResolver().query(URI_TELEPHONY, null, null, null, null);
        if (c.moveToFirst()){
            do {
                int id = c.getInt(c.getColumnIndex("_id"));
                int slot = c.getInt(c.getColumnIndex("slot"));
                String display_name = c.getString(c.getColumnIndex("display_name"));
                String icc_id = c.getString(c.getColumnIndex("icc_id"));
                SimInfo simInfo = new SimInfo(id, display_name, icc_id, slot);
                Log.d("apipas_sim_info", simInfo.toString());
                simInfoList.add(simInfo);
            } while (c.moveToNext());
        }
        c.close();

        return simInfoList;
    }
    public App()
    {
    }
}
