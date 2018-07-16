package com.asiapacific.attendancemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chakely on 12/07/2018.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private Context context;
    private List<Class> classList;

    public ClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_recycler_layout, parent, false);
        ClassViewHolder classViewHolder = new ClassViewHolder(view);
        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        final Class myClass = classList.get(position);

        holder.textViewClassName.setText(myClass.getClassName());
        holder.textViewAccessCode.setText(myClass.getAccessCode());
        holder.textViewSchedule.setText(myClass.getStartTime()+" - "+myClass.getEndTime());

        holder.cardViewContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, ClassesAddClass.class);
                intent.putExtra("ACCESS_CODE", myClass.getAccessCode());
                intent.putExtra("CLASS_NAME", myClass.getClassName());
                intent.putExtra("START_DATE", myClass.getStartDate());
                intent.putExtra("END_DATE", myClass.getEndDate());
                intent.putExtra("START_TIME", myClass.getStartTime());
                intent.putExtra("END_TIME", myClass.getEndTime());
                intent.putExtra("WEEKDAYS", (Serializable) myClass.getWeekdays());
                intent.putExtra("_EDIT", true);
                context.startActivity(intent);
                return false;
            }
        });

        holder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfStudentsList.class);
                intent.putExtra("ACCESS_CODE", myClass.getAccessCode());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public void clear() {
        final int size = classList.size();
        classList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{

        TextView textViewClassName, textViewAccessCode, textViewSchedule;

        CardView cardViewContainer;

        public ClassViewHolder(View itemView) {
            super(itemView);

            textViewClassName = itemView.findViewById(R.id.textViewClassName);
            textViewAccessCode = itemView.findViewById(R.id.textViewAccessCode);
            textViewSchedule = itemView.findViewById(R.id.textViewSchedule);

            cardViewContainer = itemView.findViewById(R.id.cardViewContainer);

        }
    }
}
