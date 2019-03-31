package helloworld.dsn.myapplication;
/***
 *
 * 订饭所有订单显示界面
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class Main2Activity_eatshow extends AppCompatActivity implements View.OnClickListener {

    private List<eatDingDan> list=new ArrayList<>();
    private TextView show;
    private Button have_new;
    private Button reback;
    private String data=null;
    private RecyclerView mRecyclerView;//数据列表
    private SwipeRefreshLayout mSwipeRefreshLayout;//下拉刷新布局
    private eatDingDanAdapter adapter;//我的适配器
    private static int page=Integer.parseInt("10");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_eatshow);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        have_new = (Button) findViewById(R.id.eatshow_new);
        reback = (Button) findViewById(R.id.eatshow_reback);
        /**
         * 发送订单内容
         * **/
        send();
        /***
         * 标题栏的点击事件
         * **/
        have_new.setOnClickListener(this);
        reback.setOnClickListener(this);
    }
    private void send() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                String pages=String.valueOf(page);
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().
                        url( "http://47.106.159.165:8080/guest/listFoodOrders?pageNo=1&pageSize=2++" )
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
                            String s1[]=new String [10];
                            String s2[]=new String [10];
                            String s3[]=new String [10];
                            for(int i=0;i< page;i++){
                                JSONObject jsonObject=jsonArray.getJSONObject( i );
                                String a=jsonObject.getString("foods");
                                String sum="";
                                JSONArray array=new JSONArray(a);


                                String a7=jsonObject.getString("received");
                                String id=jsonObject.getString("orderNumber");
                                String contactName=jsonObject.getString("contactName");
                                String receiveAddress=jsonObject.getString("receiveAddress");
                                String remarks=jsonObject.getString("remarks");
                                String publishTime=jsonObject.getString("publishTime");
                                String totalMoney=jsonObject.getString("totalMoney");
                                String allMoney=jsonObject.getString("allMoney");
                                String phone=jsonObject.getString("phone");
                                if(a7.equals("false")){
                                    a7="该订单未接取";
                                }else{
                                    a7="该订单已接取";
                                }

                                sum="订单号 "+" "+id+" "+a7+'\n'+'\n'+"联系人："+contactName+"  联系电话"+phone
                                        +'\n'+"购买物品："+'\n';
                                String sum1="";
                                for(int j=0;j<array.length();j++){
                                    JSONObject object=array.getJSONObject(j);
                                    s1[j]=object.getString("foodName");
                                    s2[j]=object.getString("money");
                                    s3[j]=object.getString("address");
                                    sum1=sum1+"地点："+s3[j]+"  菜式："+s1[j]+"  价钱："+s2[j]+'\n';

                                }
                                sum=sum+sum1+"菜单总价："+totalMoney+"   预支付："+allMoney+'\n'+
                                        "收货地址："+receiveAddress+'\n'+
                                        "备注："+remarks+'\n'+
                                        "发布时间：  "+publishTime;;
                                eatDingDan dingdan=new eatDingDan( sum,R.drawable.menu );
                                list.add(dingdan );
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //创建适配器
                                        mRecyclerView=(RecyclerView)findViewById( R.id.eat_recyclerview );
                                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( Main2Activity_eatshow.this );
                                        mRecyclerView.setLayoutManager( linearLayoutManager );
                                        adapter=new eatDingDanAdapter( list );
                                        // adapter.setHasStableIds(true);//数据加载
                                        mRecyclerView.setAdapter( adapter );
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
            case R.id.eatshow_new:{
                Intent intent=new Intent(Main2Activity_eatshow.this,
                        Main2Activity_eatwrite.class);
                startActivity(intent);
                break;
            }
            case R.id.eatshow_reback:{
                Intent intent =new Intent(Main2Activity_eatshow.this,Main2Activity_eatpersonal.class);
                startActivity(intent);
                break;
            }
            default:{
                break;
            }
        }
    }



}
