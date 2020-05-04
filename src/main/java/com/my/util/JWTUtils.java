package com.my.util;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.pojo.JWTResult;
import com.my.pojo.Users;
import io.jsonwebtoken.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtils
{
    static Logger logger=Logger.getLogger(JWTUtils.class);
    //    服务器的key
    private static final String JWT_SECRET = "test_jwt_secret";
    private static final ObjectMapper Mapper = new ObjectMapper();
    public static final int JWT_ERRCODE_EXPIRE = 1005;
    public static final int JWT_ERRCODE_FAIL = 1006;

//    通过key生成密钥。
    public static SecretKey generalKey()
    {
        try
        {
//            得到byte[]类型key
            byte[] encodeKey=JWT_SECRET.getBytes("UTF-8");
            SecretKey key=new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
            return key;
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    签发token.
    public static String createJWT(String id,String iss,String subject,Long ttMillis)
    {
//       加密算法
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
//       当前时间
        long nowMilis=System.currentTimeMillis();
//        当前时间的日期对象；
        Date now=new Date(nowMilis);
        SecretKey secretKey=generalKey();
//        创建JWT的构建器。就是使用指定的信息和加密算法，生成Token；
        JwtBuilder builder= Jwts.builder()
                            .setId(id)
                            .setIssuer(iss)
                            .setSubject(subject)
                            .setIssuedAt(now)//生成Token的时间。
                            .signWith(signatureAlgorithm,secretKey);//设置密钥和算法。
        if (ttMillis>=0)
        {
            long expMilis=nowMilis+ttMillis;
            Date expDate=new Date(expMilis);//token失效时间
            builder.setExpiration(expDate);
        }
        return builder.compact();//生成token
    }

//    验证JWT
    public static JWTResult validateJWT(String jwtStr)
    {
        JWTResult checkResult=new JWTResult();
        Claims claims=null;
        checkResult.setErrCode(200);
        try
        {
            logger.debug("token有效");
            claims=parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        }catch (ExpiredJwtException e)//token失效
        {
            logger.debug("token失效");
            checkResult.setErrCode(JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        }catch (SignatureException e)//tonken校验失败
        {
            logger.debug("tonken校验失败");
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }catch (Exception e)
        {
            logger.debug("tonken校验失败");
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
            e.printStackTrace();
        }
        logger.debug("返回的"+checkResult);
        return checkResult;
    }

//    解析JWT字符串
    public static Claims parseJWT(String jwt) throws Exception
    {
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();//获取token中记录的payload数据
    }

//    生成subject信息
    public  static  String generalSubject(Users users)
    {
        try
        {
            return Mapper.writeValueAsString(users);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}


