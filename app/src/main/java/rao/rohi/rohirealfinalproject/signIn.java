package rao.rohi.rohirealfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class signIn extends AppCompatActivity {
    private TextInputEditText etMail;
    private TextInputEditText etPass;
    private Button btnSignUp;
    private Button btnSignIn;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etMail=findViewById(R.id.etEmail);
        etPass=findViewById(R.id.etPass);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnSignIn=findViewById(R.id.btnSignIn);

    }
}