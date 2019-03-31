package helloworld.dsn.myapplication;
/***
 *
 * 个人订饭订单显示
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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

public class Main2Activity_eatpersonal extends AppCompatActivity implements View.OnClickListener
{
    private List<eatDingdanPersonal> list=new ArrayList<>();
    private String data=null;
    private Button reback_eatpersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_eatpersonal);
        reback_eatpersonal=(Button)findViewById(R.id.reback_eatpersonal);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        send();
        show_picture();
        //reback_eatpersonal.setOnClickListener(this);
    }

    private void show_picture() {
        File file = new File( "http://192.168.71.1:8080/img/87d95314788444859a3a58bd2a36591b.jpg");
        ImageView img = (ImageView) findViewById(R.id.ershow_show);
        if(file.exists()){
            Bitmap bm = BitmapFactory.decodeFile( "http://192.168.71.1:8080/img/87d95314788444859a3a58bd2a36591b.jpg");
            img.setImageBitmap(bm);
        }
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
                        add("pageNo", String.valueOf(1)  ).
                        add("pageSize", String.valueOf(100)).build();
                Request request=new Request.Builder().
                        url("http://47.106.159.165:8080/food/getMyPublishedOrder").
                        post(formbody).build();
                client.newCall( request ).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        data=response.body().string();
                        JsonElement je = new JsonParser().parse( data );
                        String s= String.valueOf( je.getAsJsonObject().
                                get( "foodOrders" ) );
                        JsonElement je1=new JsonParser().parse( s );
                        String s0=String.valueOf( je1.getAsJsonObject() .
                                get( "list" ));
                        JSONArray jsonArray= null;
                        try {
                            jsonArray = new JSONArray( s0);
                            String s1[]=new String [10];
                            String s2[]=new String [10];
                            String s3[]=new String [10];
                            for(int i=0;i< 100;i++){
                                JSONObject jsonObject=jsonArray.getJSONObject( i );
                                String a=jsonObject.getString("foods");
                                String sum="";
                                JSONArray array=new JSONArray(a);

                                /***
                                 *
                                 * 获取订单内容
                                 ***/
                                 String a7=jsonObject.getString("received");
                                 String id=jsonObject.getString("orderNumber");
                                 String contactName=jsonObject.getString("contactName");
                                 String receiveAddress=jsonObject.getString("receiveAddress");
                                 String remarks=jsonObject.getString("remarks");
                                 String publishTime=jsonObject.getString("publishTime");
                                 String totalMoney=jsonObject.getString("totalMoney");
                                 String allMoney=jsonObject.getString("allMoney");
                                 String phone=jsonObject.getString("phone");
                                 if(a7=="false"){
                                     a7="该订单未接取";
                                 }else{
                                     a7="该订单已接取";
                                 }
                                 sum="订单号   "+id+' '+a7+' '+'\n'+'\n'+"联系人："+contactName+"  联系电话"+phone
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
                                 "发布时间：  "+publishTime;
                                eatDingdanPersonal dingdan=new eatDingdanPersonal(R.drawable.menu,
                                        R.drawable.menu,sum);
                                list.add(dingdan );
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //创建适配器
                                        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_eatpersonal);
                                        LinearLayoutManager layoutManager=new LinearLayoutManager(Main2Activity_eatpersonal.this);
                                        recyclerView.setLayoutManager(layoutManager);
                                        eatDingdanPersonalAdapter adapter=new eatDingdanPersonalAdapter(list);
                                        recyclerView.setAdapter(adapter);
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
/**
 * 点击事件
 * **/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_eatpersonal:{
                Intent intent=new Intent(Main2Activity_eatpersonal.this,Main2Activity_eatshow.class);
                startActivity(intent);
                break;
            }
            default:{
                break;
            }
        }
    }
}
