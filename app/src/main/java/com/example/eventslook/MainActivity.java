package com.example.eventslook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.eventslook.Consts.URL_GET_EVENTS;

public class MainActivity extends AppCompatActivity {
    TextView isAvailable, dateEvent, title, description, descText;


    private RecyclerView rv;
    private ArrayList<Data> events = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.animate();



        LoaderManager.getInstance(this).initLoader(1, null, new MyLoaderCallback());


//        initData();
        rv = findViewById(R.id.rv);
        EventAdapter eventAdapter = new EventAdapter(this, events);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }

    class MyLoaderCallback implements LoaderManager.LoaderCallbacks<String>{

        @NonNull
        @NotNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
            return new MyAsyncTaskLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(@NonNull @NotNull Loader<String> loader, String data) {
            Gson gson = new Gson();
            Data obj = gson.fromJson(data, Data.class);
//            Data newObj = null;
//            newObj.setFullname(obj.getFullname());
//            newObj.setDescription(obj.getDescription());
//            newObj.setEnddate(obj.getEnddate());
//            newObj.setStartdate(obj.getStartdate());
            events.add(new Data(obj.getCourseId(),obj.getFullname(), obj.getCategory(), obj.getStartdate(), obj.getEnddate(), obj.getDescription(), obj.getImage(), obj.getOrganizers()));
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {

        }
    }

    private void initData(){
        events.add(new Data ("123","Очень-очень длинное имя", "2021", "1579136400", "0", "бэээээээээээээээээээээээээээээээээээээээээээээээээээээээээээээээ", "123", "Балдаев Владимир"));
    }

}