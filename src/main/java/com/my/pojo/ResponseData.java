package com.my.pojo;

//返回给浏览器的是数据对象

public class ResponseData
{
    private int code;//返回码。‘
    private Object users;//数据
    private String msg;//返回描述
    private String token;//令牌
    private String add;//返回跳转地址
    private String jsti;//姓名标识

    @Override
    public String toString()
    {
        return "ResponseData{" +
                "code=" + code +
                ", users=" + users +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", add='" + add + '\'' +
                ", jsti='" + jsti + '\'' +
                '}';
    }

    public String getJsti()
    {
        return jsti;
    }

    public void setJsti(String jsti)
    {
        this.jsti = jsti;
    }

    public String getAdd()
    {
        return add;
    }

    public void setAdd(String add)
    {
        this.add = add;
    }

    public ResponseData()
    {
    }

    public ResponseData(int code, Users users, String msg, String token)
    {
        this.code = code;
        this.users = users;
        this.msg = msg;
        this.token = token;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Object getUsers()
    {
        return users;
    }

    public void setUsers(Object users)
    {
        this.users = users;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }
}
