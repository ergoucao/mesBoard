package com.my.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class PasswordUtil
{
    static final int N=99999999;

//    生成随机盐的密码
    public static String generate(String uPassword)
    {
        Random random = new Random();
        StringBuilder temp = new StringBuilder(16);
        temp.append(random.nextInt(N)).append(random.nextInt(N));
        for (int i=0,len=temp.length(); len< 16;i++,len++)
        {
            temp.append("0");
        }
        String salt=temp.toString();
        uPassword=md5Hex(uPassword+salt);
        char[] cs=new char[48];
        for (int i=0;i<48;i+=3)
        {
            cs[i]=uPassword.charAt(i/3*2);
            char c=salt.charAt(i/3);
            cs[i+1]=c;
            cs[i+2]=uPassword.charAt(i/3*2+1);
        }
        return new String(cs);
    }


//    密码校验
    public static boolean verify(String password,String md5)
    {
        char[] cs1=new char[32];
        char[] cs2=new char[16];
        for (int i=0;i<48;i+=3)
        {
            cs1[i/3*2]=md5.charAt(i);
            cs1[i/3*2+1]=md5.charAt(i+2);
            cs2[i/3]=md5.charAt(i+1);
        }
        String salt=new String(cs2);
        return md5Hex(password+salt).equals(new String(cs1));
    }

//    返回MD5摘要
    public static String md5Hex(String src)
    {
        try{
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte[] bs=md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }

    public static void main(String[] args)
    {
        // 加密+加盐
        String password1 = generate("e10adc3949ba59abbe56e057f20f883e");
        System.out.println("结果：" + password1 + "   长度："+ password1.length());
        // 解码
        System.out.println(verify("e10adc3949ba59abbe56e057f20f883e", password1));
        // 加密+加盐
        if (PasswordUtil.verify("e10adc3949ba59abbe56e057f20f883e",password1))
        {
            System.out.println("密码正确");
        }
    }
}
