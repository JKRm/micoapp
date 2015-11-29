package iie.ac.cn.micoconnection.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import iie.ac.cn.micoconnection.R;

/**
 * Created by Rimon_Kou on 2015/11/27.
 */
public class Util {

    private final static char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public static String getMD5(String val) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes());
        byte[] m = md5.digest();
        return getString(m);
    }

    private static String getString(byte[] md){
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static String getTimeStamp(){
        Long timeStamp = System.currentTimeMillis()/1000;
        return timeStamp.toString();
    }

    public static void sendHttpRequest(Map<String, String> keyParams, HttpRequest.HttpMethod method, String httpApi, final Context context){
        JSONObject postParam = new JSONObject();
        try {
            for(Map.Entry<String, String> entry : keyParams.entrySet()){
                postParam.put(entry.getKey(), entry.getValue());
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        String timeStamp = Util.getTimeStamp();
        String secret = context.getResources().getString(R.string.FogCloud_secret);
        String md5 = null;
        try {
            md5 = Util.getMD5(secret + timeStamp);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        HttpUtils http = new HttpUtils();
        http.configTimeout(15000);
        RequestParams params = new RequestParams();
        params.addHeader("Content-Type", "application/json");
        params.addHeader("X-Application-Id", context.getResources().getString(R.string.FogCloud_id));
        params.addHeader("X-Request-Sign", md5 + ", " + timeStamp);
        try{
            params.setBodyEntity(new StringEntity(postParam.toString(), "UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        http.send(method, httpApi, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, responseInfo.result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
