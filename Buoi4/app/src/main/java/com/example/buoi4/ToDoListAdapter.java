package com.example.buoi4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<ToDoItem> {
    int resource;
    List<ToDoItem> todoitems;

    public ToDoListAdapter(@NonNull Context context, int resource,@NonNull List<ToDoItem> todoitems)
    {
        super(context, resource, todoitems);
        this.resource = resource;
        this.todoitems = todoitems;
    }

    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent)
    {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(this.resource, null);
            v.setLongClickable(true);
        }
        ToDoItem t = getItem(position);
        if (t != null)
        {
            TextView titleTV = (TextView) v.findViewById(R.id.itTitle);
            TextView sttTV = (TextView) v.findViewById(R.id.stt);
            TextView dateTV = (TextView) v.findViewById(R.id.itDate);
            CheckBox doneCB = (CheckBox) v.findViewById(R.id.itCBox);
            int stt = position + 1;
            if (titleTV != null)
                titleTV.setText(t.getTitle());
            if (sttTV!= null)
                sttTV.setText("#" + stt);
            if (dateTV != null)
                dateTV.setText(t.getDay() + "/" + t.getMonth() + "/" + t.getYear());
            if (doneCB != null)
                doneCB.setChecked(t.getDone());
        }
        return v;
    }
}
