package com.example.eventslook;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.eventslook.Consts.URL_GET_EVENTS;

public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {
    public MyAsyncTaskLoader(@NonNull Context context){
        super(context);
    }
    @Nullable
    @Override
    public String loadInBackground() {
        Request request = new Request.Builder().url(URL_GET_EVENTS).build();

        String result = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();



        } catch (IOException e) {
            result = "No Internet";
            e.printStackTrace();
        }
        return result;
    }
}
