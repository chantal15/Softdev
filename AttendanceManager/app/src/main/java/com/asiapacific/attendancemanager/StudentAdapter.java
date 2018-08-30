package com.asiapacific.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Setting up StudentAdapter for Recycler View.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;
    private String activity;

    public StudentAdapter(Context context, List<Student> studentList, String activity) {
        this.context = context;
        this.studentList = studentList;
        this.activity = activity;
    }

     public StudentAdapter(){}

@Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_recycler_layout, parent, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final Student myStudent = studentList.get(position);
        List<AttendanceStudent> attendanceStudents = new ArrayList<>();

          holder.textViewStudentName.setText(myStudent.getDisplayName());
          holder.textViewStudentEmail.setText(myStudent.getEmail());

    }

    if(activity.equals("Attendance")) {
              holder.radioButtonPresent.setVisibility(View.VISIBLE);
              holder.radioButtonPresent.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
//                      tinyDB.putListObject(););
                  }
              });

              holder.radioButtonLate.setVisibility(View.VISIBLE);
              holder.radioButtonLate.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      tinyDB.putObject(myStudent.getDisplayName(), new AttendanceStudent(myStudent.getDisplayName() , "No", "Yes", "No"));
                  }
              });

              holder.radioButtonAbsent.setVisibility(View.VISIBLE);
              holder.radioButtonAbsent.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      tinyDB.putObject(myStudent.getDisplayName(), new AttendanceStudent(myStudent.getDisplayName() , "No", "No", "Yes"));
                  }
              });

          } else {
              holder.radioButtonPresent.setVisibility(View.GONE);
              holder.radioButtonLate.setVisibility(View.GONE);
              holder.radioButtonAbsent.setVisibility(View.GONE);
          }

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void clear() {
        final int size = studentList.size();
        studentList.clear();
        notifyItemRangeRemoved(0, size);
    }

    private void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView textViewStudentName, textViewStudentEmail;

        CardView cardViewContainer;

        public StudentViewHolder(View itemView) {
            super(itemView);

            textViewStudentName = itemView.findViewById(R.id.textViewStudentName);
            textViewStudentEmail = itemView.findViewById(R.id.textViewStudentEmail);

            cardViewContainer = itemView.findViewById(R.id.cardViewContainer);
        }
    }
}