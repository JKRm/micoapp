package iie.ac.cn.micoconnection.users;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import iie.ac.cn.micoconnection.R;
import iie.ac.cn.micoconnection.easylink.EasyLinkActivity;
import iie.ac.cn.micoconnection.utils.Utils;

/**
 * Created by Rimon on 2015/11/29.
 */
public class UserHttpRequest {

    public static int status = -1;
    public static int sendHttpRequest(Map<String, String> keyParams, HttpRequest.HttpMethod method, String httpApi, final Context context){
        JSONObject postParam = new JSONObject();
        try {
            for(Map.Entry<String, String> entry : keyParams.entrySet()){
                postParam.put(entry.getKey(), entry.getValue());
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        String timeStamp = Utils.getTimeStamp();
        String secret = context.getResources().getString(R.string.FogCloud_secret);
        String md5 = null;
        try {
            md5 = Utils.getMD5(secret + timeStamp);
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
                        status = 1;
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
                        status = 0;
                    }
                });
        int i = 0;
        while (i < 10){
            if(status != -1){
                return status;
            }
            try {
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            i ++;
        }
        return status;
    }
}
