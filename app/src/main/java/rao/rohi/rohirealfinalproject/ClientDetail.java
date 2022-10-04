package rao.rohi.rohirealfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClientDetail extends AppCompatActivity {
    private TextView txtView,txtView1;
    private TextInputEditText etWeight,etLength,etAge;
    private CheckBox CheckBoxFemale,CheckBoxMale;
    private Button btnCheck;
    private SeekBar ActiveSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        CheckBoxFemale=findViewById(R.id.CheckBoxFemale);
        CheckBoxMale=findViewById(R.id.CheckBoxMale);
        txtView=findViewById(R.id.txtView);
        etWeight=findViewById(R.id.etWeight);
        etLength=findViewById(R.id.etLength);
        etAge=findViewById(R.id.etAge);
        txtView1=findViewById(R.id.txtView1);
        ActiveSeekBar=findViewById(R.id.ActiveSeekBar);
        btnCheck=findViewById(R.id.btnCheck);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAndSave();
            }
        });

    }





    public void CheckAndSave(){
        String Weight= etWeight.getText().toString();
        String Length=etLength.getText().toString();
        String Age= etAge.getText().toString();
        String checkBox=CheckBoxFemale.getText().toString();
        boolean isOk=true;

        if(Weight.length()>3 ||Weight.length()<2 ){
            etWeight.setError("Enter a Valid Weight!");
            isOk=false;
        }
        if(Weight.length()==0){
            etWeight.setError("Enter your Length");
        }

        if(Length.length()>4 || Length.length()<3 || Length.indexOf(1)>1){
            etLength.setError("Enter a Valid Length");
            isOk=false;
        }
        if(Length.length()==0){
            etLength.setError("Enter your Length");
        }

        if(Age.length()>=3){
            if(Age.length()>=3) {
                etAge.setError("You are too Old");
                isOk=false;
            }
                else
                etAge.setError("You are too young");
                isOk = false;

        }

        if(Age.length()==0){
            etAge.setError("Enter your Age");

        }

        if(isOk){
            Intent i = new Intent(ClientDetail.this,BulkOrCut.class);
            startActivity(i);
        }

        else{
            Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
        }


    }
}