package itesm.ronald.emprendedor;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ronald on 9/9/2015.
 */
public class WishAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Wish> wishes;
    private Activity activity;

    public WishAdapter(Activity activity, ArrayList<Wish> wishes){
        this.activity = activity;
        this.wishes = wishes;
    }


    @Override
    public int getCount() {
        return wishes.size();
    }

    @Override
    public Object getItem(int position) {
        return wishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textTitle);
        TextView description = (TextView) convertView.findViewById(R.id.textDescription);
        TextView price = (TextView) convertView.findViewById(R.id.textPrice);

        Wish wish = wishes.get(position);
        name.setText(wish.getName());
        description.setText(wish.getDescription());
        price.setText("$" + wish.getCost());

        return convertView;
    }
}
