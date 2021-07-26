package sg.edu.rp.c346.id20018318.myndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> versionList;

    public CustomAdapter( Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvYr = rowView.findViewById(R.id.tvYr);
        TextView tvRating = rowView.findViewById(R.id.tvRating);
        TextView tvSinger = rowView.findViewById(R.id.tvSinger);

        Song currentVersion = versionList.get(position);

        tvName.setText(currentVersion.getTitle());
        tvYr.setText(String.valueOf(currentVersion.getYear()));
        tvSinger.setText(currentVersion.getSingers());
        tvRating.setText(currentVersion.toString());

        return rowView;
    }
}
