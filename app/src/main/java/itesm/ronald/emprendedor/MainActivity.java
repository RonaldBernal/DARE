package itesm.ronald.emprendedor;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean doubleBackToExitPressedOnce;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        // check in shared prefs if user has logged in already

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public void selectPromos(View v){
        Intent intent = new Intent(this ,PromoActivity.class);
        startActivity(intent);
    }

    public void selectWish(View v){
        Intent intent = new Intent(this ,WishActivity.class);
        startActivity(intent);
    }

    public void selectDonations(View v){
        Intent intent = new Intent(this ,DonateActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View v){
        Intent intent = new Intent(this, PromoActivity.class);
        startActivity(intent);
    }

    /*
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Presione atrás una vez más para salir", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000);
    }
    */
}
