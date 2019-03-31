package helloworld.dsn.myapplication;
/**
 *
 * 订饭删除
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

public class Main2Activity_eatdelete extends AppCompatActivity implements View.OnClickListener {
    private TextView eatdelete_top;
    private Button OK;
    private Button Cancel;
    private String num;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_eatdelete);
        eatdelete_top=(TextView)findViewById(R.id.eatdelete_top);
        OK=(Button)findViewById(R.id.eatdelete_right);
        Cancel=(Button)findViewById(R.id.eatdelete_cancel);
        Intent intent=getIntent();
        num=intent.getStringExtra("num");
        s=intent.getStringExtra("receive");
        Show();
        OK.setOnClickListener(this);
        Cancel.setOnClickListener(this);
    }

    private void Show() {
        if(s.equals("该订单未接取")){
            eatdelete_top.setText("   该订单未被接取，您确定取消该订单吗？");
         }else{
            eatdelete_top.setText("   该订单已被接取，您确定取消该订单吗？");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.eatdelete_right:{
                if(s.equals("该订单未接取")){
                    /**
                     * 未接取时删除订单
                     * */
                    send_OK();
                }else{
                    /***
                     * //接取时删除订单
                     * */
                   send_Cancel();

                }
            }
            case R.id.eatdelete_cancel:{
                Intent intent=new Intent(Main2Activity_eatdelete.this,
                        Main2Activity_eatpersonal.class);
                startActivity(intent);
            }
        }
    }

    private void send_Cancel() {
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
                        url("http://47.106.159.165:8080/food/agreeDeleteOrder").
                        post(formbody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Show("该订单已删除");
                    }
                });
            }
        }).start();
    }

    private void send_OK() {
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
                        url("http://47.106.159.165:8080/food/deleteOrder").
                        post(formbody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Show("该订单已删除");
                    }
                });
        }
        }).start();
    }

    private void Show(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity_eatdelete.this,string,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}