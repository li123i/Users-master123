package helloworld.dsn.myapplication;
/***
 *
 * 二手物品展示界面
 * ***/

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity_ershoushow extends AppCompatActivity {

    private ImageView ershou_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_ershoushow);
        ershou_show = (ImageView) findViewById(R.id.ershow_show);
        requestWritePermission();
        String url = "http://192.168.71.1:8080/img/87d95314788444859a3a58bd2a36591b.jpg";

        Glide.with(this).load(url).into(ershou_show);
        Show();
        //send();
        }
        private void send () {
            /***
             * 用get请求带参数体
             * */
            Request.Builder reqBuild = new Request.Builder().get();
            final HttpUrl.Builder urlBuilder = HttpUrl.parse("http://47.106.159.165:8080/guest/getTasks")
                    .newBuilder();
            urlBuilder.addQueryParameter("n", String.valueOf(1));
            reqBuild.url(urlBuilder.build());
            Request request = reqBuild.build();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url(urlBuilder.toString()).
                            build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            JsonElement je = new JsonParser().parse(response.body().string());
                            String s = String.valueOf(je.getAsJsonObject().
                                    get("tasks")); // 一个未转化的字符串
                            JSONArray jsonArray = null;
                            /**
                             * 获得图片的路径
                             * **/
                            try {
                                jsonArray = new JSONArray(s);
                                for (int i = 0; i < 100; i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String path = jsonObject.getString("imgsPath");
                                    int j = path.charAt('"');
                                    int j1 = path.indexOf('"', j + 1);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }).start();
        }

    private void Show() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView sh=(TextView)findViewById(R.id.error_show);
                sh.setText("error");
            }
        });
    }

    private void requestWritePermission () {
            if (ActivityCompat.checkSelfPermission(Main2Activity_ershoushow.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Main2Activity_ershoushow.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

}


