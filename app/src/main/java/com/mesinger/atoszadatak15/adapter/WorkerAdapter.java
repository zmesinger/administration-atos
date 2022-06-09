package com.mesinger.atoszadatak15.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mesinger.atoszadatak15.R;
import com.mesinger.atoszadatak15.model.Worker;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerHolder> {
    private IOnWorkerItemClickListener listener;
    private List<Worker> workers;


    public void setWorkers(List<Worker> workers){
        this.workers = workers;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WorkerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_item, parent, false);
        return new WorkerHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull WorkerHolder holder, int position) {
        Worker currentWorker = workers.get(position);
        holder.tvName.setText(currentWorker.getName());
        holder.tvSurname.setText(currentWorker.getSurname());
        holder.tvWorkPosition.setText(currentWorker.getWorkPosition());

    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    class WorkerHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvSurname;
        TextView tvWorkPosition;



        public WorkerHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_workerName);
            tvSurname = itemView.findViewById(R.id.tv_workerSurname);
            tvWorkPosition = itemView.findViewById(R.id.tv_workPosition);


            itemView.setOnClickListener(view -> {
                int position = WorkerHolder.this.getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(workers.get(position));
                }
            });
        }


    }


    public interface IOnWorkerItemClickListener{
        void onItemClick(Worker worker);
    }

    public void setOnClickListener(IOnWorkerItemClickListener listener){
        this.listener = listener;
    }



}
