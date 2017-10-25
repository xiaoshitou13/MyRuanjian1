package Myadater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by 白玉春 on 2017/10/25.
 */

public class MyZhutiPager extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
          //  myviews = itemView.findViewById(R.id.images);


        }
    }
}
