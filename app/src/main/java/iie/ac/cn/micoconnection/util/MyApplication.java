package iie.ac.cn.micoconnection.util;

/**
 * Created by Rimon_Kou on 2015/11/27.
 */
import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}