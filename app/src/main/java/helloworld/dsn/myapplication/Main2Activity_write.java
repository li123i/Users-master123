package helloworld.dsn.myapplication;
/****
 *
 * 填写快递订单
 *
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity_write extends AppCompatActivity implements View.OnClickListener {
    private Button right;
    private Button cancel;
    private TextView show;
    private EditText editText_name;
    private EditText editText2_phoneNumber;
    private EditText editText3_thing;
    private EditText editText5_thingmoney;
    private EditText editText6_address;
    private EditText editText4_shouhuo;
    private EditText editText7_pay;
    private EditText editText8_remark;
    private String data=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2_write );
        right=(Button)findViewById( R.id.right );
        show=(TextView)findViewById( R.id.textview6 ) ;
        editText_name=(EditText)findViewById( R.id.edittext );
        editText2_phoneNumber=(EditText)findViewById( R.id.edittext2 );
        editText3_thing=(EditText)findViewById( R.id.edittext3 );
        editText4_shouhuo=(EditText)findViewById( R.id.edittext4 );
        editText5_thingmoney=(EditText)findViewById( R.id.edittext5 );
        editText6_address=(EditText)findViewById( R.id.edittext6 );
        editText7_pay=(EditText)findViewById( R.id.edittext7 );
        editText8_remark=(EditText)findViewById( R.id.edittext8 );
        cancel=(Button)findViewById(R.id.cancel);
        right.setOnClickListener( this );
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right:{
                    String s=MainActivity.data1;
                    data=s.substring( 1,s.length()-1 );
                    send(data);
                    break;
            }
            case R.id.cancel:{
                Intent intent=new Intent(
                        Main2Activity_write.this,Main2Activity_show.class
                );
                startActivity(intent);
            }
        }
    }

    private void send(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formbody=new FormBody.Builder().
                            add("accessToken",s).
                            add("tradeName","sads" /*String.valueOf( editText3_thing.getText()* )*/ ).
                            add("contactName","saas" //String.valueOf( editText_name.getText() )
                                     ).
                            add("phone", "17343620917"//valueOf( editText2_phoneNumber.getText())
                                     ) .
                            add("money", "23"//String.valueOf( editText5_thingmoney.getText() )
                                     ).
                            add("payMoney","23"// String.valueOf( editText7_pay.getText() )
                                     ).
                            add("remark", String.valueOf( editText8_remark.getText() ) ).
                            add("buyAddress","afsdcs"//String.valueOf( editText6_address.getText() )
                                     ).
                            add("receiveAddress", "sadsadsa"//String.valueOf( editText4_shouhuo.getText() )
                                     ).build();
                    Request request=new Request.Builder().
                            url("http://47.106.159.165:8080/specialty/publishOrder").
                            post(formbody).build();
                    Response response=client.newCall(request).execute();
                    String s=response.body().string();
                    JsonElement je = new JsonParser().parse( s );
                    String s1= String.valueOf( je.getAsJsonObject().
                            get( "code" ) );
                    Show( s1 );
                } catch (IOException e) {
                    e.printStackTrace();
                }}
        }).start();
    }

    private void Show(final String s) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                int i=Integer.valueOf(s).intValue();
                if(i==0){
                    show.setText( "successfully" );
                    Intent intent=new Intent( Main2Activity_write.this,Main2Activity_show.class );
                    startActivity( intent );
                }else{
                    Toast.makeText( Main2Activity_write.this,
                            "请认真填写表格信息",Toast.LENGTH_SHORT).show();
                    right=(Button)findViewById( R.id.right );
                    right.setOnClickListener( Main2Activity_write.this );
                }

            }
        } );
    }

}

