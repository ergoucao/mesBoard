package com.my.service.impl;

import com.my.mapper.UsersMapper;
import com.my.pojo.Complain;
import com.my.pojo.Question;
import com.my.pojo.Users;
import com.my.service.UsersService;
import com.my.util.GenerateLinkUtils;
import com.my.util.PasswordUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService
{
    Logger logger=Logger.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int insRegist(Users users)
    {
        logger.debug("前端给的密码："+users.getuPassword());
        users.setuPassword(PasswordUtil.generate(users.getuPassword()));
        if (PasswordUtil.verify("e10adc3949ba59abbe56e057f20f883e",users.getuPassword()))
        {
            System.out.println("密码正确");
        }
        return usersMapper.insUsers(users);
    }

    @Override
    public int updIsActiveById(Users users)
    {
        return usersMapper.updIsActiveById(users);
    }

    @Override
    public Users selUsersByEmail(Users users)
    {
        return usersMapper.selUsersByEmail(users);
    }

    @Override
    public Users selUsersById(Users users)
    {
        return usersMapper.selUsersById(users);
    }

    @Override
    public Users selUsersByUNameAndUPwd(Users users)
    {
        Users temp=usersMapper.selUsersByUName(users);
        if (temp!=null)
        {
            logger.debug("数据库Users:"+temp.toString());
        }
        logger.debug("输入User:"+users.toString());
        if (temp!=null&&PasswordUtil.verify(users.getuPassword(),temp.getuPassword()))
        {
            return temp;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Users selUsersByUNameAndUMail(Users users)
    {
        Users temp=usersMapper.selUsersByUNameAndUMail(users);
        if (PasswordUtil.verify(users.getuPassword(),temp.getuPassword()))
        {
            return temp;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Users selUsersByUName(Users users)
    {
        return usersMapper.selUsersByUName(users);
    }

    @Override
    public int updUsersByUName(Users users)
    {
        logger.debug("进入service层更改密码");
        users.setuPasswordSure(PasswordUtil.generate(users.getuPasswordSure()));
        return usersMapper.updUsersByUName(users);
    }

    @Override
    public int updCodeUrlByUmail(Users users)
    {
        logger.debug("进入service层更改密码");
        return usersMapper.updCodeUrlByUmail(users);
    }

    @Override
    public List<Question> selQuestionByUName(Question question)
    {
        return usersMapper.selQuestionByUName(question);
    }

    @Override
    public int insQuestion(Question question)
    {
        return usersMapper.insQuestion(question);
    }

    @Override
    public Question selBlacklist(Question question)
    {
        return usersMapper.selBlacklist(question);
    }

    @Override
    public int updQueAnswer(Question question)
    {
        return usersMapper.updQueAnswer(question);
    }

    @Override
    public Question selQuestionById(Question question)
    {
        return usersMapper.selQuestionById(question);
    }

    @Override
    public int udpQueDeleteById(Question question)
    {
        return usersMapper.udpQueDeleteById(question);
    }

    @Override
    public int insBlacklist(Question question)
    {
        return usersMapper.insBlacklist(question);
    }

    @Override
    public Complain selComplain(Complain complain)
    {
        return usersMapper.selComplain(complain);
    }

    @Override
    public int insComplain(Complain complain)
    {
        return usersMapper.insComplain(complain);
    }

    @Override
    public int updUPhotoByUName(Users users)
    {
        return usersMapper.updUPhotoByUName(users);
    }

    @Override
    public List<Question> selQuestionByUNameALL(Question question)
    {
        return usersMapper.selQuestionByUNameALL(question);
    }

    @Override
    public int updUnameByuId(Users users)
    {
        Users temp=usersMapper.selUsersById(users);
        int n=usersMapper.updUnameByuId(users);
        if (n>0)
        {
            usersMapper.updQuestionerByuName1(users,temp);
            usersMapper.updQuestionedByuName(users,temp);
            usersMapper.updBlacklistquerByuName(users,temp);
            usersMapper.updbBlacklistquedByuName(users,temp);
            usersMapper.updReporterByuName(users,temp);
            usersMapper.updInformantByuName(users,temp);
        }
        else
        {
            logger.debug("更新数据库失败");
        }
        return n;
    }

    @Override
    public int updUPasswordByUname(Users users)
    {
        Users temp=usersMapper.selUsersByUName(users);
        logger.debug("进入service层判断输入的密码和以前密码是否一样，更改密码");
        if (temp!=null&&PasswordUtil.verify(users.getuPassword(),temp.getuPassword()))
        {
            logger.debug("原密码校验正确");
            users.setuPasswordSure(PasswordUtil.generate(users.getuPasswordSure()));
            return usersMapper.updUPasswordByUname(users);
        }
        else
        {
            logger.debug("输入的原密码错误");
            return 0;
        }
    }

    @Override
    public int updUmailByuName(Users users)
    {
        if (selUsersByEmail(users)==null)
        {
            return usersMapper.updUmailByuName(users);
        }
        else
        {
            logger.debug("邮箱已存在");
            return -1024;
        }
    }

    @Override
    public int updAcceptQuesByuName(Users users)
    {
        return usersMapper.updAcceptQuesByuName(users);
    }


    @Override
    public int selCountQuestionerByUName(Users users)
    {
        return usersMapper.selCountQuestionerByUName(users);
    }

    @Override
    public int selCountQuestionedByUName(Users users)
    {
        return usersMapper.selCountQuestionedByUName(users);
    }

    @Override
    public int selCountAnswerByUName(Users users)
    {
        return usersMapper.selCountAnswerByUName(users);
    }

    @Override
    public List<Users> selAllUsers()
    {
        return usersMapper.selAllUsers();
    }

    @Override
    public int updIsBanBuUName(Users users)
    {
        return  usersMapper.updIsBanBuUName(users);
    }

    @Override
    public List<Users> selAllUName()
    {
        return  usersMapper.selAllUName();
    }

    @Override
    public String selUPhotoByUName(Users users)
    {
        return usersMapper.selUPhotoByUName(users);
    }

    @Override
    public List<Complain> selALLComplain()
    {
        return usersMapper.selALLComplain();
    }

    @Override
    public Complain selComplainById(Complain complain)
    {
        return  usersMapper.selComplainById(complain);
    }

    @Override
    public int updDealAnswerById(Complain complain)
    {
        return usersMapper.updDealAnswerById(complain);
    }

}
