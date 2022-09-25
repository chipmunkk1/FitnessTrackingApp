package rao.rohi.rohirealfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class splashscreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        setContentView(R.layout.activity_splashscreen);
        //start next activity screen automatically after period of time
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashscreen.this,signIn.class);
                startActivity(i);                   //to start the i wich is going from splash activity to signIn
                finish();                          //to close the current activity
            }
        };
        h.postDelayed(r,3000);            //delay the r 3 seconds
    }
}