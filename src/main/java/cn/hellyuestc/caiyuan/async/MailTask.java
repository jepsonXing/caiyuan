package cn.hellyuestc.caiyuan.async;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import cn.hellyuestc.caiyuan.util.MyUtil;

public class MailTask implements Runnable {

	private String code;
	private String email;
	private JavaMailSender javaMailSender;
	private int operation;
	
	public MailTask(String code, String email, JavaMailSender javaMailSender, int operation) {
		super();
		this.code = code;
		this.email = email;
		this.javaMailSender = javaMailSender;
		this.operation = operation;
	}

	@Override
	public void run() {
		javaMailSender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setFrom(MyUtil.getCaiyuanProperties().getProperty("mail.username"));
				mimeMessageHelper.setTo(email);
				mimeMessageHelper.setSubject("菜缘");
				StringBuilder sb = new StringBuilder();
				sb.append("<html><head></head><body>");

				if (operation == 1) {
					sb.append("<a href=" + MyUtil.getCaiyuanProperties().getProperty("serverScheme") + "://"
							+ MyUtil.getCaiyuanProperties().getProperty("serverHost") + ":"
							+ MyUtil.getCaiyuanProperties().getProperty("serverPort") 
							+ "/activate?code=");
					sb.append(code);
					sb.append(">点击激活您的邮箱</a></body>");
				} else {
//					sb.append("是否将您的密码修改为:");
//					sb.append(code.substring(0, 8));
//					sb.append("，<a href=" + MyConstant.DOMAIN_NAME + "verify.do?code=" + code + ">");
//					sb.append("点击是</a></body>");
				}

				mimeMessageHelper.setText(sb.toString(), true);
			}
			
		});
	}

}
