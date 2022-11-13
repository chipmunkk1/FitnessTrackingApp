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
 * sign up activity
 */
public class SignUp extends AppCompatActivity {
    private TextInputEditText etEmail2,etPass2,etConfirmPass;
    private Button btnCancel,btnSave;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail2=findViewById(R.id.etEmail2);
        etPass2=findViewById(R.id.etPass2);
        etConfirmPass=findViewById(R.id.etConfirmPass);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave=findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndSave();

            }
        });



    }

    private void CheckAndSave() {
        String signUpMail=etEmail2.getText().toString();
        String signUpPass=etPass2.getText().toString();
        String confirmpass=etConfirmPass.getText().toString();
        boolean isOk=true;

        if(signUpMail.length()==0){
            etEmail2.setError("Enter a New Email");
            isOk=false;
        }
        if(signUpPass.length()==0){
            etPass2.setError("Enter a New Password");
            isOk=false;
        }
        if(signUpMail.indexOf('@')<=0){
            etEmail2.setError("Unvalid Email!");
            isOk=false;
        }
        if(signUpPass.length()<7&& signUpPass.length()>0){
            etPass2.setError("Short password!");
            isOk=false;
        }
        if(signUpPass.equals(confirmpass)==false){
            etConfirmPass.setError("No Match in password");
            isOk=false;
        }



        if(isOk){
            //used for signing in, signing up and sign out
            FirebaseAuth auth = FirebaseAuth.getInstance();
            //create a new user with email and password
            auth.createUserWithEmailAndPassword(signUpMail,signUpPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                /**
                 * event handler when the mission is completed (create account)
                 * the task is an information about the event
                 */
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){    //check if the mission (built account) is successful
                        /*
                        if the email and password are valid then the email and the pass are saved in the firebase
                        and a text will pop up in the bottom of the screen says "password and email are saved"
                         */
                        Toast.makeText(SignUp.this, "password and email are saved", Toast.LENGTH_SHORT).show();
                        finish(); // when  i press the button save the current screen will close and returns to the previous screen

                    }
                    else{
                        Toast.makeText(SignUp.this, "something wrong in password or Email"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }
}