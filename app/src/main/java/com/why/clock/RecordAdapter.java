package com.why.clock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<Record> mRecordList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView record;

        public ViewHolder(View view) {
            super(view);
            record = (TextView) view.findViewById(R.id.time_record);
        }
    }

    public RecordAdapter(List<Record> recordList){
        mRecordList = recordList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        Record record = mRecordList.get(position);
        holder.record.setText(record.getRecord());
    }

    @Override
    public int getItemCount() {
        return mRecordList.size();
    }
}
