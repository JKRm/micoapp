package iie.ac.cn.micoconnection.easylink;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mico.wifi.EasyLinkWifiManager;
import com.mxchip.easylink.EasyLinkAPI;
import com.mxchip.easylink.FTCListener;

import iie.ac.cn.micoconnection.R;

public class EasyLinkActivity extends AppCompatActivity {

    private Button startsearch;
    private Button stopsearch;
    private EditText wifissid;
    private EditText wifipsw;
    private String SSID;
    private EasyLinkWifiManager mWifiManager = null;
    private EasyLinkOperation easyLinkOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_link);

        wifissid = (EditText) findViewById(R.id.wifissid);
        wifipsw = (EditText) findViewById(R.id.wifipsw);
        startsearch = (Button) findViewById(R.id.startsearch);
        stopsearch = (Button) findViewById(R.id.stopsearch);
        mWifiManager = new EasyLinkWifiManager(EasyLinkActivity.this);
        SSID = mWifiManager.getCurrentSSID();
        wifissid.setText(SSID);
        easyLinkOperation = new EasyLinkOperation(EasyLinkActivity.this);

        startsearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                easyLinkOperation.startConnection(SSID, wifipsw.getText().toString().trim());
            }
        });
        stopsearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                easyLinkOperation.closeConnection();
            }
        });
    }
}
