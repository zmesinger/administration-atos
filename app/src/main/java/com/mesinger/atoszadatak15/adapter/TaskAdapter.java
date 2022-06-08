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


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> tasks = new ArrayList<>();
    private IOnItemClickListener listener;

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewName.setText(currentTask.getName());
        holder.textViewDescription.setText(currentTask.getDescription());
        holder.textViewStatus.setText(currentTask.getStatus());

    }

    public Task getTaskAt(int position){
        return tasks.get(position);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(tasks.get(position));
                    }
                }
            });
        }


    }


    public interface IOnItemClickListener{
        void onItemClick(Task task);
    }

    public void setOnClickListener(IOnItemClickListener listener){
        this.listener = listener;
    }

}
