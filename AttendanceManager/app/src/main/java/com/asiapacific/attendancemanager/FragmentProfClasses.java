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

    
    }


}
