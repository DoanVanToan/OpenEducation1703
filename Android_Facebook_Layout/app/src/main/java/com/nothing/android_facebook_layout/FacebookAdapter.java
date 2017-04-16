package com.nothing.android_facebook_layout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by THM on 4/6/2017.
 */
public class FacebookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StatusItem> mStatusItems;

    public FacebookAdapter(List<StatusItem> statusItems) {
        mStatusItems = statusItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) return 0;
        else if (position == 1) return 1;
        else return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_border, parent, false);
                return new ViewHodlerBorder(v);
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_own_status, parent, false);
                return new ViewHolderOwn(v);
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_status, parent, false);
                return new ViewHolderStatus(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 2) {
            StatusItem item = mStatusItems.get((position - 3) / 2);
            ViewHolderStatus holderStatus = (ViewHolderStatus) holder;
            holderStatus.bindData(item);
        }
    }

    @Override
    public int getItemCount() {
        // 1 own status + each space line for each status of others
        return (mStatusItems.size() + 1) * 2;
    }

    public class ViewHodlerBorder extends RecyclerView.ViewHolder {
        public ViewHodlerBorder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderOwn extends RecyclerView.ViewHolder {
        private Button mButton;
        private ImageView mImageView;

        public ViewHolderOwn(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.button_write_status);
            mImageView = (ImageView) itemView.findViewById(R.id.image_own_avatar);
        }

        public void bindData() {
            //// TODO: 4/6/2017
        }
    }

    public class ViewHolderStatus extends RecyclerView.ViewHolder {
        private TextView mTextName, mTextTime, mTextStatus;
        private ImageView mImageAva, mImageStatus;

        public ViewHolderStatus(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextTime = (TextView) itemView.findViewById(R.id.text_time);
            mTextStatus = (TextView) itemView.findViewById(R.id.text_status);
            mImageAva = (ImageView) itemView.findViewById(R.id.image_avatar);
            mImageStatus = (ImageView) itemView.findViewById(R.id.image_status);
        }

        public void bindData(StatusItem item) {
            if (item != null) {
                mTextName.setText(item.getName());
                mTextTime.setText(item.getTime());
                mTextStatus.setText(item.getStatus());
                mImageAva.setImageResource(item.getAvatar());
                mImageStatus.setImageResource(item.getImage());
            }
        }
    }
}
