package com.asiapacific.attendancemanager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClassesAddClass extends AppCompatActivity {

   //Declaring DatePickerDialog on set listener for user interaction.
    private DatePickerDialog.OnDateSetListener startDateSetListener, endDateSetListener;

    //Declaring Views.
    EditText editTextAccessCode, editTextClassName;
    TextView textViewStartdate, textViewEndDate, textViewStartTime, textViewEndTime, textViewAddClassTitle;

    Button buttonSave ;

    CheckBox checkBoxSun, checkBoxMon, checkBoxTues, checkBoxWed, checkBoxThurs, checkBoxFri, checkBoxSat;

    //Firebase Database Instance
    DatabaseReference mDatabase;

    //Progress Dialog
    ProgressDialog progressDialog;

    //Bundle
    Bundle bundle;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_add_class);

        progressDialog = new ProgressDialog(this);

        //Initializing Views
        editTextAccessCode = (EditText) findViewById(R.id.editTextAccessCode) ;
        editTextClassName = (EditText) findViewById(R.id.editTextClassName) ;

        textViewStartdate = (TextView) findViewById(R.id.textViewStartDate);
        textViewEndDate = (TextView) findViewById(R.id.textViewEndDate);

        textViewStartTime = (TextView) findViewById(R.id.textViewStartTime);
        textViewEndTime = (TextView) findViewById(R.id.textViewEndTime);

        textViewAddClassTitle = (TextView) findViewById(R.id.textViewAddClassTitle);

        buttonSave = (Button) findViewById(R.id.buttonSave);

        checkBoxSun = (CheckBox) findViewById(R.id.checkBoxSun);
        checkBoxMon = (CheckBox) findViewById(R.id.checkBoxMon);
        checkBoxTues = (CheckBox) findViewById(R.id.checkBoxTues);
        checkBoxWed = (CheckBox) findViewById(R.id.checkBoxWed);
        checkBoxThurs = (CheckBox) findViewById(R.id.checkBoxThurs);
        checkBoxFri = (CheckBox) findViewById(R.id.checkBoxFri);
        checkBoxSat = (CheckBox) findViewById(R.id.checkBoxSat);

         //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Setting listener to "Select Date" button/label under "Start Date"
        textViewStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


    }
}
