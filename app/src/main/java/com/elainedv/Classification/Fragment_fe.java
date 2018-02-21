package com.elainedv.Classification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elainedv.Gank.R;
import com.elainedv.Utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Elaine on 18/2/16.
 */

public class Fragment_fe extends Fragment {

    private static final String address="http://gank.io/api/data/前端/50/2";
    private static final String TAG="------"+Fragment_fe.class.getSimpleName()+"------";
    private  List<ResponseBean.ResultBean> resultBeanList;
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.class_fragment,container,false);
        rv=(RecyclerView)view.findViewById(R.id.class_rv);
        ProgressDialog dialog=new ProgressDialog(getContext(),R.style.AppTheme_Dialog);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        new HttpTask(rv,dialog).execute(address);
        return view;
    }
}
