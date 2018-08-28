package com.asiapacific.attendancemanager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClassesAddClass extends AppCompatActivity {

   //Declaring DatePickerDialog on set listener for user interaction.
    private DatePickerDialog.OnDateSetListener startDateSetListener, endDateSetListener;

    //Declaring Views.
    EditText editTextAccessCode, editTextClassName, editTextRoom, editTextSection;
    TextView textViewStartdate, textViewEndDate, textViewStartTime, textViewEndTime, textViewAddClassTitle;
    Button buttonSave ;
    ImageView imageViewDelete;
    CheckBox checkBoxSun, checkBoxMon, checkBoxTues, checkBoxWed, checkBoxThurs, checkBoxFri, checkBoxSat;
    Spinner spinnerSY, spinnerSemester;

    //Array for spinners
    ArrayAdapter spinnerSYAdapter, spinnerSemesterAdapter;
    
    //Firebase Database Instance
    DatabaseReference mDatabase;

    //Progress Dialog
    ProgressDialog progressDialog;

    //Bundle
    Bundle bundle;

    String professorsEmail;
    String professorsName;

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

        imageViewDelete = (ImageView) findViewById(R.id.imageViewDelete);
        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClass();
            }
        });

         //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Setting listener to "Select Date" button/label under "Start Date"
        textViewStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will return current date informations.
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //Configuring DatePickerDialog.
                DatePickerDialog dialog = new DatePickerDialog(
                        ClassesAddClass.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
                        startDateSetListener,
                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //Setting listener to "Select Date" button/label under "End Date"
        textViewEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This will return current date informations.
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                //Configuring DatePickerDialog.
                DatePickerDialog dialog = new DatePickerDialog(
                        ClassesAddClass.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
                        endDateSetListener,
                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        textViewStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePicker();
            }
        });

        textViewEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePicker();
            }
        });

        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = String.format("%s %d, %d",month(month), day, year);
                //This will display the starting date of class.
                textViewStartdate.setText(date);
            }
        };

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = String.format("%s %d, %d",month(month), day, year);
                //This will display the starting date of class.
                textViewEndDate.setText(date);
            }
        };

         buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
                showProgressDialog();
            }
        });

        bundle = getIntent().getExtras();
        if(bundle != null) {
            textViewAddClassTitle.setText("Edit Class");
            populateViews();
        } else {
            textViewAddClassTitle.setText("Add Class");
        }
    }

    SharedPreferences sharedPreferencesProfessorGoogleInfo = getSharedPreferences("AMS_PREFERENCE",MODE_PRIVATE);
        professorsName = sharedPreferencesProfessorGoogleInfo.getString("NAME",null);
        professorsEmail = sharedPreferencesProfessorGoogleInfo.getString("EMAIL",null).replace("."," ");
    }

    private void deleteClass() {
        //Access Classes Node
        final String accessCode = bundle.getString("ACCESS_CODE");
        mDatabase.child("Classes").child(accessCode).removeValue();
        mDatabase.child("Professors").child(professorsEmail).child("Classes").child(accessCode).removeValue();
        Query retrieveClasses = mDatabase.child("Students");
        retrieveClasses.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot studentSnapShot : dataSnapshot.getChildren()) {
                    String student = studentSnapShot.getKey();
                    for(DataSnapshot nodeSnapShot : studentSnapShot.getChildren()) {
                        String node = nodeSnapShot.getKey();
                        if(node.equals("Classes")) {
                            for(DataSnapshot classSnapShot : nodeSnapShot.getChildren()) {
                                String tempAccessCode = classSnapShot.getKey();
                                if(tempAccessCode.equals(accessCode)) {
                                    mDatabase.child("Students").child(student).child("Classes").child(accessCode).removeValue();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toast.makeText(this, "Deleted successfully.",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ProfMainActivity.class));
        finish();
    }

     private void populateViews() {
        editTextAccessCode.setText(bundle.getString("ACCESS_CODE"));
        editTextClassName.setText(bundle.getString("CLASS_NAME"));
        textViewStartdate.setText(bundle.getString("START_DATE"));
        textViewEndDate.setText(bundle.getString("END_DATE"));
        textViewStartTime.setText(bundle.getString("START_TIME"));
        textViewEndTime.setText(bundle.getString("END_TIME"));

        if(bundle.getSerializable("WEEKDAYS") != null) {
            List<String> weekdays = (List <String>)bundle.getSerializable("WEEKDAYS");
            for(int i = 0 ; i < weekdays.size() ; i++) {
                switch (weekdays.get(i)) {
                    case "Sunday":
                        checkBoxSun.setChecked(true);
                    break;
                    case "Monday":
                        checkBoxMon.setChecked(true);
                    break;
                        case "Tuesday":
                        checkBoxTues.setChecked(true);
                    break;
                    case "Wednesday":
                        checkBoxWed.setChecked(true);
                    break;
                    case "Thursday":
                        checkBoxThurs.setChecked(true);
                    break;
                    case "Friday":
                        checkBoxThurs.setChecked(true);
                    break;
                    case "Saturday":
                        checkBoxSat.setChecked(true);
                    break;
                }
            }
        }
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Saving data...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void saveUserInformation() {
        Query retrieveClasses = mDatabase.child("Classes");

        final String accessCode = editTextAccessCode.getText().toString();
        String className = editTextClassName.getText().toString();
        String startDate = textViewStartdate.getText().toString();
        String endDate = textViewEndDate.getText().toString();
        String startTime = textViewStartTime.getText().toString();
        String endTime = textViewEndTime.getText().toString();
        List<String> weekDays = new ArrayList<>();

        if(checkBoxSun.isChecked()) { weekDays.add("Sunday");}
        if(checkBoxMon.isChecked()) { weekDays.add("Monday");}
        if(checkBoxTues.isChecked()) { weekDays.add("Tuesday");}
        if(checkBoxWed.isChecked()) { weekDays.add("Wednesday");}
        if(checkBoxThurs.isChecked()) { weekDays.add("Thursday");}
        if(checkBoxFri.isChecked()) { weekDays.add("Friday");}
        if(checkBoxSat.isChecked()) { weekDays.add("Saturday");}

        final Class myClass = new Class(
                accessCode,
                className,
                startDate,
                endDate,
                startTime,
                endTime,
                weekDays
        );
        retrieveClasses.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exists = false;
                for (DataSnapshot classSnapShot : dataSnapshot.getChildren()) {
                    //Check if class is already exists.
                    String tempAccessCode = classSnapShot.getKey();
                    if(accessCode.equals(tempAccessCode)){
                        exists = true;
                    }
                }
                //If existing
                if(exists) {
                    showMessage("Class already exists!");
                    dismissProgressDialog();

                } else {
                    //Professors Node - Adding information of professor
                    mDatabase.child("Professors").child(professorsEmail).child("Information").setValue(new Professor(professorsName, professorsEmail));
                    //Adding class to professor node.
                    mDatabase.child("Professors").child(professorsEmail).child("Classes").child(accessCode).setValue(myClass);
                    //Classes Node
                    mDatabase.child("Classes").child(accessCode).child("Information").setValue(myClass);
                    //Setting initial number of students
                    mDatabase.child("Classes").child(accessCode).child("Students").child("totalStudents").setValue(0);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressDialog();
                            Toast.makeText(getApplicationContext(), "Data saved successfully.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, 300); // Millisecond 1000 = 1 sec
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showEndTimePicker() {
        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    int hour = hourOfDay % 12;
                    if (hour == 0)
                    {
                        hour = 12;
                        textViewEndTime.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
                    }
                    else
                    {
                        textViewEndTime.setText(String.format("%02d:%02d %s", hour, minute,hourOfDay < 12 ? "am" : "pm"));
                    }
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth, myTimeListener, hour, minute, false);
//        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private void showStartTimePicker() {
        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    int hour = hourOfDay % 12;
                    if (hour == 0)
                    {
                        hour = 12;
                        textViewStartTime.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
                    }
                    else
                    {
                        textViewStartTime.setText(String.format("%02d:%02d %s", hour, minute,hourOfDay < 12 ? "am" : "pm"));
                    }
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth, myTimeListener, hour, minute, false);
//        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

     //DatePicker only returns INT data for MONTH by default so this method will convert the data to its String value.
    private String month(int x) {
        String month;
        switch (x) {
            case 0:
                month = "January";
            break;
            case 1:
                month = "February";
            break;
            case 2:
                month = "March";
            break;
            case 3:
                month = "April";
            break;
            case 4:
                month = "May";
            break;
            case 5:
                month = "June";
            break;
            case 6:
                month = "July";
            break;
            case 7:
                month = "August";
            break;
            case 8:
                month = "September";
            break;
            case 9:
                month = "October";
            break;
            case 10:
                month = "November";
            break;
            case 11:
                month = "December";
            break;
            default:
                month = null;
        }
        return month;


        public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
