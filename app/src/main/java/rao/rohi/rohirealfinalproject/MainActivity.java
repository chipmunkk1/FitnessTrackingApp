package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rao.rohi.rohirealfinalproject.data.StepsProfile;
import rao.rohi.rohirealfinalproject.data.profile;

/**
 * main activity
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView Calories;
    private TextView TvStepCounter,TvStepDetector,TvCalorieBurn,TvCalorieBurnNumber,TvDistance,TvDistanceNumber,calorieText;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    private int StepCount=0;



    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        readTaskFromFireBase();

        Calories=findViewById(R.id.Calories);
        TvStepCounter=findViewById(R.id.TvStepCounter);
        TvStepDetector=findViewById(R.id.TvStepDetector);
        TvCalorieBurn=findViewById(R.id.TvCalorieBurn);
        TvCalorieBurnNumber=findViewById(R.id.TvCalorieBurnNumber);
        TvDistance=findViewById(R.id.TvDistance);
        calorieText=findViewById(R.id.calorieText);
        TvDistanceNumber=findViewById(R.id.TvDistanceNumber);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            mStepCounter=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent=true;
        }else {
            TvStepCounter.setText("Counter Sensor IS Not Present");
            isCounterSensorPresent=false;
        }

    }

    /**
     * to check if there is a details in firebase to this account and shows them if so
     * (((((((.getCurrentUser()))))))))
     */
    private void readTaskFromFireBase() {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        String Owner= FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child("Steps and Calorie").child(Owner).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getChildren();
                for(DataSnapshot d:snapshot.getChildren())
                {
                    StepsProfile stepsProfile =d.getValue(StepsProfile.class);
                    TvStepCounter.setText(stepsProfile.getSteps()+"");
                    TvDistanceNumber.setText(stepsProfile.getDistance()+"");
                    TvCalorieBurnNumber.setText(stepsProfile.getCalorieBurn()+"");
                    Calories.setText((stepsProfile.getCalorieEat()-stepsProfile.getCalorieBurn())+"");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent()!=null && getIntent().hasExtra("typ1"))
        {
            Double type1 =(double) getIntent().getExtras().get("typ1");
            Calories.setText(type1+"");
        }
        else{
            if(getIntent()!=null && getIntent().hasExtra("typ2")) {
                Double type2 = (double) getIntent().getExtras().get("typ2");

                Calories.setText(type2 + "");
            }
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            sensorManager.registerListener(this, mStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
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

    //methods for sensor and steps counter
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor==mStepCounter){
        StepCount=(int) sensorEvent.values[0];
        TvStepCounter.setText(String.valueOf(StepCount));
        }

        int CalorieBurn= (int) (0.05*StepCount);
        TvCalorieBurnNumber.setText(String.valueOf(CalorieBurn));
        double Distance = (StepCount/1312.0);
        TvDistanceNumber.setText(String.valueOf(Distance+"  km"));

        CheckAndSaveSteps();

    }

    private void CheckAndSaveSteps() {
        /**
         * to save the steps , distance , calorie burn and calories eat in firebase
         *         so when an used account join and has steps ... it will show them
         */


        int StepsCounter=Integer.parseInt(String.valueOf(TvStepCounter.getText().toString()));
        double DistanceNumber=Double.parseDouble(String.valueOf(TvDistanceNumber.getText().toString()));
        int CalorieBurnNumber=Integer.parseInt(String.valueOf(TvCalorieBurnNumber.getText().toString()));
        int CalorieEat=Integer.parseInt(String.valueOf(Calories.getText().toString()));


        StepsProfile stepsProfile= new StepsProfile();


        stepsProfile.setSteps(StepsCounter);
        stepsProfile.setCalorieBurn(CalorieBurnNumber);
        stepsProfile.setDistance(DistanceNumber);
        stepsProfile.setCalorieEat(CalorieEat);

        String owner = FirebaseAuth.getInstance().getCurrentUser().getUid();
        stepsProfile.setOwner(owner);

//        String key = FirebaseDatabase.getInstance().getReference().child("Steps and Calorie").child(owner).push().getKey();
//        stepsProfile.setKey(key);

        FirebaseDatabase.getInstance().getReference().child("Steps and Calorie").child(owner).child("iam").setValue(stepsProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()==false)
                {
                    Toast.makeText(MainActivity.this, "Did not save in Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null) {
            sensorManager.unregisterListener(this, mStepCounter);
        }
    }
}
