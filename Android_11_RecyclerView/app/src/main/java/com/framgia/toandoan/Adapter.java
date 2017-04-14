package com.framgia.toandoan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by framgia on 30/03/2017.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_1 = 1;
    private int TYPE_2 = 2;
    private int TYPE_3 = 3;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
        if (viewType ==1){
            return new ViewHolder1(,,,,)
        }else if (viewType==2){
            return new ViewHolder2();
        }else return new ViewHolder3();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position)==1){
        ((ViewHolder1)holder)
    }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        public ViewHolder1(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }
}
