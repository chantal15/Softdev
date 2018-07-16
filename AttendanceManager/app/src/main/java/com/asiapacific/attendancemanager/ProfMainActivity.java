package com.asiapacific.attendancemanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        loadFragment(new FragmentProfClasses());
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_classes:
                fragment = new FragmentProfClasses();
                break;
            case R.id.navigation_calendar:
                fragment = new FragmentProfAttendance();
                break;
            case R.id.navigation_reports:
                fragment = new FragmentProfReports();
                break;
//            case R.id.navigation_students:
//                fragment = new FragmentProfStudents();
//                break;
        }
        return loadFragment(fragment);
    }
}
