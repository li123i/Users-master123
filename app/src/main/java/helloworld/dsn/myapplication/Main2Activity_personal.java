package helloworld.dsn.myapplication;
/***
 *
 * 个人快递界面
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity_personal extends AppCompatActivity {
    private List<Dingdan_personal> list=new ArrayList<>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_personal);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        send();
    }
    private void send() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                String s=MainActivity.data1;
                s=s.substring( 1,s.length()-1 );
                RequestBody formbody=new FormBody.Builder().
                        add("accessToken", s).
                        add("pageNo", String.valueOf(2)  ).
                        add("contactName", String.valueOf(100)).build();
                Request request=new Request.Builder().
                        url("http://47.106.159.165:8080/user/getMySpecialtyOrder").
                        post(formbody).build();
                client.newCall( request ).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String data=response.body().string();
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
                                String a="订单号"+a0+' '+"订单状态"+a7+' '+'\n'+'\n'+"   联系人："+a2+"   购买物品："+a1+"   联系方式："+a8+
                                        "   物品价格"+a6+"   购买地址"+a9+"    收货地址"+a3+"   备注"+a5+'\n'+'\n'+"     发布时间"+a4;
                                Dingdan_personal dingdan=new Dingdan_personal( a ,R.drawable.menu,R.drawable.menu);
                                list.add(dingdan);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //创建适配器
                                        RecyclerView recyclerView=(RecyclerView)findViewById( R.id.recyclerview_person);
                                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( Main2Activity_personal.this );
                                        recyclerView.setLayoutManager( linearLayoutManager );
                                        DingdanAdapter_personal adapter=new DingdanAdapter_personal( list );
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
}
