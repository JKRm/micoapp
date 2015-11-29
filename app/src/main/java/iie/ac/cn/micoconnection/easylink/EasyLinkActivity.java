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
    public EasyLinkAPI elapi;
    private Context ctx = null;
    private EasyLinkWifiManager mWifiManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_link);

        wifissid = (EditText) findViewById(R.id.wifissid);
        wifipsw = (EditText) findViewById(R.id.wifipsw);
        startsearch = (Button) findViewById(R.id.startsearch);
        stopsearch = (Button) findViewById(R.id.stopsearch);

        ctx = EasyLinkActivity.this;
        elapi = new EasyLinkAPI(ctx);

        mWifiManager = new EasyLinkWifiManager(ctx);
        wifissid.setText(mWifiManager.getCurrentSSID());

        startsearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                elapi.startFTC(wifissid.getText().toString().trim(),
                        wifipsw.getText().toString(), new FTCListener() {
                            @Override
                            public void onFTCfinished(String ip,
                                                      String jsonString) {
                                Log.e("FTCEnd", ip + " " + jsonString);
                                elapi.stopEasyLink();
                            }

                            @Override
                            public void isSmallMTU(int MTU) {
                            }
                        });
            }
        });
        stopsearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                elapi.stopFTC();
                elapi.stopEasyLink();
            }
        });
    }
}
