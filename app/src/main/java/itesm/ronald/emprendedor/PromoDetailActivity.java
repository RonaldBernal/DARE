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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PromoDetailActivity extends AppCompatActivity {
    private String title, description, location;
    private String phone, picture, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        location = intent.getStringExtra("Location");
        phone = intent.getStringExtra("Phone");
        picture = intent.getStringExtra("Picture");
        price = intent.getStringExtra("Price");

        TextView titleView = (TextView) findViewById(R.id.titleTextView);
        TextView descriptionView = (TextView) findViewById(R.id.descriptionTextView);
        TextView locationView= (TextView) findViewById(R.id.locationTextView);
        TextView priceView = (TextView) findViewById(R.id.priceTextView);
        TextView phoneView  = (TextView) findViewById(R.id.phoneTextView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        titleView.setText(title);
        descriptionView.setText(description);
        phoneView.setText("Tel.: " + phone);
        priceView.setText("$" + price);
        locationView.setText("Lugar: " + location);

        new DownloadImageTask(imageView).execute(picture);


    }


    public void getPromo(View v){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
