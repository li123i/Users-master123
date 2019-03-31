package helloworld.dsn.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class DingdanAdapter extends RecyclerView.Adapter<DingdanAdapter.ViewHolder> {
    private List<Dingdan> mDingdan;
    private String data;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View dingdanView;//添加点击事件
        Button Image;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            Image=(Button) itemView.findViewById( R.id.image_kuaidi );
            name=(TextView)itemView.findViewById( R.id.show_kuaidi );
        }
    }
    public DingdanAdapter(List<Dingdan> dingdan){
        mDingdan=dingdan;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dingdan dingdan=mDingdan.get( position );
        holder.name.setText(dingdan.getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( parent.getContext() ).inflate(
                R.layout.layout_show,parent,false
        );
        final ViewHolder holder=new ViewHolder(view);
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                dialog.setTitle("这是一个提示框");
                dialog.setMessage("您确定接取该订单吗？");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position=holder.getAdapterPosition();
                        Dingdan dingdan=mDingdan.get(position);
                        String s=dingdan.getName();
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
        return holder;
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
                            url("http://47.106.159.165:8080/specialty/receiveOrder").
                            post(formbody).build();
                    Response response=client.newCall(request).execute();
                    String s=response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }}
        }).start();
    }


    @Override
    public int getItemCount() {
        return mDingdan.size();
    }
    public long getItemId(int position) {
        return position;
    }
}
