package helloworld.dsn.myapplication;
/****
 *
 * 登陆界面
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private TextView show;
    private static JsonUser obj = null;
    private String data=null;
    private Button d;
    static String data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        login=(Button)findViewById( R.id.login_phone_btnLogin);
        d=(Button)findViewById( R.id.login_phone_btnLogin1 );
        show=(TextView) findViewById( R.id.login_show );
        login.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_phone_btnLogin:{
                send();
                Intent intent=new Intent( MainActivity.this,Main2Activity_eatshow.class );
                startActivity( intent );
                break;
            }
        }
    }
    public void send() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formbody = new FormBody.Builder().
                            add( "phone", "123456" ).
                            add( "password", "123456" ).build();
                    Request request = new Request.Builder().
                            url( "http://47.106.159.165:8080/guest/phoneLogin" ).
                            post( formbody ).build();
                    Response response = client.newCall( request ).execute();
                    String s = response.body().string();
                    JsonElement je = new JsonParser().parse( s );
                    data1 = String.valueOf( je.getAsJsonObject().
                            get( "accessToken" ) );
                  //  Show(data1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

    }

    public void Show(final String data1) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                show.setText( data1 );
            }
        } );
    }
}


