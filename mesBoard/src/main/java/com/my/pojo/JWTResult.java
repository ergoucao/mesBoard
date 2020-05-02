package com.my.pojo;

import io.jsonwebtoken.Claims;

public class JWTResult
{
//    错误编码，200为正确
    private int errCode;
//    是否成功
    private boolean success;

    public JWTResult()
    {
    }

    @Override
    public String toString()
    {
        return "JWTResult{" +
                "errCode=" + errCode +
                ", success=" + success +
                ", claims=" + claims +
                '}';
    }

    //    验证payload中的数据
    private Claims claims;

    public int getErrCode()
    {
        return errCode;
    }

    public void setErrCode(int errCode)
    {
        this.errCode = errCode;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Claims getClaims()
    {
        return claims;
    }

    public void setClaims(Claims claims)
    {
        this.claims = claims;
    }
}
