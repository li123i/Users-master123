package helloworld.dsn.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class eatDingdanPersonalAdapter extends RecyclerView.Adapter<eatDingdanPersonalAdapter.ViewHolder> {
    private List<eatDingdanPersonal> mlist;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView show;
        Button OK;
        Button Cancel;
        View eatpersonalview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eatpersonalview=itemView;
            show=(TextView)itemView.findViewById(R.id.show_eatpersonal);
            OK=(Button)itemView.findViewById(R.id.eat_OK_person);
            Cancel=(Button)itemView.findViewById(R.id.eat_cancel_person);
        }
    }
    public eatDingdanPersonalAdapter(List<eatDingdanPersonal> list){
        mlist=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_showeatpersonal,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        /***
         * 确定完成订单并评分
         * **/
        holder.OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                dialog.setTitle("这是一个提示框");
                dialog.setMessage("您确定该订单已完成吗？");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position=holder.getAdapterPosition();
                        eatDingdanPersonal dingdan=mlist.get(position);
                        String s=dingdan.getShow();
                        int zero=s.indexOf(' ',0);
                        Intent intent = new Intent();
                        intent.setClass(viewGroup.getContext(), Main2Activity_eatstar.class);
                        String s1=s.substring(3,zero);
                        intent.putExtra("num",s1);
                        viewGroup.getContext().startActivity(intent);
                        ((Activity) viewGroup.getContext()).finish();


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
        /**
         * *
         * 删除订单
         * */
        holder.Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                eatDingdanPersonal dingdan=mlist.get(position);
                String s=dingdan.getShow();
                int zero=s.indexOf(' ',7);
                int zero1=s.indexOf(' ',zero+1);//第二个空格
                String s3=s.substring(zero+1,zero1);//订单状态
                String s2=s.substring(6,zero);//订单号
                Intent intent = new Intent();
                intent.setClass(viewGroup.getContext(), Main2Activity_eatdelete.class);
                String s1=s.substring(3,zero);
                intent.putExtra("num",s2);
                intent.putExtra("receive",s3);
                viewGroup.getContext().startActivity(intent);
                ((Activity) viewGroup.getContext()).finish();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        eatDingdanPersonal eatDingdanpersonal=mlist.get(i);
        viewHolder.show.setText(eatDingdanpersonal.getShow());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
