package com.xiuye.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xiuye.service.Demo4Service;
import com.xiuye.util.LogUtil;

@Service
public class Demo4ServiceImpl implements Demo4Service {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.fromMail.addr}")
	private String from;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);

		try {
			mailSender.send(message);
			LogUtil.println("简单邮件已经发送。");
		} catch (Exception e) {
			LogUtil.println("发送简单邮件时发生异常！:\n" + e);
		}

	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			// true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);
			LogUtil.println("html邮件发送成功");
		} catch (MessagingException e) {
			LogUtil.println("发送html邮件时发生异常！:\n" + e);
		}
	}

	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			// helper.addAttachment("test"+fileName, file);

			mailSender.send(message);
			LogUtil.println("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			LogUtil.println("发送带附件的邮件时发生异常！:\n" + e);
		}
	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			mailSender.send(message);
			LogUtil.println("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			LogUtil.println("发送嵌入静态资源的邮件时发生异常！:\n" + e);
		}
	}

}
