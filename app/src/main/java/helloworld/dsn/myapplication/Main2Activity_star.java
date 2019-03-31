package helloworld.dsn.myapplication;
/****
 *
 * 快递完成时，填写评价
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity_star extends AppCompatActivity  {

    private EditText write_comment;
    private String num;
    private Button OK;
    private Button Cancel;
    private RatingBar star;
    private int  star_rating;
    private final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_star);
        write_comment=(EditText)findViewById(R.id.comment);
        Intent intent=getIntent();
        num=intent.getStringExtra("num");
        OK=(Button)findViewById(R.id.OK_star);
        Cancel=(Button)findViewById(R.id.CANCEL_star);
        star=(RatingBar)findViewById(R.id.ratingbar);
        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star_rating=(int)rating;
            }
        });
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               send();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity_star.this,Main2Activity_show.class);
                startActivity(intent);
            }
        });
    }


    private void send() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                    String s=MainActivity.data1;
                    s=s.substring( 1,s.length()-1 );
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formbody=new FormBody.Builder().
                        add("accessToken", s).
                        add("orderNum", num).
                        add("comment", String.valueOf(write_comment.getText())).
                            add("score",Integer.toString(star_rating)).build();
                    Request request = new Request.Builder() .post(formbody)
                            .url("http://47.106.159.165:8080/specialty/finishOrder")
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            JsonElement je = new JsonParser().parse( response.body().string() );
                            String s= String.valueOf( je.getAsJsonObject().
                                    get( "msg" ) );
                            Show(s);
                        }
                    });

            }
        } ).start();
    }

    private void Show(final String s3) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity_star.this,s3,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

