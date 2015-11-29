package iie.ac.cn.micoconnection.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.lidroid.xutils.http.client.HttpRequest;

import java.util.HashMap;
import java.util.Map;

import iie.ac.cn.micoconnection.R;

public class UserActivity extends AppCompatActivity {

    private Button bindButton;
    private Button registerButton;
    private Button loginButton;
    private Button modifyPwdButton;
    private Button resetPwdButton;
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
                keyParams.put("username", "1012612428@qq.com");
                UserHttpRequest.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
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
                UserHttpRequest.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
                        "http://api.easylink.io/v2/users", UserActivity.this);
            }
        });

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> keyParams = new HashMap<>();
                keyParams.put("username", "1012612428@qq.com");
                keyParams.put("password", "rootroot");
                UserHttpRequest.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
                        "http://api.easylink.io/v2/users/login", UserActivity.this);
            }
        });

        modifyPwdButton = (Button)findViewById(R.id.modifyButton);
        modifyPwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> keyParams = new HashMap<>();
                keyParams.put("username", "1435521445@qq.com");
                keyParams.put("password", "rootroot");
                keyParams.put("new_password", "1234567");
                UserHttpRequest.sendHttpRequest(keyParams, HttpRequest.HttpMethod.PUT,
                        "http://api.easylink.io/v2/users/password", UserActivity.this);
            }
        });

        resetPwdButton = (Button)findViewById(R.id.resetButton);
        resetPwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> keyParams = new HashMap<>();
                keyParams.put("username", "1012612428@qq.com");
                keyParams.put("password", "rootroot");
                keyParams.put("verification_code", "479576");
                UserHttpRequest.sendHttpRequest(keyParams, HttpRequest.HttpMethod.POST,
                        "http://api.easylink.io/v2/users/password/reset", UserActivity.this);
            }
        });
    }
}
