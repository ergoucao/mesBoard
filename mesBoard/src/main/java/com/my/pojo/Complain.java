package com.my.pojo;

public class Complain
{
    int id=0;
    String reporter=null;
    String informant=null;
    String reason=null;
    String dealAnswer=null;

    @Override
    public String toString()
    {
        return "Complain{" +
                "id=" + id +
                ", reporter='" + reporter + '\'' +
                ", informant='" + informant + '\'' +
                ", reason='" + reason + '\'' +
                ", dealAnswer='" + dealAnswer + '\'' +
                '}';
    }

    public String getDealAnswer()
    {
        return dealAnswer;
    }

    public void setDealAnswer(String dealAnswer)
    {
        this.dealAnswer = dealAnswer;
    }


    public Complain()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getReporter()
    {
        return reporter;
    }

    public void setReporter(String reporter)
    {
        this.reporter = reporter;
    }

    public String getInformant()
    {
        return informant;
    }

    public void setInformant(String informant)
    {
        this.informant = informant;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}