package com.example.bryanhenneshw2gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5; //display 5 fields to enter grades
    TextView titleText; //display 'GPA Calculator' at top of screen
    TextView resultText; //display calculated GPA
    double field1;
    double field2;
    double field3;
    double field4;
    double field5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = (EditText) findViewById(R.id.eT1);
        mEditText2 = (EditText) findViewById(R.id.eT2);
        mEditText3 = (EditText) findViewById(R.id.eT3);
        mEditText4 = (EditText) findViewById(R.id.eT4);
        mEditText5 = (EditText) findViewById(R.id.eT5);


        titleText = (TextView) findViewById(R.id.tv1);
        titleText.setTextSize(30);
        titleText.setTextColor(getResources().getColor(R.color.title_color));
        resultText = (TextView) findViewById(R.id.tv2);
        resultText.setTextSize(45);
        resultText.setTextColor(getResources().getColor(R.color.title_color));




    }

    public void calculateGpa(View view) {

        double average= 0.0;
        if(!fieldEmpty(mEditText1)&&!fieldEmpty(mEditText2)&&!fieldEmpty(mEditText3)&&!fieldEmpty(mEditText4)&&!fieldEmpty(mEditText5)) {

                average = (Double.parseDouble(mEditText1.getText().toString()) + Double.parseDouble(mEditText2.getText().toString()) + Double.parseDouble(mEditText3.getText().toString()) +
                        Double.parseDouble(mEditText4.getText().toString()) + Double.parseDouble(mEditText5.getText().toString())) / 5;

        }

        else
            promptUser(mEditText1, mEditText2, mEditText3, mEditText4, mEditText5);
        setBackgroundColor(average);
        resultText.setText("GPA: " + getGPA(average));
        promptUser(mEditText1, mEditText2, mEditText3, mEditText4, mEditText5);
        setBackgroundColor(average);

    }
    public double getGPA(double average){
        double result = 0.0;
        if (average >= 93)
            result = 4.0;
        else if (average >= 90 && average <= 92)
            result = 3.7;
        else if (average >= 87 && average <= 89)
            result = 3.3;
        else if (average >= 83 && average <= 86)
            result = 3.0;
        else if (average >= 80 && average <= 82)
            result = 2.7;
        else if (average >= 77 && average <= 79)
            result = 2.3;
        else if (average >= 73 && average <= 76)
            result = 2.0;
        else if (average >= 70 && average <= 72)
            result = 1.7;
        else if (average >= 67 && average <= 69)
            result = 1.3;
        else if (average >= 65 && average <= 66)
            result = 1.0;
        else
            result = 0.0;
        return result;
    }

    //make this work
    public boolean isValidNumber(EditText field){
        //double num;
        boolean result = true;
            try{
                Double.parseDouble(field.getText().toString());
            }catch (NumberFormatException ex){
               result = false;
            }

       return result;

    }

    //change background color based on GPA
    public void setBackgroundColor(double average){
       View view = this.getWindow().getDecorView();
       int bad = getResources().getColor(R.color.bad_GPA);
       int okay = getResources().getColor(R.color.okay_GPA);
       int good = getResources().getColor(R.color.good_GPA);

       if(average >= 80)
           view.setBackgroundColor(good);
       else if (average >=61 && average < 80)
           view.setBackgroundColor(okay);
       else
           view.setBackgroundColor(bad);
    }

    //method to check for empty field
    public boolean fieldEmpty(EditText field){
        String fieldText = field.getText().toString();
        boolean result = false;
        if(fieldText.matches("")) // if field is empty
            result = true;
        return result;
    }

    //method that checks all fields and prompts user with a toast if any are empty
    public void promptUser(EditText field1, EditText field2, EditText field3, EditText field4, EditText field5 ){
        EditText[] fields = {field1, field2, field3, field4, field5};
        for(int i =0; i < fields.length; i++){
            if(fieldEmpty(fields[i])) {
                Toast.makeText(MainActivity.this, "Cannot leave any fields blank", Toast.LENGTH_LONG).show();
                fields[i].setBackgroundColor(getResources().getColor(R.color.empty_field));
            }
            else
                fields[i].setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}