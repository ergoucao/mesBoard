package com.my.util;

import com.my.pojo.Users;
import org.apache.log4j.Logger;

import static org.apache.logging.log4j.core.helpers.NameUtil.md5;

public class GenerateLinkUtils
{
    private static final String CHECK_CODE="checkCode";
    static Logger logger=Logger.getLogger(GenerateLinkUtils.class);

    public static String generateActivateLink(Users user)
    {
        return "http://172.81.235.232:8080/mesBoard_Web_exploded/activate?id="+user.getId()+"&"+"uName"+"="+user.getuName()+"&"+CHECK_CODE+"="+generateCheckcode(user);
    }

    public static String generatePwdResetLink(Users user)
    {
        return "http://172.81.235.232:8080/mesBoard_Web_exploded/reset.html?id="+user.getId()+"&"+"uName"+"="+user.getuName()+"&"+CHECK_CODE+"="+generateCheckcode(user);
    }

//    用户名+UUID唯一标识符，为安全把他们加密发送
    private static String generateCheckcode(Users users)
    {
        String name=users.getuName();
        String code= users.getCodeUrl();
        logger.debug("使用了用户的UUID"+code);
        return md5(name+":"+code);
    }

//    判断验证码是否相等。
    public static boolean verifyCheckcode(Users users,String checkCode)
    {
        boolean flag= generateCheckcode(users).equals(checkCode);
        return flag;
    }
}
