package itesm.ronald.emprendedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class WishDetail extends AppCompatActivity {
    private String title, description;
    private String cost, phone, url;

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

        nameView.setText(title);
        descriptionView.setText(description);
        phoneView.setText("Tel.: " + phone);
        costView.setText("Meta: $" + cost);

    }

    public void goToVideo(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void donateNow(View v){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}
