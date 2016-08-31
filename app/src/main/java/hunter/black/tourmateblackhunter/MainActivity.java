package hunter.black.tourmateblackhunter;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewEventButtonActionInMainActivity(View view) {
    }

    public void currentEventsButtonAction(View view) {
        Intent currentEventsIntent = new Intent(this,AllEventListActivity.class);
        startActivity(currentEventsIntent);
    }
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}