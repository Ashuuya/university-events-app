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

import com.example.eventslook.model.Data;
import com.example.eventslook.model.Datalist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

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

        Loader<String> stringLoader = LoaderManager.getInstance(this).initLoader(1, null, new MyLoaderCallback());
        stringLoader.forceLoad();
//        Log.d("teststring", stringLoader.toString());

//        initData();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

//        try (FileReader reader = new FileReader(String.valueOf(stringLoader))){
//            JsonParser parser = new JsonParser();
////            JSONArray jsonArray = new JSONArray(stringLoader);
//            JsonElement obj = parser.parse(reader);
//            obj.
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        EventAdapter eventAdapter = new EventAdapter(this, events);
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
            Type collectionType = new TypeToken<Collection<Data>>(){}.getType();
            Collection<Data> eventslist = gson.fromJson(data, collectionType);
//            Datalist eventslist = gson.fromJson(data, Datalist.class);
//            Datalist newlist = eventslist.forEach();
            Log.d("testviewobj", ""+eventslist.toString());


//            String json = "[{\"\":\"\"},..., {\"\":\"\"}]";
//            Datalist eventslist = gson.fromJson(json, new TypeToken<Datalist>() {}.getType());
//            Datalist objlist = gson.fromJson(data, Datalist.class);
//            System.out.println(objlist.toString());


//            Data newObj = null;
//            newObj.setFullname(obj.getFullname());
//            newObj.setDescription(obj.getDescription());
//            newObj.setEnddate(obj.getEnddate());
//            newObj.setStartdate(obj.getStartdate());
//            events.add(new Data(obj.getCourseId(),obj.getFullname(), obj.getCategory(), obj.getStartdate(), obj.getEnddate(), obj.getDescription(), obj.getImage(), obj.getOrganizers()));
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