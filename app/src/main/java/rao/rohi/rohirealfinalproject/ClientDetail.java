package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import rao.rohi.rohirealfinalproject.data.profile;

/**
 * client detail
 */
public class ClientDetail extends AppCompatActivity {
    private TextView txtView,txtView1;
    private TextInputEditText etWeight,etHeight,etAge;
    private RatingBar ActiveBar;
    private Button btnBulk,btnCut;
    private RadioButton radioButtonMale,radioButtonFemale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        radioButtonMale=findViewById(R.id.radioButtonMale);
        radioButtonFemale=findViewById(R.id.radioButtonFemale);
        txtView=findViewById(R.id.txtView);
        etWeight=findViewById(R.id.etWeight);
        etHeight=findViewById(R.id.etHeight);
        etAge=findViewById(R.id.etAge);
        txtView1=findViewById(R.id.txtView1);
        ActiveBar=findViewById(R.id.ActiveBar);
        btnBulk=findViewById(R.id.btnBulk);
        btnCut=findViewById(R.id.btnCut);



        btnBulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndSave();
            }
        });

        btnCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndSave2();
            }
        });

    }

    public void CheckAndSave2(){
        String Height=etHeight.getText().toString();

        String Age= etAge.getText().toString();

        String Weight=etWeight.getText().toString();

        int Active =ActiveBar.getProgress();

        boolean isOk=true;
        if(Height.length()==0){
            etHeight.setError("Enter you Length");
            isOk=false;
        }

        if(Weight.length()==0){
            etWeight.setError("Enter you Weight");
            isOk=false;
        }

        if(Age.length()==0){
            etAge.setError("Enter your Age");
            isOk=false;
        }

        if(isOk==false){
            Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
        }

        else {
            int L = Integer.parseInt(Height);
            int A = Integer.parseInt(Age);
            int W = Integer.parseInt(Weight);
            int Act = ActiveBar.getProgress();
            if (W > 200 || W < 40) {
                etWeight.setError("Enter a Valid Weight!");
                isOk = false;
            }
            if (W == 0) {
                etWeight.setError("Enter your Weight");
                isOk = false;
            }

            if (L > 220 || L < 130) {
                etHeight.setError("Enter a Valid Length");
                isOk = false;
            }
            if (L == 0) {
                etHeight.setError("Enter your Length");
                isOk = false;
            }

            if (A > 80 || A < 12) {
                if (A > 80) {
                    etAge.setError("You are too Old");
                    isOk = false;
                } else if (A < 12) {
                    etAge.setError("You are too young");
                    isOk = false;
                }
            }

            if (A == 0) {
                etAge.setError("Enter your Age");
                isOk = false;
            }


            if (!isOk) {
                Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
            } else {
                profile p = new profile();
                p.setWeight(W);
                p.setLength(L);
                p.setAge(A);
                p.setActive(Act);
                //استخراج الرقم المميز لل Uid
                String owner = FirebaseAuth.getInstance().getCurrentUser().getUid();
                p.setOwner(owner);
                //أستخراج الرقم المميز للمهمة
                String key = FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).push().getKey();
                p.setKey(key);

                //حفظ بالخادم
                FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).child(key).setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(ClientDetail.this, MainActivity.class);
                            startActivity(i);
                            Toast.makeText(ClientDetail.this, "Detail Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ClientDetail.this, "Added failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


    public void CheckAndSave(){
        //converting string to integer:
        String Height=etHeight.getText().toString();
        String Age= etAge.getText().toString();
        String Weight=etWeight.getText().toString();

        boolean isOk=true;
        if(Height.length()==0){
            etHeight.setError("Enter you Length");
            isOk=false;
        }

        if(Weight.length()==0){
            etWeight.setError("Enter you Weight");
            isOk=false;
        }

        if(Age.length()==0){
            etAge.setError("Enter your Age");
            isOk=false;
        }

        if(isOk==false){
            Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
        }

        else{
            int L = Integer.parseInt(Height);
            int A = Integer.parseInt(Age);
            int W = Integer.parseInt(Weight);
            int Act =ActiveBar.getProgress();
            if(W>200 ||W<40 ){
                etWeight.setError("Enter a Valid Weight!");
                isOk=false;
            }
            if(W==0){
                etWeight.setError("Enter your Weight");
                isOk=false;
            }

            if(L>220 || L<140){
                etHeight.setError("Enter a Valid Length");
                isOk=false;
            }
            if(L==0){
                etHeight.setError("Enter your Length");
                isOk=false;
            }

            if(A>80|| A<12){
                if(A>80) {
                    etAge.setError("You are too Old");
                    isOk=false;
                }
                else if(A<12) {
                    etAge.setError("You are too young");
                    isOk = false;
                }
            }
            if(A==0){
                etAge.setError("Enter your Age");
                isOk = false;
            }


            if (!isOk) {
                Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
            } else {
                profile p = new profile();
                p.setWeight(W);
                p.setLength(L);
                p.setAge(A);
                p.setActive(Act);
                //استخراج الرقم المميز لل Uid
                String owner = FirebaseAuth.getInstance().getCurrentUser().getUid();
                p.setOwner(owner);
                //أستخراج الرقم المميز للمهمة
                String key = FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).push().getKey();
                p.setKey(key);

                //حفظ بالخادم
                FirebaseDatabase.getInstance().getReference().child("Profile").child(owner).child(key).setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(ClientDetail.this, MainActivity.class);
                            startActivity(i);
                            Toast.makeText(ClientDetail.this, "Detail Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ClientDetail.this, "Added failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}