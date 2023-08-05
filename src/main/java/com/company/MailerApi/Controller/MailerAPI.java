package com.company.MailerApi.Controller;

import com.company.MailerApi.Model.Mail;
import com.company.MailerApi.Model.Student;
import com.company.MailerApi.Service.MailSendService;
import com.company.MailerApi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mail/api")
public class MailerAPI {

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/message")
    public void sendMessage(@RequestBody Mail mail){
        mailSendService.sendMail(mail);
    }



    /* Below two methods are not part of implementation,
    they are just for testing by adding data*/
    @GetMapping("/student")
    public List<Student> getStudentData(){
        return studentService.getAllStudent();
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        LocalDate localDate = LocalDate.now();
        student.setRegistrationDate(localDate);
        return studentService.addStudent(student);
    }

    @Scheduled(cron = "0 */1 * ? * *")
    @GetMapping("/student/getStudentsAfterDate")
    public String getStudentRegisteredThisWeek() {
        Mail mail = new Mail("gauriguptacodes@gmail.com","Hello, how are you?","Test Mail");
        List<Student> students =  studentService.getStudentRegisteredThisWeek();
        return mailSendService.sendMailWithAttachments(mail,students);
    }

}
