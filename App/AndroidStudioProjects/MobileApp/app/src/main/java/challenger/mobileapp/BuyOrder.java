package challenger.mobileapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BuyOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_order);
        Toolbar tool = findViewById(R.id.BuyOrderToolbar);
        setSupportActionBar(tool);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Buy Order Activity");
        bar.setDisplayHomeAsUpEnabled(true);
    }

}
