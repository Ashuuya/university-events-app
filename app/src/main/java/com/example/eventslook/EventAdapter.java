package com.example.eventslook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Data> events;


    EventAdapter(Context context, List<Data> events){
        this.events = events;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data event = events.get(position);
        holder.isAvailable.setText(event.getEnddate());
        holder.dateEvent.setText(event.getStartdate());
        holder.title.setText(event.getFullname());
        holder.descText.setText(event.getDescription());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView isAvailable, dateEvent, title, description, descText;
        public ViewHolder(View view) {
            super(view);
            isAvailable = view.findViewById(R.id.isAvailable);
            dateEvent = view.findViewById(R.id.dateEvent);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            descText = view.findViewById(R.id.descText);


        }
    }

}
