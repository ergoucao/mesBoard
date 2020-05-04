package com.my.pojo;

public class UsersStatis
{
    private Integer uId;
    private String uName;
    private boolean isBan;
    private Integer questionNum;
    private Integer askNum;
    private Integer answerNum;
    private String uMail;

    @Override
    public String toString()
    {
        return "UsersStatis{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", isBan=" + isBan +
                ", questionNum=" + questionNum +
                ", askNum=" + askNum +
                ", answerNum=" + answerNum +
                ", uMail='" + uMail + '\'' +
                '}';
    }

    public String getuMail()
    {
        return uMail;
    }

    public void setuMail(String uMail)
    {
        this.uMail = uMail;
    }

    public UsersStatis()
    {
    }

    public Integer getuId()
    {
        return uId;
    }

    public void setuId(Integer uId)
    {
        this.uId = uId;
    }

    public String getuName()
    {
        return uName;
    }

    public void setuName(String uName)
    {
        this.uName = uName;
    }

    public boolean isBan()
    {
        return isBan;
    }

    public void setBan(boolean isBan)
    {
        this.isBan = isBan;
    }

    public Integer getQuestionNum()
    {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum)
    {
        this.questionNum = questionNum;
    }

    public Integer getAskNum()
    {
        return askNum;
    }

    public void setAskNum(Integer askNum)
    {
        this.askNum = askNum;
    }

    public Integer getAnswerNum()
    {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum)
    {
        this.answerNum = answerNum;
    }
}
