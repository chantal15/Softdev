package com.asiapacific.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Setting up StudentAdapter for Recycler View.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

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

          holder.textViewStudentName.setText(myStudent.getDisplayName());
          holder.textViewStudentEmail.setText(myStudent.getEmail());

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
    
        }
    }
}