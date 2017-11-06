package actualsolusi.com.samplesqliteb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import models.MyWish;

/**
 * Created by erick on 06/11/2017.
 */

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.MyViewHolder> {
    private List<MyWish> listWish;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView)itemView.findViewById(R.id.tvContent);
        }
    }

    public WishAdapter(List<MyWish> listWish){
        this.listWish = listWish;
    }

    @Override
    public WishAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wish,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WishAdapter.MyViewHolder holder, int position) {
        MyWish currWish = listWish.get(position);
        holder.tvTitle.setText(currWish.getTitle());
        holder.tvContent.setText(currWish.getContent());
    }

    @Override
    public int getItemCount() {
        return listWish.size();
    }


}
