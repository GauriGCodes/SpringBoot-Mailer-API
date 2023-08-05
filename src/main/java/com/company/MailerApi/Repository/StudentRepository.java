package com.company.MailerApi.Repository;

import com.company.MailerApi.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "Select * from students s where s.registration_date> ?1",nativeQuery = true)
    public List<Student> getStudentRegisteredThisWeek(LocalDate date);


}
