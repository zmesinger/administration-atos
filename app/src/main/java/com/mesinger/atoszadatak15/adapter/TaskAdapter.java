package com.mesinger.atoszadatak15.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.model.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter {
    private List<Task> tasks = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewStatus;

        public TaskHolder(View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            textViewStatus = itemView.findViewById(R.id.tv_status);
        }


    }

}
