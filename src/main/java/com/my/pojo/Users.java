package com.my.pojo;

public class Users
{
    private int id=0;
    private String uName=null;
    private String uPassword=null;
    private String uPasswordSure=null;
    private String uPhoto=null;
    private String uMail=null;
    private String codeUrl=null;
    private boolean isActive=false;
    private boolean isDelete=false;
    private boolean acceptQues=true;
    private boolean isBan=false;

    @Override
    public String toString()
    {
        return "Users{" +
                "id=" + id +
                ", uName='" + uName + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uPasswordSure='" + uPasswordSure + '\'' +
                ", uPhoto='" + uPhoto + '\'' +
                ", uMail='" + uMail + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", isActive=" + isActive +
                ", isDelete=" + isDelete +
                ", acceptQues=" + acceptQues +
                ", isBan=" + isBan +
                '}';
    }

    public boolean isBan()
    {
        return isBan;
    }

    public void setBan(boolean ban)
    {
        isBan = ban;
    }

    public Users()
    {
    }

    public boolean isAcceptQues()
    {
        return acceptQues;
    }

    public void setAcceptQues(boolean acceptQues)
    {
        this.acceptQues = acceptQues;
    }

    public boolean isDelete()
    {
        return isDelete;
    }

    public void setDelete(boolean delete)
    {
        isDelete = delete;
    }

    public String getuPasswordSure()
    {
        return uPasswordSure;
    }

    public void setuPasswordSure(String uPasswordSure)
    {
        this.uPasswordSure = uPasswordSure;
    }

    public String getCodeUrl()
    {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl)
    {
        this.codeUrl = codeUrl;
    }

    public String getuPassword()
    {
        return uPassword;
    }

    public void setuPassword(String uPassword)
    {
        this.uPassword = uPassword;
    }

    public String getuMail()
    {
        return uMail;
    }

    public void setuMail(String uMail)
    {
        this.uMail = uMail;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getuName()
    {
        return uName;
    }

    public void setuName(String uName)
    {
        this.uName = uName;
    }

    public String getuPhoto()
    {
        return uPhoto;
    }

    public void setuPhoto(String uPhoto)
    {
        this.uPhoto = uPhoto;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    public Users(int id, String uName, String uPhoto, boolean isActive)
    {
        this.id = id;
        this.uName = uName;
        this.uPhoto = uPhoto;
        this.isActive = isActive;
    }
}
