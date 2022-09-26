package rao.rohi.rohirealfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class splashscreen extends AppCompatActivity {
    private ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView2=findViewById(R.id.imageView2);
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashscreen.this,signIn.class);
                startActivity(i);                   //to start the i witch is going from splash activity to signIn
                finish();                          //to close the current activity
            }
        };
        h.postDelayed(r,3000);









    };

    }
