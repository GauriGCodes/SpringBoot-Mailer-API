package com.company.MailerApi.Service;
import com.company.MailerApi.Model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudent();

    public Student addStudent(Student student);

    public List<Student> getStudentRegisteredThisWeek();


}
