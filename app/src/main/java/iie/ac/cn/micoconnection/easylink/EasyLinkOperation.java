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
    private static String WifiPassword = "adminwangyang";
    private final static String TAG = "EASYLINK_OPERATION";

    public static String startConnection(Context context){
        easyLinkWifiManager = new EasyLinkWifiManager(context);
        String SSID = easyLinkWifiManager.getCurrentSSID();
        easyLinkAPI.startFTC(SSID, WifiPassword, new FTCListener() {
            @Override
            public void onFTCfinished(String ip, String jsonString) {
                Log.e(TAG, ip + "" + jsonString);
                easyLinkAPI.stopEasyLink();
            }

            @Override
            public void isSmallMTU(int i) {

            }
        });
        return null;
    }
}
