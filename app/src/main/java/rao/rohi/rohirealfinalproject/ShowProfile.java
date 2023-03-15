package rao.rohi.rohirealfinalproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rao.rohi.rohirealfinalproject.data.profile;



public class ShowProfile extends AppCompatActivity {
    private Button btnOk;
    private TextInputEditText WeightProfile;
    private TextInputEditText HeightProfile;
    private TextInputEditText AgeProfile;
    private RatingBar ActiveBar;
    boolean toupdate=true;
    profile profile1 =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        btnOk=findViewById(R.id.btnOk);
        WeightProfile=findViewById(R.id.WeightProfile);
        HeightProfile=findViewById(R.id.HeightProfile);
        AgeProfile=findViewById(R.id.AgeProfile);
        ActiveBar=findViewById(R.id.ActiveBar);

        //تشغيل مراقب لاي تغيير على قاعدة البيانات ويقوم بتنظيف المعطيات الموجه وتنزيل معلومات جديدة
        readTaskFromFirebase();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndSave();
            }
        });
    }

    public void CheckAndSave(){
        String Weight=WeightProfile.getText().toString();
        String Height=HeightProfile.getText().toString();
        String Age =AgeProfile.getText().toString();

        if(profile1==null){
            toupdate=false;
            profile1=new profile();
        }

        profile1.setWeight(Double.parseDouble(Weight));
        profile1.setLength(Integer.parseInt(Height));
        profile1.setAge(Integer.parseInt(Age));


        String owner=FirebaseAuth.getInstance().getCurrentUser().getUid();
        profile1.setOwner(owner);

        String Key="";
        if(!toupdate){
            Key=FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).getKey();
            profile1.setKey(Key);
        }
        else{
            Key=profile1.getKey();
        }
        FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).child(Key).setValue(profile1).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finish();
                            Toast.makeText(ShowProfile.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(ShowProfile.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void readTaskFromFirebase(){
        String Owner= FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("Profile").child(Owner)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            snapshot.getChildren();
            for(DataSnapshot d:snapshot.getChildren())
            {
                profile1  =d.getValue(profile.class);
                toupdate=true;
                btnOk.setText("Edit");
                WeightProfile.setText(profile1.getWeight()+"");
                HeightProfile.setText(profile1.getLength()+"");
                AgeProfile.setText(profile1.getAge()+"");
                ActiveBar.setProgress(profile1.getActive());
            }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}