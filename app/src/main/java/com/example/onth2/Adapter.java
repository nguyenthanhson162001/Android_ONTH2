package com.example.onth2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Task> list;

    public Adapter(Context context, int idLayout, List<Task> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout,parent,false);
        }
        TextView name = convertView.findViewById(R.id.txtItemName);
        TextView status = convertView.findViewById(R.id.txtItemStatus);
        name.setText(list.get(position).getName());
        status.setText(list.get(position).getStatus());

        return convertView;
    }
}
