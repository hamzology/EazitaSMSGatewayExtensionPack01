package com.eazita.smsgateway.pack01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    String text;
    List<SimInfo> simInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simInfoList=App.getSIMInfo(this);


        TextView textv = (TextView)findViewById(R.id.text);
        text=textv.getText().toString();

        text=text+"\n\n SIM 0: "+simInfoList.get(0).getDisplay_name();
        text=text+"\n\n SIM 1: "+simInfoList.get(1).getDisplay_name();

        textv.setText(text);
    }
}
