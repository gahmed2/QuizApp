package com.example.gehad.quiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    static final String STATE_Name = "Name";
    static final String STATE_Q1 = "Q1";
    static final String STATE_Q2 = "Q2";
    static final String STATE_Q3 = "Q3";
    static final String STATE_Q4 = "Q4";
    static final String STATE_Q5Text = "Q5Text";
    static final String STATE_Q6Check1 = "Q6Check1";
    static final String STATE_Q6Check2 = "Q6Check2";

    private EditText nameField;
    private Editable nameEditable;
    String name;

    private RadioGroup Q1;
    private RadioButton Q1a;

    private RadioGroup Q2;
    private RadioButton Q2b;

    private RadioGroup Q3;
    private RadioButton Q3a;

    private RadioGroup Q4;
    private RadioButton Q4a;

    private EditText Q5Field;
    private Editable Q5Editable;
    String Q5Text;

    private CheckBox Q6a;
    private CheckBox Q6b;
    private CheckBox Q6c;
    private CheckBox Q6d;

    private TextView finalScoreView;

    int finalScore = 0;

    String message;

    boolean Q5Result, Q6Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.Name);

        Q1 = findViewById(R.id.Q1);
        Q1a = findViewById(R.id.Q1a);

        Q2 = findViewById(R.id.Q2);
        Q2b = findViewById(R.id.Q2b);

        Q3 = findViewById(R.id.Q3);
        Q3a = findViewById(R.id.Q3a);

        Q4 = findViewById(R.id.Q4);
        Q4a = findViewById(R.id.Q4a);

        Q5Field = findViewById(R.id.Q5);

        Q6a = findViewById(R.id.Q6a);
        Q6b = findViewById(R.id.Q6b);
        Q6c = findViewById(R.id.Q6c);
        Q6d = findViewById(R.id.Q6d);

        finalScoreView = findViewById(R.id.Score);
    }

    public void SubmitResults (View view){

        nameEditable = nameField.getText();
        name = nameEditable.toString();

        if(Q1a.isChecked()) finalScore++;

        if(Q2b.isChecked()) finalScore++;

        if(Q3a.isChecked()) finalScore++;

        if(Q4a.isChecked()) finalScore++;

        Q5Editable = Q5Field.getText();
        Q5Text = Q5Editable.toString();

        if(Q5Text.equals("ImageView") || Q5Text.equals("Image View")){
            finalScore++;
            Q5Result = TRUE;
        }

        if((Q6a.isChecked() && Q6c.isChecked()) && !(Q6b.isChecked() || Q6d.isChecked())){
            finalScore++;
            Q6Result = TRUE;
        }

        finalScoreView.setText(String.valueOf(finalScore));

        message = "Name: " + name;
        message += "\n" + "Q1: " + Q1a.isChecked();
        message += "\n" + "Q2: " + Q2b.isChecked();
        message += "\n" + "Q3: " + Q3a.isChecked();
        message += "\n" + "Q4: " + Q4a.isChecked();
        message += "\n" + "Q5: " + Q5Result;
        message += "\n" + "Q6: " + Q6Result;
        message += "\n" + "Score: " + finalScore;
        message += "\n" + "Best Wishes!";
    }

    public void ResetResults (View view){

        nameField.getText().clear();
        name = "";
        finalScore = 0;
        finalScoreView.setText(String.valueOf(finalScore));

        Q1.clearCheck();
        Q1a.setChecked(false);

        Q2.clearCheck();
        Q2b.setChecked(false);

        Q3.clearCheck();
        Q3a.setChecked(false);

        Q4.clearCheck();
        Q4a.setChecked(false);

        Q5Field.getText().clear();
        Q5Text = "";
        Q5Result = FALSE;

        Q6a.setChecked(false);
        Q6b.setChecked(false);
        Q6c.setChecked(false);
        Q6d.setChecked(false);
        Q6Result = FALSE;

        message = "";
    }

    public void ShareResults(View view){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Final Results for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
