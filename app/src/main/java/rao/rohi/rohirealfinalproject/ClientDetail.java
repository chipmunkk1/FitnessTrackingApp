package rao.rohi.rohirealfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ClientDetail extends AppCompatActivity {
    private TextView txtView;
    private TextInputEditText etWeight,etLength,etAge,etActive;
    private CheckBox CheckBoxFemale,CheckBoxMale;
    private Button btnCheck;

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
        etActive=findViewById(R.id.etActive);
        btnCheck=findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClientDetail.this, MainActivity.class);
                startActivity(i);
            }
        });
















    }
}