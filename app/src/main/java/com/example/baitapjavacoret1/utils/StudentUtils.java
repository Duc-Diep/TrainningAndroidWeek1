package com.example.baitapjavacoret1.utils;

import com.example.baitapjavacoret1.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentUtils {
    private List<Student> list;

    public StudentUtils() {
        list = new ArrayList<>();
        list.add(new Student("Nguyễn Đức Điệp", 1998, "0981206317", "Đại học"));
        list.add(new Student("Nguyễn Anh Linh", 1976, "0912342344", "Đại học"));
        list.add(new Student("Vũ Văn Doan", 2000, "092342344", "Đại học"));
        list.add(new Student("Đoàn Duy Nam", 2018, "098345543", "Cao đẳng"));
        list.add(new Student("Hoàng Duy Khánh", 2012, "0923423443", "Đại học"));
        list.add(new Student("Đỗ Vinh Hà", 2020, "098323443", "Cao đẳng"));
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    //    private boolean checkPhoneNumber(String phoneNumber) {
//        for (Student student : list) {
//            if (student.getPhoneNumber().equals(phoneNumber)) {
//                return true;
//            }
//        }
//        return false;
//    }
    private int findStudentByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addStudent(Student student) {
        if (findStudentByPhoneNumber(student.getPhoneNumber()) == -1) {
            list.add(student);
            return true;
        }
        return false;
    }

    public boolean updateByPhoneNumber(String phoneNumber, Student student) {
        int index = findStudentByPhoneNumber(phoneNumber);
        if (index!=-1){
            list.set(index, student);
            return true;
        }
        return false;
    }

    public void removeStudent(int index) {
//        int index = findStudentByPhoneNumber(phoneNumber);
        list.remove(index);
    }

    public void sortByAttribute(String attribute) {
        switch (attribute) {
            case "name":
                list.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
                break;
            case "phoneNumber":
                list.sort((s1, s2) -> s1.getPhoneNumber().compareTo(s2.getPhoneNumber()));
                break;
            case "yearOfBirth":
                list.sort((s1, s2) -> s1.getYearOfBirth() - s2.getYearOfBirth());
                break;
            default:
                break;
        }
    }

    public List<Student> filterBySpecialized(String specialized) {
        List<Student> listTemp = new ArrayList<>();
        if (specialized.equals("Cao đẳng")) {
            for (Student student : list) {
                if (student.getSpecialized().equals("Cao đẳng")) {
                    listTemp.add(student);
                }
            }
        } else {
            for (Student student : list) {
                if (student.getSpecialized().equals("Đại học")) {
                    listTemp.add(student);
                }
            }
        }
        return listTemp;
    }

    public List<Student> searchByAttributes(String attributes, String searchString) {
        List<Student> listTemp = new ArrayList<>();
        switch (attributes) {
            case "name":
                for (Student student : list) {
                    if (student.getName().toLowerCase().contains(searchString.toLowerCase())) {
                        listTemp.add(student);
                    }
                }
                break;
            case "yearOfBirth":
                for (Student student : list) {
                    if (String.valueOf(student.getYearOfBirth()).contains(searchString) ) {
                        listTemp.add(student);
                    }
                }
                break;
            case "phoneNumber":
                for (Student student : list) {
                    if (student.getPhoneNumber().contains(searchString)) {
                        listTemp.add(student);
                    }
                }
                break;
            case "specialized":
                for (Student student : list) {
                    if (student.getSpecialized().toLowerCase().contains(searchString.toLowerCase())) {
                        listTemp.add(student);
                    }
                }
                break;
            default:
                return list;
        }
        return listTemp;
    }

}
