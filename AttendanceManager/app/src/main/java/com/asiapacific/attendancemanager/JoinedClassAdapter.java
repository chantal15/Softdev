package com.asiapacific.attendancemanager;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chakely on 14/07/2018.
 */

public class JoinedClassAdapter extends RecyclerView.Adapter<JoinedClassAdapter.JoinedClassViewHolder> {

    private Context context;
    private List<Class> classList;

    public JoinedClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
    }

    @Override
    public JoinedClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_recycler_layout, parent, false);
        JoinedClassViewHolder joinedClassViewHolder = new JoinedClassViewHolder(view);
        return joinedClassViewHolder;
    }

    @Override
    public void onBindViewHolder(JoinedClassViewHolder holder, int position) {
        final Class myClass = classList.get(position);

        holder.textViewClassName.setText(myClass.getClassName());
        holder.textViewAccessCode.setText(myClass.getAccessCode());
        holder.textViewTotalStudents.setText("");

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

    public class JoinedClassViewHolder extends RecyclerView.ViewHolder{

        TextView textViewClassName, textViewAccessCode, textViewTotalStudents;

        CardView cardViewContainer;

        public JoinedClassViewHolder(View itemView) {
            super(itemView);

            textViewClassName = itemView.findViewById(R.id.textViewClassName);
            textViewAccessCode = itemView.findViewById(R.id.textViewAccessCode);
            textViewTotalStudents = itemView.findViewById(R.id.textViewSchedule);

            cardViewContainer = itemView.findViewById(R.id.cardViewContainer);



        }
    }
}