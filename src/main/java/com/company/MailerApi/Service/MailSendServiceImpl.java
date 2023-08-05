package com.company.MailerApi.Service;

import com.company.MailerApi.Model.Mail;
import com.company.MailerApi.Model.Student;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@Service
public class MailSendServiceImpl implements MailSendService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(Mail mail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("");
            simpleMailMessage.setTo(mail.getRecipient());
            simpleMailMessage.setSubject(mail.getSubject());
            simpleMailMessage.setText(mail.getMessage());
            javaMailSender.send(simpleMailMessage);
            return "Message has been sent successfully";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @Override
    public String sendMailWithAttachments(Mail mail, List<Student> studentList) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader("studentID","firstName","lastName","email","phone","registrationDate")
                .build();

        String csvFilePath = "student_data.csv";


        // Create CSVPrinter
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(csvFilePath), csvFormat)) {
            // Write data to CSV file
            studentList.forEach((student -> {
                try {
                    csvPrinter.printRecords((Object) new String[]{String.valueOf(student.getStudentID()),student.getFirstName()
                    ,student.getLastName(),student.getEmail(), String.valueOf(student.getPhone()), String.valueOf(student.getRegistrationDate())});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setText(mail.getMessage());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.addAttachment("Student_data.csv", new File(csvFilePath));
            javaMailSender.send(mimeMailMessage);
            return "Mail Send Successfully";
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
