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

        retrieveJSON();
    }

    public void retrieveJSON(){
        String jsonURL = "http://10.43.15.130:8000/wishes";
        WishJReq jreq = new WishJReq(this, this.adapter, this.wishes);
        jreq.execute(jsonURL);
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
