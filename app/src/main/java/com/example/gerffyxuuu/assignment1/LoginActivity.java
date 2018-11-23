package com.example.gerffyxuuu.assignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;


public class LoginActivity extends AppCompatActivity{

    private Button button;
    private EditText ed_name;
    private EditText ed_pass;
    private TextView register;
    private TextView la_username;
    private String name;
    private String url1="http://192.168.101.1:8080/SHproject/homepage/login";//服务器接口地址

    @SuppressLint("HandlerLeak")
    private Handler hanlder=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(LoginActivity.this, "登陆成功!",Toast.LENGTH_LONG);
                    toast.show() ;
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        register = findViewById(R.id.textView5);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.btn_login);
        ed_name = (EditText)findViewById(R.id.et_userName);
        ed_pass = (EditText)findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //验证输入内容
                final String username=ed_name.getText().toString();
                name = username;
                final String password=ed_pass.getText().toString();

                if("".equals(username) || "".equals(password)){

                    Toast toast = Toast.makeText(LoginActivity.this, "请完整填写表单!",Toast.LENGTH_LONG);
                    toast.show() ;
                }else{
                    new Thread() {
                        @Override
                        public void run() {
                            hanlder.obtainMessage(0).sendToTarget();
                            URL url;
                            HttpURLConnection httpURLConnection;
                            try {
                                url = new URL(url1);
                                httpURLConnection = (HttpURLConnection) url.openConnection();

                                httpURLConnection.setDoOutput(true);//是否向链接输出
                                httpURLConnection.setDoInput(true);

                                httpURLConnection.setRequestMethod("POST");

                                httpURLConnection.setUseCaches(false);
                                httpURLConnection.setInstanceFollowRedirects(true);

                                httpURLConnection.setRequestProperty("Content-Type", "text/html; charset=GBK");
                                httpURLConnection.setConnectTimeout(10 * 1000);//连接超时 单位毫秒
                                httpURLConnection.setReadTimeout(10 * 1000);//读取超时 单位毫秒

                                httpURLConnection.connect();
                                DataOutputStream out = new DataOutputStream(httpURLConnection
                                        .getOutputStream());


                                StringBuffer params = new StringBuffer();
                                params.append("name").append("=").append(username).append("&")
                                        .append("pass").append("=").append(password);
                                httpURLConnection.getOutputStream().write(params.toString().getBytes("gb2312"));
                                out.flush();
                                out.close();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        httpURLConnection.getInputStream(), "gb2312"));
                                StringBuilder response = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null)
                                    response.append(line);
                                httpURLConnection.disconnect();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
    }

}
