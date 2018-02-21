package com.elainedv.Classification;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.elainedv.Gank.R;
import com.elainedv.Utils.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Elaine on 18/2/20.
 */

public class HttpTask extends AsyncTask<String ,Void,Boolean> {

    private final String TAG="----"+HttpTask.class+"-----";
    private List<ResponseBean.ResultBean> resultBeanList;
    private RecyclerView rv;
    private ProgressDialog progressdialog;

    public HttpTask(RecyclerView rv, ProgressDialog progressdialog){
        this.rv=rv;
        this.progressdialog=progressdialog;
    }

    @Override
    protected void onPreExecute() {
        progressdialog.show();
    }

    @Override
    protected Boolean doInBackground(String... voids) {
        Boolean flag=true;
        OkHttpClient client=new OkHttpClient();
        Log.i(TAG,voids[0]);
        Request request=new Request.Builder().url(voids[0]).build();
        try {
            //Log.i(TAG,"yes");
            Response response =client.newCall(request).execute();
            String responseData=response.body().string();
            Gson gson=new Gson();
            ResponseBean responseBean=gson.fromJson(responseData,ResponseBean.class);
            //Log.i(TAG,"parseJson");
            resultBeanList=responseBean.getResults();
          //  Log.i(TAG,"images: "+resultBeanList.get(0).getImages().get(0));
            Log.i(TAG,"Thread name:"+Thread.currentThread().getName());
        } catch (IOException e) {
            Log.i(TAG,"error");
            flag=false;
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        progressdialog.setMessage("Downloading...");
        progressdialog.setIndeterminate(true);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressdialog.dismiss();
        if(aBoolean==true){
            AdapterRV adapter=new AdapterRV(resultBeanList);
            rv.setAdapter(adapter);
        }
    }
}
