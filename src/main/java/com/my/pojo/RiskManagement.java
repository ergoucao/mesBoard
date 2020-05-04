package com.my.pojo;

public class RiskManagement
{
    int rmId=0;
    int uid=0;
    String city=null;
    int wrongLoginN=0;
    int judgeCity=0;

    @Override
    public String toString()
    {
        return "RiskManagement{" +
                "rmId=" + rmId +
                ", uid=" + uid +
                ", city='" + city + '\'' +
                ", wrongLoginN='" + wrongLoginN + '\'' +
                ", judgeCity='" + judgeCity + '\'' +
                '}';
    }

    public int getJudgeCity()
    {
        return judgeCity;
    }

    public void setJudgeCity(int judgeCity)
    {
        this.judgeCity = judgeCity;
    }

    public RiskManagement()
    {

    }

    public int getRmId()
    {
        return rmId;
    }

    public void setRmId(int rmId)
    {
        this.rmId = rmId;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getWrongLoginN()
    {
        return wrongLoginN;
    }

    public void setWrongLoginN(int wrongLoginN)
    {
        this.wrongLoginN = wrongLoginN;
    }
}
