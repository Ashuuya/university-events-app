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
import com.example.eventslook.model.Data;
import com.example.eventslook.model.Datalist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<Data> events = new ArrayList<>();
    ProgressBar progressBar;
    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.animate();

        Loader<String> stringLoader = LoaderManager.getInstance(this).initLoader(1, null, new MyLoaderCallback());
        stringLoader.forceLoad();

//        Log.d("teststring", stringLoader.toString());

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        eventAdapter = new EventAdapter(this, events);
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

//            Log.d("recieveid", data);

            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Data>>(){}.getType();
            Datalist newlist = new Datalist();
            newlist.setEvents(gson.fromJson(data, collectionType));
            for(int counter = 0; counter < newlist.getSize(); counter++){
                Data localevent = newlist.getEvents().get(counter);
                events.add(new Data(localevent.getCourseId(),localevent.getFullname(), localevent.getCategory(), localevent.getStartStamp(), localevent.getEndStamp(), localevent.getDescription(), localevent.getImage(), localevent.getOrganizers()));
                Log.d("desctest", (newlist.getEvents().get(counter).getDescription()));
            }
            eventAdapter.refreshList();
            eventAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onLoaderReset(@NonNull @NotNull Loader<String> loader) {}
    }
}