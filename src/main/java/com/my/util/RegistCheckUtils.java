package com.my.util;

import com.my.pojo.ResponseData;
import com.my.pojo.Users;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 本工具类，用于检验注册输入用户名，密码，邮箱，等格式是否正确
 */
public class RegistCheckUtils
{
    public static ResponseData registCheck(Users users, String code)
    {
        Logger logger=Logger.getLogger(RegistCheckUtils.class);
        ResponseData rb=new ResponseData();
//       响应码，表示提交数据格式出现错误。901表示没问题，900表示出现了问题
        rb.setCode(901);
        if (users.getuName()==null)
        {
            logger.debug("注册用户名为空。");
            rb.setMsg(rb.getMsg()+" 用户名不能为空");
            rb.setCode(900);
        }
        if (!emailFormat(users.getuMail()))
        {
            logger.debug("注册邮箱格式不正确");
            rb.setMsg(rb.getMsg()+" 邮箱格式不正确");
            rb.setCode(900);
        }
        return rb;
    }

    public static boolean emailFormat(String email)
    {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

//    工具测试类
    public static void main(String[] args)
    {
        if (RegistCheckUtils.emailFormat("123456@"))
        {
            System.out.println("1true");
        }
        else
        {
            System.out.println("1false");
        }
        if (RegistCheckUtils.emailFormat("123456@qq.com"))
        {
            System.out.println("2true");
        }
        else
        {
            System.out.println("2false");
        }
        if (RegistCheckUtils.emailFormat("123456"))
        {
            System.out.println("3true");
        }
        else
        {
            System.out.println("3false");
        }
    }
}
