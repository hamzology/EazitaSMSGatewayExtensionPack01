package com.yeleman.fondasms.pack01;


public class App
{
    public static final String TAG = "RENO";
    public static final String QUERY_EXPANSION_PACKS_INTENT = "com.yeleman.fondasms.QUERY_EXPANSION_PACKS";
    public static final String QUERY_EXPANSION_PACKS_EXTRA_PACKAGES = "packages";

    public static final String OUTGOING_SMS_INTENT_SUFFIX = ".OUTGOING_SMS";
    public static final String OUTGOING_SMS_EXTRA_TO = "to";
    public static final String OUTGOING_SMS_EXTRA_BODY = "body";
    public static final String OUTGOING_SMS_EXTRA_SERVERID = "serverid";
    public static final String OUTGOING_SMS_EXTRA_DELIVERY_REPORT = "delivery";

    public static final String MESSAGE_STATUS_INTENT = "com.yeleman.fondasms.MESSAGE_STATUS";
    public static final String MESSAGE_DELIVERY_INTENT = "com.yeleman.fondasms.MESSAGE_DELIVERY";

    public static final String STATUS_EXTRA_INDEX = "status";
    public static final String STATUS_EXTRA_NUM_PARTS = "num_parts";
    public static final String STATUS_EXTRA_SERVER_ID = "server_id";

    public App()
    {
    }
}
