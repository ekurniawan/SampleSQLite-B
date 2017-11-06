package actualsolusi.com.samplesqliteb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by erick on 06/11/2017.
 */

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public WishAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WishAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
