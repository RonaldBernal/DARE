package itesm.ronald.emprendedor;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PromoJReq extends AsyncTask<String, Void, ArrayList<Promo>> {
    private Context context;
    private ProgressDialog dialog;
    private ArrayList<Promo> promos = new ArrayList<>();
    private PromoAdapter adapter;

    public PromoJReq(Context context, PromoAdapter adapter, ArrayList<Promo> promos){
        this.context = context;
        this.promos = promos;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        this.dialog = new ProgressDialog(context);
        this.dialog.setTitle("Cargando DARExperiencias");
        this.dialog.show();
    }

    @Override
    protected ArrayList<Promo> doInBackground(String... params) {

        StringBuilder sb = new StringBuilder();
        JSONObject result;

        try{
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-length", "0");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setReadTimeout(10000 /* milliseconds */);
            // Starts the query
            conn.connect();

            int status = conn.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String currentLine;
                    while ((currentLine = br.readLine()) != null) {
                        sb.append(currentLine);
                    }

                    result = new JSONObject(sb.toString());

                    JSONArray promos = result.getJSONArray("Promos");
                    for (int i = 0; i < promos.length(); i++) {
                        JSONObject promo = promos.getJSONObject(i);
                        this.promos.add(
                                new Promo(
                                        promo.getString("title"),
                                        promo.getString("short_desc"),
                                        promo.getString("long_desc"),
                                        promo.getString("location"),
                                        promo.getString("contact"),
                                        promo.getString("image_url"),
                                        promo.getString("price")
                                )
                        );
                    }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.promos;
    }


    @Override
    protected void onPostExecute(ArrayList<Promo> result){
        this.adapter.notifyDataSetChanged();
        this.dialog.dismiss();
    }

}
