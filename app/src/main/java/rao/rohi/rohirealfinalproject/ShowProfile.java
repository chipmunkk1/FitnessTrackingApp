package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        btnOk=findViewById(R.id.btnOk);
        WeightProfile=findViewById(R.id.WeightProfile);
        HeightProfile=findViewById(R.id.HeightProfile);
        AgeProfile=findViewById(R.id.AgeProfile);
        ActiveBar=findViewById(R.id.ActiveBar);

        //تشغيل مراقب لاي تغيير على قلاعدة البيانات ويقوم بتنظيف المعطيات الموجه وتنزيل معلومات جديدة
        readTaskFromFirebase();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void readTaskFromFirebase(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        String Owner= FirebaseAuth.getInstance().getCurrentUser().getUid();

        reference.child("Profile").child(Owner).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            snapshot.getChildren();
            for(DataSnapshot d:snapshot.getChildren())
            {
                profile p =d.getValue(profile.class);
                WeightProfile.setText(p.getWeight()+"");
                HeightProfile.setText(p.getLength()+"");
                AgeProfile.setText(p.getAge()+"");
                ActiveBar.setProgress(p.getActive());

            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }








}