package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * main activity
 */
public class MainActivity extends AppCompatActivity {
    private TextView Calories;
    private FloatingActionButton returnToClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calories=findViewById(R.id.Calories);
        returnToClient=findViewById(R.id.returnToClient);

        returnToClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ClientDetail.class);
                startActivity(i);
            }
        });





    }



    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent()!=null&&getIntent().hasExtra("typ1"))
        {
            Double type1 =(double) getIntent().getExtras().get("typ1");
            Calories.setText(type1+"");
        }
        else{
            if(getIntent()!=null&&getIntent().hasExtra("typ2")) {
                Double type2 = (double) getIntent().getExtras().get("typ2");

                Calories.setText(type2 + "");
            }
        }
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
            //signing out from the account
            //1.building the Constructer of the dialog
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Sign In");
            builder.setMessage("Want To Sign Out?");
            builder.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //disappear the dialog message
                    dialogInterface.dismiss();
                    //signing out from current account
                    FirebaseAuth.getInstance().signOut();
                    //exiting the screen
                    finish();
                }
            });

            builder.setNegativeButton("Stay In", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //close the dialog's square that appears when i press the sign out item
                    dialogInterface.cancel();

                }
            });
            //now building the dialog
            AlertDialog dialog=builder.create();
            dialog.show();
        }
        else if (item.getItemId()==R.id.ItmHistory){
            Intent i = new Intent(MainActivity.this,History.class);
            startActivity(i);

        }

        else if(item.getItemId()==R.id.ItmProfile){
            Intent i =new Intent(MainActivity.this,ShowProfile.class);
            startActivity(i);
        }
        return true;
    }










}
