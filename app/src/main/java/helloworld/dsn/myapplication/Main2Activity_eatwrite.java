package helloworld.dsn.myapplication;
/***
 * 填写订饭界面
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity_eatwrite extends AppCompatActivity implements View.OnClickListener {
    private String s[]={"1","2","3","4","5","6","7","8","9","10"};
    private Spinner spinner;
    private List<eatDingdanWrite> eatwriteList=new ArrayList<>();
    private RecyclerView recyclerView;
    private eatDingdanWriteAdapter eatwriteAdapter;
    private int length=0;
    private List<Map<String, String>> houseRes = new ArrayList<>();
    public double sum=0;
    private Map<String, String> mDeviceHeaderMap;
    private Button summit;
    private TextView total;
    private JSONObject json;
    private Map<String,String> map = new HashMap<String,String>();
    private JSONArray jsonArray;
    private EditText contactname;
    private EditText eat_writephone;
    private EditText eat_writerecivedaddress;
    private EditText eat_writeremark;
    private Button eat_writeright;
    private EditText eat_writepaymoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_eatwrite);
        /***
         * 配置下拉框
         * */


spinner=(Spinner)findViewById(R.id.choose_much);
        //2）初始化设配器：
        initMuch(4);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //设置下拉列表的风格
        spinner.setAdapter(adapter);//将adapter 添加到spinner中
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener()); //添加事件Spinner事件监听
        summit=(Button)findViewById(R.id.write_sum);
        total=(TextView)findViewById(R.id.write_totalmoney);
        summit.setOnClickListener(this);

        contactname=(EditText)findViewById(R.id.eat_writename);
        eat_writephone=(EditText)findViewById(R.id.eat_writephone);
        eat_writerecivedaddress=(EditText)findViewById(R.id.eat_writerecivedaddress);
        eat_writeremark=(EditText)findViewById(R.id.eat_writeremark);
        eat_writepaymoney=(EditText)findViewById(R.id.eat_writepaymoney);
        eat_writeright=(Button)findViewById(R.id.eat_writeright);

        /***
         * 点击事件
         * */
        eat_writeright.setOnClickListener(this);
        }
    /***
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.write_sum:{
                getMap();
                break;
            }
            case R.id.eat_writeright:{
                try {
                    jsonArray=new JSONArray(houseRes.toString());
                    send();
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void send() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                String s=MainActivity.data1;
                s=s.substring( 1,s.length()-1 );
                RequestBody body=new FormBody.Builder().
                        add("accessToken",s).
                        add("contactName",contactname.getText().toString()).
                        add("phone",eat_writephone.getText().toString()).
                        add("receiveAddress",eat_writerecivedaddress.getText().toString()).
                        add("remarks",eat_writeremark.getText().toString()).
                        add("foodList", String.valueOf(jsonArray)).
                        add("payMoney",eat_writepaymoney.getText().toString()).
                        build();
                Request request=new Request.Builder().
                        url("http://47.106.159.165:8080/food/publishOrder").
                        post(body).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonElement je=new JsonParser().parse(response.body().string());
                        String s1=String.valueOf(je.getAsJsonObject().get("code"));
                        Show(s1);
                    }
                });
            }
        }).start();
    }

    private void Show(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(string == String.valueOf('0')){
                    Toast.makeText(Main2Activity_eatwrite.this
                    ,"订单创建成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Main2Activity_eatwrite.this
                    ,Main2Activity_eatshow.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Main2Activity_eatwrite.this
                            ,"订单创建未成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /****
     *
     * 设置下拉框 可以改变填写菜式的框框个数
     *
     * **/
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            /**
             * 初始化框框个数
             */
            initMuch(Integer.parseInt(s[arg2]));
            recyclerView=(RecyclerView)findViewById(R.id.eatwrite_recyclerview);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Main2Activity_eatwrite.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            eatDingdanWriteAdapter eatDingdanWriteAdapter=new eatDingdanWriteAdapter(eatwriteList);
            recyclerView.setAdapter(eatDingdanWriteAdapter);
            length=eatDingdanWriteAdapter.getItemCount();
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    /***
     * 初始化框框
     */
    private void initMuch(int n) {
        eatwriteList.clear();
        for(int i=1;i<=n;i++){
            eatDingdanWrite eatwrite=new eatDingdanWrite("","","");
            eatwriteList.add(eatwrite);
        }
    }
    /***
     *利用map类型获取recyclerview中子件的值
     * */
    public void getMap(){
        sum= Double.valueOf(0);
        for (int i = 0; i <recyclerView.getChildCount(); i++) {
            mDeviceHeaderMap = new HashMap<>();
            mDeviceHeaderMap.clear();
            View layout = (View) recyclerView.getChildAt(i);
            EditText name = layout.findViewById(R.id.eatwrite_cainame);
            EditText money = layout.findViewById(R.id.eatwrite_caimoney);
            EditText address=layout.findViewById(R.id.eatwrite_caiaddress);
            sum=sum+Double.parseDouble(money.getText().toString());
            map.put("foodName",name.getText().toString());
            map.put("address",address.getText().toString());
            map.put("money",money.getText().toString());
            mDeviceHeaderMap.put(address.getText().toString(),money.getText().toString());
            houseRes.add(map);
            //jsonArray = JSONArray.fromObject(map);
        }
        total.setText("总价为 "+sum+" 元");
    }


}

