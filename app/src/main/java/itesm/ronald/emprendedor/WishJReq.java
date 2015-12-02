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

public class WishJReq extends AsyncTask<String, Void, ArrayList<Wish>> {
    private Context context;
    private ProgressDialog dialog;
    private ArrayList<Wish> wishes = new ArrayList<>();
    private WishAdapter adapter;

    public WishJReq(Context context, WishAdapter adapter, ArrayList<Wish> wishes){
        this.context = context;
        this.wishes = wishes;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        this.dialog = new ProgressDialog(context);
        this.dialog.setTitle("Cargando lista de deseos");
        this.dialog.show();
    }

    @Override
    protected ArrayList<Wish> doInBackground(String... params) {

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
                    JSONArray wishes = result.getJSONArray("Deseos");
                    for (int i = 0; i < wishes.length(); i++) {
                        JSONObject promo = wishes.getJSONObject(i);
                        String tmp = promo.getString("approved");
                        if (tmp.equals("true")){
                            this.wishes.add(
                                    new Wish(
                                            promo.getString("name"),
                                            promo.getString("short_desc"),
                                            promo.getString("cost"),
                                            promo.getString("contact"),
                                            promo.getString("video_url")
                                    )
                            );
                        }
                    }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.wishes;
    }


    @Override
    protected void onPostExecute(ArrayList<Wish> result){
        this.adapter.notifyDataSetChanged();
        this.dialog.dismiss();
    }

}

