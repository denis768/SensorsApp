package com.school.sensorsapp.ui.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.school.sensorsapp.databinding.ItemSensorBinding;
import com.school.sensorsapp.model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorViewHolder> {
    private List<Sensor> items = new ArrayList<>();
    private final SensorClickListener listener;

    public SensorAdapter(SensorClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSensorBinding binding = ItemSensorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SensorViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Sensor> items){
        int count = getItemCount();
        this.items = new ArrayList<>(items);
        notifyItemRangeChanged(0, Math.max(count, getItemCount()));
    }
}
