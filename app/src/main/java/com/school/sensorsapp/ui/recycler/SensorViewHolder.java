package com.school.sensorsapp.ui.recycler;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.school.sensorsapp.databinding.ItemSensorBinding;
import com.school.sensorsapp.model.Sensor;

public class SensorViewHolder extends ViewHolder {

    private final ItemSensorBinding binding;
    private final SensorClickListener listener;
    public SensorViewHolder(ItemSensorBinding binding, SensorClickListener listener) {
        super(binding.getRoot());
        this.binding=binding;
        this.listener=listener;
    }

    public void bind(Sensor item){
        binding.value.setText((int) item.getAirQuality());

    }
}
