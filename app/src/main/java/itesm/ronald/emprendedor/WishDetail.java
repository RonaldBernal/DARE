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
import android.net.Uri;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WishDetail extends AppCompatActivity {
    private String title, description;
    private String cost, phone, url;
    private String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("Name");
        description = intent.getStringExtra("Description");
        phone = intent.getStringExtra("Phone");
        cost = intent.getStringExtra("Cost");
        url = intent.getStringExtra("URL");

        TextView nameView = (TextView) findViewById(R.id.titleTextView);
        TextView descriptionView = (TextView) findViewById(R.id.descriptionTextView);
        TextView phoneView = (TextView) findViewById(R.id.phoneTextView);
        TextView costView = (TextView) findViewById(R.id.priceTextView);
        ImageButton videoButton = (ImageButton) findViewById(R.id.videoButton);
        ImageButton playButton = (ImageButton) findViewById(R.id.PlayButton);

        nameView.setText(title);
        descriptionView.setText(description);
        phoneView.setText("Tel.: " + phone);
        costView.setText("Meta: $" + cost);

        String videoID = extractID(url);

        if (!videoID.equals("")){
            String imageurl = "http://img.youtube.com/vi/" + videoID + "/hqdefault.jpg";
            new DownloadImageTask(videoButton).execute(imageurl);
            videoButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        }
        else {
            videoButton.setVisibility(View.VISIBLE);
        }
    }

    public String extractID(String url){
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public void goToVideo(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void donateNow(View v){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageButton bmImage;

        public DownloadImageTask(ImageButton bmImage) {
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
