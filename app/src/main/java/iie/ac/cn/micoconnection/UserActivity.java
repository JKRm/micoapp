package iie.ac.cn.micoconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.entity.StringEntity;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import iie.ac.cn.micoconnection.util.Util;

public class UserActivity extends AppCompatActivity {

    private Button bindButton;
    private Button registerButton;
    private final static String TAG = "MICO_USER_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        bindButton = (Button)findViewById(R.id.bindButton);
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(UserActivity.this, "hello world", Toast.LENGTH_SHORT).show();
                Map<String, String> keyParams = new HashMap<>();
                keyParams.put("username", "1435521445@qq.com");
                Util.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
                        "http://api.easylink.io/v2/users/email_verification_code", UserActivity.this);
            }
        });

        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> keyParams = new HashMap<>();
                keyParams.put("username", "1435521445@qq.com");
                keyParams.put("password", "rootroot");
                keyParams.put("verification_code", "736263");
                Util.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
                        "http://api.easylink.io/v2/users", UserActivity.this);
            }
        });
    }
}
