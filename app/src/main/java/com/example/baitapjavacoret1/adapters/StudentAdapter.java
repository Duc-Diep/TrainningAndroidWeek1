package com.example.baitapjavacoret1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitapjavacoret1.R;
import com.example.baitapjavacoret1.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    List<Student> listStudent;
    Context context;
    IOnClickStudent iOnClickItem;

    public void setiOnClickItem(IOnClickStudent iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }

    public StudentAdapter(List<Student> listStudent, Context context) {
        this.listStudent = listStudent;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student student = listStudent.get(position);
        holder.tvStudentName.setText(student.getName());
        holder.tvPhoneNumber.setText( "Số điện thoại: "+student.getPhoneNumber());
        holder.tvSpecialized.setText("Hệ: "+student.getSpecialized());
        holder.tvYearOfBirth.setText("Năm sinh: "+student.getYearOfBirth()+"");
        holder.tvDelete.setOnClickListener(v -> iOnClickItem.onClickDeleteItem(student,position));
        holder.itemView.setOnClickListener(v -> iOnClickItem.onClickItem(student));
    }

    @Override
    public int getItemCount() {
        if (listStudent!=null){
            return listStudent.size();
        }
        return 0;
    }



    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName,tvPhoneNumber,tvSpecialized,tvYearOfBirth,tvDelete;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvSpecialized = itemView.findViewById(R.id.tvSpecialized);
            tvYearOfBirth = itemView.findViewById(R.id.tvYearOfBirth);
            tvDelete = itemView.findViewById(R.id.tvDelete);
        }
    }

    public interface IOnClickStudent{
        void onClickDeleteItem(Student student,int position);
        void onClickItem(Student student);
    }

}
