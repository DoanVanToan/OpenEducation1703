package com.nothing.android_facebook_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {
    private static final String BUNDLE_LIST = "FACEBOOK_LIST";
    private RecyclerView mRecycler;
    private FacebookAdapter mAdapter;
    private List<StatusItem> mStatusItems;
    public FacebookFragment() {
    }
    public static FacebookFragment newInstance(List<StatusItem> statusItems){
        FacebookFragment fragment = new FacebookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_LIST, (ArrayList)statusItems);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_status);
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new FacebookAdapter(mStatusItems);
        mRecycler.setAdapter(mAdapter);
        return v;
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() == null) return;
        mStatusItems = (List<StatusItem>) getArguments().getSerializable(BUNDLE_LIST);
    }
}
