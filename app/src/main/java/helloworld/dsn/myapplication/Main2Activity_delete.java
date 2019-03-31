package helloworld.dsn.myapplication;
/**
 *
 * 快递删除
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity_delete extends AppCompatActivity implements View.OnClickListener {
    private TextView top;
    private Button right;
    private Button cancel;
    private String s;
    private String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_delete);
        Intent intent=getIntent();
        num=intent.getStringExtra("num");
        s=intent.getStringExtra("receive");
        top=(TextView)findViewById(R.id.delete_top);
        right=(Button)findViewById(R.id.delete_right);
        cancel=(Button)findViewById(R.id.delete_cancel);
        if(s.equals("false")){
            top.setText("   该订单未被接取lala，您确定取消该订单吗？");
        }else{
            top.setText("   该订单已被接取，您确定取消该订单吗？");
        }
        right.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_right:{
                if (s.equals("false")){
                    send();
                }else{
                    send_ture();
                }
                break;
            }
            case R.id.delete_cancel:{
                Intent intent=new Intent(Main2Activity_delete.this,Main2Activity_personal.class);
                startActivity(intent);
            }
        }
    }

    private void send_ture() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                String s=MainActivity.data1;
                s=s.substring( 1,s.length()-1 );
                RequestBody formbody=new FormBody.Builder().
                        add("accessToken",s).
                        add("orderNum",num).build();
                Request request=new Request.Builder().
                        url("http://47.106.159.165:8080/specialty/agreeDelete").
                        post(formbody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Show(response.body().string());
                    }
                });
            }
        }).start();
    }

    private void send() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                String s=MainActivity.data1;
                s=s.substring( 1,s.length()-1 );
                RequestBody formbody=new FormBody.Builder().
                        add("accessToken",s).
                        add("orderNum",num).build();
                Request request=new Request.Builder().
                        url("http://47.106.159.165:8080/specialty/deleteOrder").
                        post(formbody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                       Show(response.body().string());
                    }
                });
            }
        }).start();
    }

    private void Show(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity_delete.this,"该订单已被删除",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
