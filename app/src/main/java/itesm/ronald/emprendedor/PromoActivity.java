package itesm.ronald.emprendedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class PromoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ArrayList<Promo> promos = new ArrayList<>();
    protected PromoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView list = (ListView)findViewById(R.id.listView);

        this.adapter = new PromoAdapter(this, this, this.promos);
        list.setAdapter(this.adapter);
        list.setOnItemClickListener(this);

        retrieveJSON();
    }

    public void retrieveJSON(){
        String jsonURL = "http://10.43.15.130:8000/promos";
        PromoJReq jreq = new PromoJReq(this, this.adapter, this.promos);
        jreq.execute(jsonURL);
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

}
