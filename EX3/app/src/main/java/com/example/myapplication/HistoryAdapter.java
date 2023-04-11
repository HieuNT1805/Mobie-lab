package com.example.myapplication;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<History>{
    public HistoryAdapter(Context context, ArrayList<History> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview, parent, false);
        }

        // Get the data item for this position
        History history = getItem(position);

        // Lookup view for data population
        TextView data = (TextView) convertView.findViewById(R.id.data);
        TextView result = (TextView) convertView.findViewById(R.id.result);
        // Populate the data into the template view using the data object
        data.setText(history.getData());
        result.setText(history.getResult());
        // Return the completed view to render on screen
        return convertView;
    }
}
