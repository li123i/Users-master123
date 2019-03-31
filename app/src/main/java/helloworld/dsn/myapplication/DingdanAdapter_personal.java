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

public class DingdanAdapter_personal extends RecyclerView.Adapter<DingdanAdapter_personal.ViewHolder> {
    private List<Dingdan_personal>mDingdan_personallist;
    private String s=null;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView techang_person;
        Button Ok;
        Button Cancel;
        View person_view;
        public ViewHolder(View view){
            super(view);
            techang_person=(TextView)view.findViewById(R.id.show_personal);
            Ok=(Button) view.findViewById(R.id.image_OK_person);
            Cancel=(Button) view.findViewById(R.id.image_cancel_person);
        }
    }
    public DingdanAdapter_personal(List<Dingdan_personal> list){
        mDingdan_personallist=list;
    }

    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_show_person,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        /***
         * 确定完成订单并评分
         * **/
        holder.Ok.setOnClickListener(new View.OnClickListener() {
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
                        Dingdan_personal dingdan=mDingdan_personallist.get(position);
                        String s=dingdan.getName();
                        int zero=s.indexOf(' ',0);
                        Intent intent = new Intent();
                        intent.setClass(viewGroup.getContext(), Main2Activity_star.class);
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
                Dingdan_personal dingdan=mDingdan_personallist.get(position);
                String s=dingdan.getName();
                int zero=s.indexOf(' ',0);
                int zero1=s.indexOf(' ',zero+1);
                String s2=s.substring(zero+5,zero1);
                Intent intent = new Intent();
                intent.setClass(viewGroup.getContext(), Main2Activity_delete.class);
                String s1=s.substring(3,zero);
                intent.putExtra("num",s1);
                intent.putExtra("receive",s2);
                viewGroup.getContext().startActivity(intent);
                ((Activity) viewGroup.getContext()).finish();
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Dingdan_personal dingdan_personal=mDingdan_personallist.get(i);
        viewHolder.techang_person.setText(dingdan_personal.getName());

    }

    @Override
    public int getItemCount() {
        return mDingdan_personallist.size();
    }

}
