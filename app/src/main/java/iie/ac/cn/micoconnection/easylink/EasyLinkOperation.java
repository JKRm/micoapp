package iie.ac.cn.micoconnection.easylink;

import android.content.Context;
import android.util.Log;

import com.mico.wifi.EasyLinkWifiManager;
import com.mxchip.easylink.EasyLinkAPI;
import com.mxchip.easylink.FTCListener;

/**
 * Created by Rimon on 2015/11/29.
 */
public class EasyLinkOperation {

    private static EasyLinkWifiManager easyLinkWifiManager;
    private static EasyLinkAPI easyLinkAPI;
    private final static String TAG = "EASYLINK_OPERATION";
    private static String mico_IP = null;
    private static Context context;

    public EasyLinkOperation(Context context){
        this.context = context;
        easyLinkAPI = new EasyLinkAPI(context);
    }

    public String startConnection(String SSID, String WifiPassword){
        easyLinkAPI.startFTC(SSID, WifiPassword, new FTCListener() {
            @Override
            public void onFTCfinished(String ip, String jsonString) {
                Log.e(TAG, ip + "" + jsonString);
                easyLinkAPI.stopEasyLink();
                mico_IP = ip;
            }

            @Override
            public void isSmallMTU(int i) {

            }
        });
        return mico_IP;
    }

    public void closeConnection(){
        easyLinkAPI.stopFTC();
        easyLinkAPI.stopEasyLink();
    }
}
