package itesm.ronald.emprendedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DonateActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView list;
    private ArrayList<Wish> wishes = new ArrayList<>();
    private WishAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.list = (ListView)findViewById(R.id.listView2);
        this.adapter = new WishAdapter(this, this.wishes);
        this.list.setAdapter(this.adapter);
        this.list.setOnItemClickListener(this);

        try {
            JSONObject result = new JSONObject(loadJSONFromAsset());
            JSONArray wishes = result.getJSONArray("Deseos");
            for (int i = 0; i < wishes.length(); i++) {
                JSONObject promo = wishes.getJSONObject(i);
                this.wishes.add(
                        new Wish(
                                promo.getString("Name"),
                                promo.getString("Description"),
                                promo.getString("Cost"),
                                promo.getString("Phone"),
                                promo.getString("URL")
                        )
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("Deseos.json");
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
        Intent intent = new Intent(this ,WishDetail.class);
        intent.putExtra("Name", wishes.get(position).getName());
        intent.putExtra("Description", wishes.get(position).getDescription());
        intent.putExtra("Cost", wishes.get(position).getCost());
        intent.putExtra("Phone", wishes.get(position).getPhone());
        intent.putExtra("URL", wishes.get(position).getUrl());
        startActivity(intent);
    }
}
