package itesm.ronald.emprendedor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Ronald on 9/8/2015.
 */
public class PromoAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<Promo> promos;
    private Activity activity;
    protected PromoActivity promoActivity;

    public PromoAdapter(Activity activity, PromoActivity promoActivity, ArrayList<Promo> promos){
        this.activity = activity;
        this.promoActivity = promoActivity;
        this.promos = promos;
    }

    @Override
    public int getCount() {
        return promos.size();
    }

    @Override
    public Object getItem(int position) {
        return promos.get(position);
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
        Promo promo = promos.get(position);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        //ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        TextView title = (TextView) convertView.findViewById(R.id.textTitle);
        TextView description = (TextView) convertView.findViewById(R.id.textDescription);
        TextView price = (TextView) convertView.findViewById(R.id.textPrice);

        title.setText(promo.getTitle());
        description.setText(promo.getShortDescription());
        price.setText("$" + promo.getPrice());

        /*
        if(this.promoActivity.photos[position] != null){

            image.setImageBitmap(this.promoActivity.photos[position]);
            image.setVisibility(View.VISIBLE);
            //progressBar.setVisibility(View.GONE);
        }else{
            image.setVisibility(View.GONE);
            //progressBar.setVisibility(View.VISIBLE);
        }
        */

        return convertView;
    }
}
