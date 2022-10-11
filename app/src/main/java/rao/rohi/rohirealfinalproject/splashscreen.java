package rao.rohi.rohirealfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class splashscreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                FirebaseAuth auth =FirebaseAuth.getInstance();
                if(auth.getCurrentUser()==null){
                    Intent i = new Intent(splashscreen.this,signIn.class);
                    startActivity(i);                   //to start the i witch is going from splash activity to signIn
                    finish();           //to close the current activity
                }
                else{
                    Intent i = new Intent(splashscreen.this,MainActivity.class);
                    startActivity(i);                   //to start the i witch is going from splash activity to signIn
                    finish();           //to close the current activity
                }



            }
        };
        h.postDelayed(r,3000);









    };

    }
