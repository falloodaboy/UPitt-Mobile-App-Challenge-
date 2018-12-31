package challenger.mobileapp;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Login extends AppCompatActivity {
        private TextView email;
        private TextView password;
        private Button but;
        private FirebaseAuth Auth;
        private Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        but = findViewById(R.id.signin);
        Auth = FirebaseAuth.getInstance();
        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Login Activity");
        bar.setDisplayHomeAsUpEnabled(true);
    }

    public void SignInUser() {

        Auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Intent inter = new Intent(Login.this, BuyOrder.class);
                   startActivity(inter);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "User " + email.getText() + " was not signed in";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == but.getId()){
                    SignInUser();
                }
            }
        };
        but.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Auth.signOut();
    }
}
