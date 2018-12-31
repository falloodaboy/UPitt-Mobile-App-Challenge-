package challenger.mobileapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class HomePage extends AppCompatActivity {
            private MyViewModel model;
            private Button logger;
            private Button signer;
            private Button faq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        setContentView(R.layout.home_page);
        logger = findViewById(R.id.login);
        signer = findViewById(R.id.signup);
        Toolbar tool = findViewById(R.id.toolbar2);
        setSupportActionBar(tool);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("App Name");

    }

    @Override
    protected void onStart() {
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(view.getId()== R.id.signup ){
                    Intent inter = new Intent(HomePage.this, SignUp.class);
                    startActivity(inter);
                }
                else if(view.getId() == R.id.login){
                    Intent inter = new Intent(HomePage.this, Login.class);
                    startActivity(inter);
                }
            }
        };
        logger.setOnClickListener(listener);
        signer.setOnClickListener(listener);
    }


}
