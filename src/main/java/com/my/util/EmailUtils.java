package com.my.util;

import com.my.pojo.RiskManagement;
import com.my.pojo.Users;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


public class EmailUtils
{
    private static final String FROM="caoxin9629@163.com";

//    编辑发件内容。
    public static void sendAccountActivateEmail(Users user)
    {
        Session session= getSession();
        MimeMessage message=new MimeMessage(session);

        try
        {
            message.setSubject("本邮件为了对您的账号进行激活");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getuMail()));
//            message.setContent("<a target='_BLANK' href='http://www.baidu.com'>"+user.getuName()+"先生/女士您好，请点击此链接激活账号"+GenerateLinkUtils.generateActivateLink(user)
//                    +"</a>","text/html;charset=utf-8");
            message.setContent(user.getuName()+"先生/女士您好，请点击此链接"+"<a target='_BLANK' href='"+GenerateLinkUtils.generateActivateLink(user)+"'>"+"激活账户"
                    +"</a>","text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    //    编辑发件内容。
    public static void sendPwdResetEmail(Users user)
    {
        Session session= getSession();
        MimeMessage message=new MimeMessage(session);

        try
        {
            message.setSubject("本邮件为了重置密码");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getuMail()));
            message.setContent(user.getuName()+"先生/女士您好，请点击此链接"+"<a target='_BLANK' href='"+GenerateLinkUtils.generatePwdResetLink(user)+"'>"+"重置密码"
                    +"</a>","text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }


    //    编辑发件内容。
    public static void sendWrongCityEmail(Users user, RiskManagement riskManagement)
    {
        Session session= getSession();
        MimeMessage message=new MimeMessage(session);

        try
        {
            message.setSubject("异地登录，请注意账号安全");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getuMail()));
//            message.setContent("<a target='_BLANK' href='http://www.baidu.com'>"+user.getuName()+"先生/女士您好，请点击此链接激活账号"+GenerateLinkUtils.generateActivateLink(user)
//                    +"</a>","text/html;charset=utf-8");
            message.setContent("异地登录，请注意账号安全，城市："+riskManagement.getCity(),"text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    public static void sendWrongLoginEmail(Users user)
    {
        Session session= getSession();
        MimeMessage message=new MimeMessage(session);

        try
        {
            message.setSubject("您的账号被多次爆破");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getuMail()));
//            message.setContent("<a target='_BLANK' href='http://www.baidu.com'>"+user.getuName()+"先生/女士您好，请点击此链接激活账号"+GenerateLinkUtils.generateActivateLink(user)
//                    +"</a>","text/html;charset=utf-8");
            message.setContent("您的账号被多次爆破，请注意！！！","text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

//    发件人信息，发送协议，发件邮箱，邮箱密码等等。
    public static Session getSession()
    {
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host","smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth","true");

        Session session=Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(FROM,"PUESKRAYVTJQISZT");
            }
        });

        return session;
    }
}
