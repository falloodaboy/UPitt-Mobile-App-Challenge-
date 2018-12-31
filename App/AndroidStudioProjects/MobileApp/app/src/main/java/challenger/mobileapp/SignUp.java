package challenger.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
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


public class SignUp extends AppCompatActivity {
        private Toolbar tool;
        private TextView passref;
        private TextView emailref;
        private TextView confirmref;
        private Button signup;
        private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tool = findViewById(R.id.SignUpToolbar);
        setSupportActionBar(tool);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("SignUp Activity");
        bar.setDisplayHomeAsUpEnabled(true);
        passref = findViewById(R.id.signuppassword);
        emailref = findViewById(R.id.signupemail);
        confirmref = findViewById(R.id.confirm);
        signup = findViewById(R.id.signupbutton);
        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart(){
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(passref.getText().toString().equals(confirmref.getText().toString()))
                {
                    CreateUser();
                }
                else {
                    passref.setError("Password doesn't match");
                    confirmref.setError("Password doesn't match");
                }
            }
        };
        signup.setOnClickListener(listener);
    }
    public void CreateUser() {
        auth.createUserWithEmailAndPassword(emailref.getText().toString(), passref.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent inter = new Intent(SignUp.this, BuyOrder.class);
                            inter.putExtra("EMAIL", emailref.getText().toString());
                            startActivity(inter);
                        }
                        else{
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "User not created", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
    }
    @Override
    public void onStop(){
        super.onStop();
        auth.signOut();
    }
}
