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
    private SeekBar ActiveSeekBar;
    private Button btnBulk,btnCut;

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
        //converting string to integer:
        String Length=etLength.getText().toString();
        double L = Double.parseDouble(Length);

        String Age= etAge.getText().toString();
        double A = Double.parseDouble(Age);

        String Weight=etWeight.getText().toString();
        double W = Double.parseDouble(Weight);


        boolean isOk=true;

        if(W>200 ||W<40 ){
            etWeight.setError("Enter a Valid Weight!");
            isOk=false;
        }
        if(Weight.length()==0){
            etWeight.setError("Enter your Length");
            isOk=false;
        }

        if(L>220 || L<140){
            etLength.setError("Enter a Valid Length");
            isOk=false;
        }
        if(Length.length()==0){
            etLength.setError("Enter your Length");
        }

        if(A>80|| A<14){
            if(A>80) {
                etAge.setError("You are too Old");
                isOk=false;
            }
            else if(A<14) {
                etAge.setError("You are too young");
                isOk = false;
            }
        }

        if(Age.length()==0){
            etAge.setError("Enter your Age");
            isOk = false;
        }

        if(isOk){
            Intent i = new Intent(ClientDetail.this,MainPage.class);
            startActivity(i);
        }

        else{
            Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
        }

    }





    public void CheckAndSave(){
        //converting string to integer:
        String Length=etLength.getText().toString();
        double L=Double.parseDouble(Length);
        L =0;

        String Age= etAge.getText().toString();
        double A=Double.parseDouble(Age);
        A =0;

        String Weight=etWeight.getText().toString();
        double W=Double.parseDouble(Weight);
        W =0;



        boolean isOk=true;

        if(W>200 ||W<40 ){
            etWeight.setError("Enter a Valid Weight!");
            isOk=false;
        }
        if(Weight.length()==0){
            etWeight.setError("Enter your Length");
            isOk=false;
        }

        if(L>220 || L<140){
            etLength.setError("Enter a Valid Length");
            isOk=false;
        }
        if(Length.length()==0){
            etLength.setError("Enter your Length");
            isOk=false;
        }

        if(A>80|| A<14){
            if(A>80) {
                etAge.setError("You are too Old");
                isOk=false;
            }
            else if(A<14) {
                etAge.setError("You are too young");
                isOk = false;
            }
        }

        if(Age.length()==0){
            etAge.setError("Enter your Age");
            isOk = false;
        }


        if(isOk){
            Intent i = new Intent(ClientDetail.this,MainPage.class);
            startActivity(i);
        }

        else{
            Toast.makeText(ClientDetail.this, "Complete your details", Toast.LENGTH_SHORT).show();
        }


    }

}