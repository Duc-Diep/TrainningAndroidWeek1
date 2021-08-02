package com.example.baitapjavacoret1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.baitapjavacoret1.R;
import com.example.baitapjavacoret1.adapters.StudentAdapter;
import com.example.baitapjavacoret1.models.Student;
import com.example.baitapjavacoret1.utils.StudentUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvStudent;
    List<Student> listStudent;
    StudentUtils utils;
    StudentAdapter adapter;
    Button btnAdd, btnSearch, btnFilter, btnUpdate, btnSort, btnDeleteFilter;
    EditText edtName, edtYearOfBirth, edtPhoneNumber, edtSearch;
    RadioButton rdUniversity, rdCollege;
    int index = 0;
    String attributeSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mapping();
        initList();
        listStudent = utils.getList();
        initRecyclerView();
        btnAdd.setOnClickListener(v -> {
            if (validEmpty()) {
                addStudent();
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            if (validEmpty()) {
                updateStudent();
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin cần sửa", Toast.LENGTH_SHORT).show();
            }
        });

        btnSort.setOnClickListener(v -> sortByAttributes());
        btnFilter.setOnClickListener(v -> filterByAttributes());
        btnSearch.setOnClickListener(v -> searchByAttributes());
        edtSearch.setOnClickListener(v -> {
            if (attributeSearch.equals("")) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn thuộc tính để tìm!!", Toast.LENGTH_SHORT).show();
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!attributeSearch.equals("")) {
                    listStudent = utils.searchByAttributes(attributeSearch, s.toString());
                    initRecyclerView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnDeleteFilter.setOnClickListener(v -> deleteFilter());
    }

    private boolean validEmpty() {
        if (edtName.getText().toString().isEmpty() || edtPhoneNumber.getText().toString().isEmpty() || edtYearOfBirth.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private void deleteFilter() {
        clearTextBox();
        index = 0;
        attributeSearch = "";
        listStudent = utils.getList();
        initRecyclerView();
    }

    private void searchByAttributes() {
        final String[] strings = {"Tên", "Số điện thoại", "Năm sinh", "Hệ"};
        index = 0;
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Chọn thuộc tính mà bạn cần tìm")
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
//                        Toast.makeText(MainActivity.this, strings[position] + " : đã chọn", Toast.LENGTH_SHORT).show();
                        index = position;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (index) {
                            case 0:
                                attributeSearch = "name";
                                break;
                            case 1:
                                attributeSearch = "phoneNumber";
                                break;
                            case 2:
                                attributeSearch = "yearOfBirth";
                                break;
                            case 3:
                                attributeSearch = "specialized";
                                break;
                            default:
                                break;
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.show();
    }

    private void filterByAttributes() {
        final String[] strings = {"Đại học", "Cao đẳng"};
        index = 0;
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Chọn hệ mà bạn muốn lọc")
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
//                        Toast.makeText(MainActivity.this, strings[position] + " : đã chọn", Toast.LENGTH_SHORT).show();
                        index = position;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (index) {
                            case 0:
                                listStudent = utils.filterBySpecialized("Đại học");
                                initRecyclerView();
//                                Toast.makeText(MainActivity.this, "Lọc theo hệ đại học!", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                listStudent = utils.filterBySpecialized("Cao đẳng");
                                initRecyclerView();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alertDialog.show();
    }

    private void sortByAttributes() {
        final String[] strings = {"Tên", "Số điện thoại", "Năm sinh"};
        index = 0;
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Chọn thuộc tính mà bạn sắp xếp")
                .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
//                        Toast.makeText(MainActivity.this, strings[position] + " : đã chọn", Toast.LENGTH_SHORT).show();
                        index = position;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (index) {
                            case 0:
                                utils.sortByAttribute("name");
                                adapter.notifyDataSetChanged();
                                break;
                            case 1:
                                utils.sortByAttribute("phoneNumber");
                                adapter.notifyDataSetChanged();
                                break;
                            case 2:
                                utils.sortByAttribute("yearOfBirth");
                                adapter.notifyDataSetChanged();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alertDialog.show();

    }

    private void updateStudent() {
        Student student = new Student();
        student.setName(edtName.getText().toString());
        student.setPhoneNumber(edtPhoneNumber.getText().toString());
        student.setYearOfBirth(Integer.parseInt(edtYearOfBirth.getText().toString()));
        student.setSpecialized(rdUniversity.isChecked() == true ? "Đại học" : "Cao đẳng");
        boolean isSuccess = utils.updateByPhoneNumber(edtPhoneNumber.getText().toString(), student);
        if (isSuccess) {
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            clearTextBox();
        } else {
            Toast.makeText(this, "Sửa thất bại, không có sinh viên nào có số điện thoại như trên!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addStudent() {
        Student student = new Student();
        student.setName(edtName.getText().toString());
        student.setPhoneNumber(edtPhoneNumber.getText().toString());
        student.setYearOfBirth(Integer.parseInt(edtYearOfBirth.getText().toString()));
        student.setSpecialized(rdUniversity.isChecked() == true ? "Đại học" : "Cao đẳng");
        boolean isSuccess = utils.addStudent(student);
        if (isSuccess) {
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            clearTextBox();
        } else {
            Toast.makeText(this, "Thêm thất bại, vui lòng nhập số điện thoại khác!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecyclerView() {
        adapter = new StudentAdapter(listStudent, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvStudent.setLayoutManager(layoutManager);
        rcvStudent.setAdapter(adapter);
//        rcvStudent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setiOnClickItem(new StudentAdapter.IOnClickStudent() {
            @Override
            public void onClickDeleteItem(Student student, int position) {
                utils.removeStudent(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onClickItem(Student student) {
                edtName.setText(student.getName());
                edtPhoneNumber.setText(student.getPhoneNumber());
                edtYearOfBirth.setText(student.getYearOfBirth() + "");
                if (student.getSpecialized().equals("Cao đẳng")) {
                    rdCollege.setChecked(true);
                } else {
                    rdUniversity.setChecked(true);
                }
            }
        });
    }

    public void clearTextBox() {
        edtName.setText("");
        edtPhoneNumber.setText("");
        edtYearOfBirth.setText("");
    }

    private void initList() {
        utils = new StudentUtils();
    }

    private void mapping() {
        rcvStudent = findViewById(R.id.rcvStudent);
        btnAdd = findViewById(R.id.btnAdd);
        btnFilter = findViewById(R.id.btnFilter);
        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtName = findViewById(R.id.edtName);
        edtYearOfBirth = findViewById(R.id.edtYearOfBirth);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        rdCollege = findViewById(R.id.rdCollege);
        rdUniversity = findViewById(R.id.rdUniversity);
        btnSort = findViewById(R.id.btnSort);
        edtSearch = findViewById(R.id.edtSearch);
        btnDeleteFilter = findViewById(R.id.btnDeleteFilter);
    }


}