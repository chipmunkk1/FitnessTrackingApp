package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.ItmSettings){
            Intent i = new Intent(MainActivity.this,Settings.class);
            startActivity(i);
        }

        else if(item.getItemId()==R.id.ItmSignOut){
            //to sign out from ur account
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        else if (item.getItemId()==R.id.ItmHistory){
            Intent i = new Intent(MainActivity.this,History.class);
            startActivity(i);

        }
        return true;
    }


}
