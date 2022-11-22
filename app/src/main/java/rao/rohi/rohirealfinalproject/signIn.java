package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * sign in activity
 */

public class signIn extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etPass;
    private Button btnSignUp;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signIn.this, SignUp.class);
                startActivity(i);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndSave();
            }
        });
    }
        private void checkAndSave(){
            //converting a string to integer
            //Double.parseDouble(the id for the field)

            //condition to join to the main
            String email=etEmail.getText().toString();
            String pass=etPass.getText().toString();
            boolean isOk=true;
            if(email.length()==0){
                etEmail.setError("Enter your Email");
                isOk=false;
            }
            if(pass.length()==0){
                etPass.setError("Enter your Password");
                isOk=false;
            }
            if(email.indexOf('@')<=0){
                etEmail.setError("Unvalid Email!");
                isOk=false;
            }
            if(pass.length()<7&& pass.length()>0){
                etPass.setError("Short password!");
                isOk=false;
            }

            //if all the if conditions are true then the inputs will be sent to the firebase
            if(isOk){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if all is true then go to client activity
                        if(task.isSuccessful()){
                            Toast.makeText(signIn.this, "Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signIn.this,ClientDetail.class);
                            startActivity(i);
                            finish();
                        }
                        //else show (toast) wtrong email or password and dont allow to join
                        else{
                            Toast.makeText(signIn.this, "Wrong password or Email", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }










        }



















}