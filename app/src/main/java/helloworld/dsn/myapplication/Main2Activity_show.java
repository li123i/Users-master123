package helloworld.dsn.myapplication;
/****
 * 快递展示界面
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity_show extends AppCompatActivity implements View.OnClickListener {
    private int page=1;
    private List<Dingdan> list=new ArrayList<>(  );
    private TextView show;
    private Button have_new;
    private Button reback;
    private String data=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2_show );
        /**让标题栏消失**/
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        have_new=(Button)findViewById(R.id.have_new);
        reback=(Button)findViewById(R.id.reback);
        have_new.setOnClickListener(this);
        reback.setOnClickListener(this);
        send();
    }

    private void send() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().
                        url( "http://47.106.159.165:8080/guest/listSpecialtyOrder?pageNo=1++&pageSize=100" )
                        .build();
                Response response = null;
                client.newCall( request ).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        data=response.body().string();
                        JsonElement je = new JsonParser().parse( data );
                        String s= String.valueOf( je.getAsJsonObject().
                                get( "orders" ) );
                        JsonElement je1=new JsonParser().parse( s );
                        String s0=String.valueOf( je1.getAsJsonObject() .
                                get( "list" ));
                        JSONArray jsonArray= null;
                        try {
                            jsonArray = new JSONArray( s0);
                            for(int i=0;i< 100;i++){
                                JSONObject jsonObject=jsonArray.getJSONObject( i );
                                String a0=jsonObject.getString( "orderNum" );
                                String a1=jsonObject.getString( "tradeName" );
                                String a2= jsonObject.getString( "contactName" );
                                String a3=jsonObject.getString("receiveAddress");
                                String a4=jsonObject.getString("publishTime");
                                String a5=jsonObject.getString("remarks");
                                String a6=jsonObject.getString("payMoney");
                                String a7=jsonObject.getString("received");
                                String a8=jsonObject.getString("phone");
                                String a9=jsonObject.getString("buyAddress");
                                String a="订单号"+a0+' '+"订单状态"+a7+'\n'+'\n'+"   联系人："+a2+"   购买物品："+a1+"   联系方式："+a8+
                                        "   物品价格"+a6+"   购买地址"+a9+"    收货地址"+a3+"   备注"+a5+'\n'+'\n'+"     发布时间"+a4;
                                Dingdan dingdan=new Dingdan( a,R.drawable.menu );
                                list.add(dingdan );
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //创建适配器
                                        RecyclerView recyclerView=(RecyclerView)findViewById( R.id.recyclerview );
                                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( Main2Activity_show.this );
                                        recyclerView.setLayoutManager( linearLayoutManager );
                                        DingdanAdapter adapter=new DingdanAdapter( list );
                                        // adapter.setHasStableIds(true);//数据加载
                                        recyclerView.setAdapter( adapter );
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } ).start();
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.have_new:{
             Intent intent=new Intent(Main2Activity_show.this,Main2Activity_write.class);
             startActivity(intent);
             break;
         }
         case R.id.reback:{
             Intent intent=new Intent(Main2Activity_show.this,Main2Activity_personal.class);
             startActivity(intent);
             break;
         }
     }
    }
}
