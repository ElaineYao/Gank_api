package com.elainedv.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Elaine on 18/2/19.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                                    .url(address)
                                    .build();
        client.newCall(request).enqueue(callback);
    }
}
