package helloworld.dsn.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class eatDingdanWriteAdapter extends RecyclerView.Adapter<eatDingdanWriteAdapter.ViewHolder> {

    private List<eatDingdanWrite> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private EditText name;
        private EditText money;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=(EditText)itemView.findViewById(R.id.eatwrite_cainame);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_eatwrite,viewGroup,false
        );
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        eatDingdanWrite eatDingdanWrite=mlist.get(i);
        viewHolder.name.setText(eatDingdanWrite.getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public eatDingdanWriteAdapter(List<eatDingdanWrite> list){
        mlist=list;
    }
}
