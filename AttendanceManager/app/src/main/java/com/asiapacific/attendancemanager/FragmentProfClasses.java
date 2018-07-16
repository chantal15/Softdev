package com.asiapacific.attendancemanager;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asiapacific.attendancemanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chakely on 12/07/2018.
 */
public class FragmentProfClasses extends Fragment {

    TextView textViewAddClass;
    View view;

    RecyclerView classRecyclerView;
    ClassAdapter classAdapter;

    List<Class> classList;

    //Firebase Database
    DatabaseReference databaseClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflates fragment xml file.
         view = inflater.inflate(R.layout.fragment_prof_classes, null);

         //Initialize Database Class from Firebase.
         databaseClass = FirebaseDatabase.getInstance().getReference("class");

         classList = new ArrayList<>();

         classRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewClassList);
         classRecyclerView.setHasFixedSize(false);
         classRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        textViewAddClass = (TextView)view.findViewById(R.id.textViewAddClass);
        textViewAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will open Add Class activity.
                Intent intent = new Intent(view.getContext(), ClassesAddClass.class);
                startActivity(intent);
            }
        });

//        addClasses();

        return view;
    }

     @Override
    public void onStart() {
        super.onStart();

        //Updating datas realtime using Firebase technology.
        databaseClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                classList.clear();
                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    Class myClass = classSnapshot.getValue(Class.class);
                    if(myClass != null) {
                        classList.add(myClass);
                        //Setting Adapter
                        classAdapter = new ClassAdapter(view.getContext(), classList);
                        classRecyclerView.setAdapter(classAdapter);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    
    }


}
