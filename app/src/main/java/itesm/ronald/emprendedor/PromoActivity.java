package itesm.ronald.emprendedor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PromoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView list;
    private ArrayList<Promo> promos = new ArrayList<>();
    protected PromoAdapter adapter;
    public Bitmap photos[] = new Bitmap[128];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.list = (ListView)findViewById(R.id.listView);

        try {
            JSONObject result = new JSONObject(loadJSONFromAsset());
            JSONArray promos = result.getJSONArray("Promos");
            for (int i = 0; i < promos.length(); i++) {
                JSONObject promo = promos.getJSONObject(i);
                this.promos.add(
                        new Promo(
                                promo.getString("Title"),
                                promo.getString("ShortDescription"),
                                promo.getString("Description"),
                                promo.getString("Location"),
                                promo.getString("Phone"),
                                promo.getString("Picture"),
                                promo.getString("Price")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        this.adapter = new PromoAdapter(this, this, this.promos);
        this.list.setAdapter(this.adapter);

        /*
        for (int i=0; i < this.promos.size(); i++){
            String picture = promos.get(i).getPicture();
            new DownloadImageTask(this, i).execute(picture);
        }
         */
        this.list.setOnItemClickListener(this);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("Promos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this ,PromoDetailActivity.class);
        intent.putExtra("Title", promos.get(position).getTitle());
        intent.putExtra("Description", promos.get(position).getDescription());
        intent.putExtra("Location", promos.get(position).getLocation());
        intent.putExtra("Phone", promos.get(position).getPhone());
        intent.putExtra("Picture", promos.get(position).getPicture());
        intent.putExtra("Price", promos.get(position).getPrice());
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private int position;
        private PromoActivity activity;

        public DownloadImageTask(PromoActivity activity, int position) {
            this.position = position;
            this.activity = activity;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap image = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                image = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return image;
        }

        protected void onPostExecute(Bitmap result) {
            this.activity.photos[this.position] = result;
            this.activity.adapter.notifyDataSetChanged();
        }
    }
}
