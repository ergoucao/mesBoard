package com.my.service;

import com.my.pojo.Complain;
import com.my.pojo.Question;
import com.my.pojo.Users;

import java.util.List;

public interface UsersService
{
    int insRegist(Users users);
    int updIsActiveById(Users users);
    Users selUsersByEmail(Users users);
    Users selUsersById(Users users);
    Users selUsersByUNameAndUPwd(Users users);
    Users selUsersByUNameAndUMail(Users users);
    Users selUsersByUName(Users users);
    int updUsersByUName(Users users);
    int updCodeUrlByUmail(Users users);
    List<Question> selQuestionByUName(Question question);
    int insQuestion(Question question);
    Question selBlacklist(Question question);
    int updQueAnswer(Question question);
    Question selQuestionById(Question question);
    int udpQueDeleteById(Question question);
    int insBlacklist(Question question);
    Complain selComplain(Complain complain);
    int insComplain(Complain complain);
    int updUPhotoByUName(Users users);
    List<Question> selQuestionByUNameALL(Question question);
    int updUnameByuId(Users users);
    int updUPasswordByUname(Users users);
    int updUmailByuName(Users users);
    int updAcceptQuesByuName(Users users);
    int selCountQuestionerByUName(Users users);
    int selCountQuestionedByUName(Users users);
    int selCountAnswerByUName(Users users);
    List<Users> selAllUsers();
    int updIsBanBuUName(Users users);
    List<Users> selAllUName();
    String selUPhotoByUName(Users users);
    List<Complain> selALLComplain();
    Complain selComplainById(Complain complain);
    int updDealAnswerById(Complain complain);
}

