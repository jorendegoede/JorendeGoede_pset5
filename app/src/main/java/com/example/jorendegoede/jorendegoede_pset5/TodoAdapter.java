package com.example.jorendegoede.jorendegoede_pset5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 12-5-2017.
 */

public class TodoAdapter extends ArrayAdapter<ToDo> {

    Context context;
    int layoutResourceId;
    ArrayList<ToDo> data = null;

    public TodoAdapter(Context context, int layoutResourceId, ArrayList<ToDo> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    //1 How many items to be displayed
    @Override
    public int getCount() {
        return data.size();
    }

    //2 returns an item to be displaced
    @Override
    public ToDo getItem(int position) {
        return data.get(position);
    }

    //3 Defines unique ID for each row in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4 Creates a view to be used as a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.row, null);

        // tasktitle
        TextView textView = (TextView) v.findViewById(R.id.task_title);
        textView.setText(data.get(position).getTodo());
        Log.d("bool test", Boolean.toString(data.get(position).getChecked()));



        if (data.get(position).getChecked() == true) {

            Log.d("komen we hier ooit", "test" );

            textView.setTextColor(0xff404d); //red

        }
        else {
            textView.setTextColor(0xFF000000); //black
            Log.d("false", "test");
        }


        return v;
    }
}