package com.my.pojo;

public class Question
{
    int id=0;
    String questioner=null;
    String questioned=null;
    String question=null;
    String answer=null;
    boolean isDelete=false;

    public Question(int id, String questioner, String questioned, String question, String answer, boolean isDelete)
    {
        this.id = id;
        this.questioner = questioner;
        this.questioned = questioned;
        this.question = question;
        this.answer = answer;
        this.isDelete = isDelete;
    }

    public Question()
    {
    }

    public boolean isDelete()
    {
        return isDelete;
    }

    public void setDelete(boolean delete)
    {
        isDelete = delete;
    }

    @Override
    public String toString()
    {
        return "Question{" +
                "id=" + id +
                ", questioner='" + questioner + '\'' +
                ", questioned='" + questioned + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getQuestioner()
    {
        return questioner;
    }

    public void setQuestioner(String questioner)
    {
        this.questioner = questioner;
    }

    public String getQuestioned()
    {
        return questioned;
    }

    public void setQuestioned(String questioned)
    {
        this.questioned = questioned;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

}
