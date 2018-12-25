package com.example.intentfilterbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class BroadcastActivity extends AppCompatActivity {

    private IntentFilter filterFlight;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("@codekul", "Intent called");
            Boolean sts = intent.getBooleanExtra("state", false);
            if(sts) {
                ((ImageView)findViewById(R.id.img)).setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
            }else {
                ((ImageView)findViewById(R.id.img)).setImageResource(R.drawable.ic_airplanemode_inactive_black_24dp);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        filterFlight = new IntentFilter();
        filterFlight.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        sendBroadcast(new Intent());
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        registerReceiver(receiver, filterFlight);
    }

    @Override
    protected void onStop() {

        unregisterReceiver(receiver);
        super.onStop();
    }*/
}
