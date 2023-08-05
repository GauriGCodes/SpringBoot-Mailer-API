package com.company.MailerApi.Service;

import com.company.MailerApi.Model.Mail;
import com.company.MailerApi.Model.Student;

import java.util.List;

public interface MailSendService {
     String sendMail(Mail mail);
     String sendMailWithAttachments(Mail mail, List<Student> students);
}
