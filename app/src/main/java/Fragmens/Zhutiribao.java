package Fragmens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.bwie.com.myruanjian1.R;

public class Zhutiribao extends Fragment {


    private View v;
    private View view;
    private RecyclerView mRecy2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_zhutiribao, container, false);

        InitView();

        return v;
    }

    private void InitView() {


    }

    private void initView() {
       // mRecy2 = (RecyclerView) v.findViewById(R.id.recy2);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRecy2.setLayoutManager(gridLayoutManager);
    }
}