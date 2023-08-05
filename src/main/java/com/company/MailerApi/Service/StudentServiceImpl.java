package com.company.MailerApi.Service;


import com.company.MailerApi.Model.Student;
import com.company.MailerApi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
         return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentRegisteredThisWeek(){
        LocalDate date = LocalDate.of(2023,3,8);
        return studentRepository.getStudentRegisteredThisWeek(date);
    }
}
