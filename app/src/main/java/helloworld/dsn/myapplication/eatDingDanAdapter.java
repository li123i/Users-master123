package helloworld.dsn.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class eatDingDanAdapter extends RecyclerView.Adapter<eatDingDanAdapter.ViewHolder>{
    private List<eatDingDan> mlist;
    private String data;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View eatdingdanView;//添加点击事件
        Button get_eat;
        TextView name_eat;
        public ViewHolder( View itemView) {
            super(itemView);
            get_eat=(Button)itemView.findViewById(R.id.image_eat);
            name_eat=(TextView)itemView.findViewById(R.id.show_eat);
        }
    }
    public eatDingDanAdapter (List<eatDingDan> list){
        mlist=list;
    }
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_eatshow,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.get_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                dialog.setTitle("这是一个提示框");
                dialog.setMessage("您确定要获取该订单吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position=holder.getAdapterPosition();
                        eatDingDan dingdan=mlist.get(position);
                        String s=dingdan.getName_eat();
                        int zero=s.indexOf(' ',0);
                        send(s.substring(3,zero));
                        notifyItemRemoved(position);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        return  holder;
    }

    private void send(final  String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    data=MainActivity.data1;
                    data=data.substring( 1,data.length()-1 );
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formbody=new FormBody.Builder().
                            add("accessToken",data).
                            add("orderNum",s )
                            .build();
                    Request request=new Request.Builder().
                            url("http://47.106.159.165:8080/food/receiveOrder").
                            post(formbody).build();
                    Response response=client.newCall(request).execute();
                    String s=response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
        }).start();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        eatDingDan eatDingDan=mlist.get(i);
        viewHolder.name_eat.setText(eatDingDan.getName_eat());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
