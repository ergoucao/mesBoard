package com.my.mapper;

import com.my.pojo.Complain;
import com.my.pojo.Question;
import com.my.pojo.RiskManagement;
import com.my.pojo.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UsersMapper
{
// 根据邮箱查找用户
    @Select("select * from musers where uMail=#{uMail}")
    Users selUsersByEmail(Users users);

//   根据id查找用户
    @Select("select * from musers where id=#{id}")
    Users selUsersById(Users users);

//    插入用户，并默认未激活。
    @Insert("insert into musers values(default,#{uName},#{uPassword},#{uPhoto},#{uMail},false,#{codeUrl},false,false,default)")
    int insUsers(Users users);

// 通过id改变数据库中用户是否激活。
    @Update("UPDATE musers SET isActive=#{isActive} WHERE id=#{id} ")
    int updIsActiveById(Users users);

//  通过用户名查找用户
    @Select("select * from musers where uName=#{uName}")
    Users selUsersByUName(Users users);

//   通过邮箱查找用户
    @Select("select * from musers where uName=#{uName}")
    Users selUsersByUNameAndUMail(Users users);

//   通过用户名更改密码
    @Update("UPDATE musers SET uPassword=#{uPasswordSure} WHERE uName=#{uName};")
    int updUsersByUName(Users users);

//    通过邮箱更改codeUrl
    @Update("UPDATE musers SET codeUrl=#{codeUrl} WHERE uMail=#{uMail}")
    int updCodeUrlByUmail(Users users);

//   通过用户名查询现在的留言板
    @Select("select * from question where questioned=#{questioned} and isDelete=false" )
    List<Question> selQuestionByUName(Question question);

//       通过用户名查询现在的留言板
    @Select("select * from question where questioned=#{questioned}" )
    List<Question> selQuestionByUNameALL(Question question);

//    在question中添加提问
    @Insert("INSERT INTO question VALUES(DEFAULT,#{questioner},#{questioned},#{question},FALSE,#{answer})")
    int insQuestion(Question question);

//    在黑名单表中进行查找拉黑人
    @Select("SELECT * FROM blacklist WHERE questioner=#{questioner} AND questioned=#{questioned}")
    Question selBlacklist(Question question);

//    修改问题的回复
    @Update("UPDATE question SET answer=#{answer} WHERE id=#{id}")
    int updQueAnswer(Question question);

//       通过问题ID查询现在的留言板
    @Select("select * from question where id=#{id} and isDelete=false" )
    Question selQuestionById(Question question);

//    通过问题ID标记删除问题留言
    @Update("UPDATE question SET isDelete=1 WHERE id=#{id}")
    int udpQueDeleteById(Question question);

//    增加黑名单
    @Insert("INSERT INTO blacklist VALUES(DEFAULT,#{questioner},#{questioned});")
    int insBlacklist(Question question);

//       查询举报。
    @Select("SELECT * FROM complain WHERE reporter=#{reporter} AND informant=#{informant};")
    Complain selComplain(Complain complain);

//    通过id查询举报
@Select("SELECT * FROM complain WHERE id=#{id};")
    Complain selComplainById(Complain complain);

//    增加举报
    @Insert("INSERT INTO complain VALUES(DEFAULT,#{reporter},#{informant},#{reason},DEFAULT)")
    int insComplain(Complain complain);

//   修改头像地址通过用户名
    @Update("UPDATE musers SET uPhoto=#{uPhoto} WHERE uName=#{uName};")
    int updUPhotoByUName(Users users);

//   修改通过id修改用户名
    @Update("UPDATE musers SET uName=#{uName} WHERE id=#{id}")
    int updUnameByuId(Users users);

//    通过用户名更改密码
    @Update("UPDATE musers SET uPassword=#{uPasswordSure} WHERE uName=#{uName}")
    int updUPasswordByUname(Users users);

//    更新其他表中的用户名
    @Update("UPDATE question SET questioner=#{0.uName} WHERE questioner=#{1.uName}")
    int updQuestionerByuName1(Users users,Users temp);
    @Update("UPDATE question SET questioned=#{0.uName} WHERE questioned=#{1.uName}")
    int updQuestionedByuName(Users users,Users temp);
    @Update("UPDATE blacklist SET questioner=#{0.uName} WHERE questioner=#{1.uName}")
    int updBlacklistquerByuName(Users users,Users temp);
    @Update("UPDATE blacklist SET questioned=#{0.uName} WHERE questioned=#{1.uName}")
    int updbBlacklistquedByuName(Users users,Users temp);
    @Update("UPDATE complain SET reporter=#{0.uName} WHERE reporter=#{1.uName}")
    int updReporterByuName(Users users,Users temp);
    @Update("UPDATE complain SET informant=#{0.uName} WHERE informant=#{1.uName}")
    int updInformantByuName(Users users,Users temp);

//    更改用户表中邮箱且将激活状态置为未激活
    @Update("UPDATE musers SET uMail=#{uMail},isActive=false WHERE uName=#{uName}")
    int updUmailByuName(Users users);

//    通过用户名更改提问状态
    @Update("UPDATE musers SET acceptQues=#{acceptQues} WHERE uName=#{uName}")
    int updAcceptQuesByuName(Users users);

//    输出所有的用户信息
    @Select("select * from musers")
    List<Users> selAllUsers();

//   查找某个人提出问题的数量
    @Select("SELECT COUNT(*) FROM question WHERE questioner=#{uName}")
    int selCountQuestionerByUName(Users users);

//   查找某个人的提问箱中的问题数量
    @Select("SELECT COUNT(*) FROM question WHERE questioned=#{uName}")
    int selCountQuestionedByUName(Users users);

//    查找某个人回答数量
    @Select("SELECT COUNT(*) FROM question WHERE questioned=#{uName} AND answer IS NOT NULL")
    int selCountAnswerByUName(Users users);

//    通过用户名修改封禁状态
    @Update("UPDATE musers SET isBan=#{isBan} WHERE uName=#{uName}")
    int updIsBanBuUName(Users users);

//    输出所有的用户名
    @Select("SELECT uName FROM musers")
    List<Users> selAllUName();

//    通过用户名输出头像地址
    @Select("SELECT uPhoto FROM musers WHERE uName=#{uName}")
    String selUPhotoByUName(Users users);

//    输出所有的举报信息
    @Select("SELECT * FROM complain")
    List<Complain> selALLComplain();

//    通过id改变complain的处理结果
    @Update("UPDATE  complain SET dealAnswer=#{dealAnswer} WHERE id=#{id}")
    int updDealAnswerById(Complain complain);

//    通过uid查找风控信息
    @Select("Select * from riskManagement where uid=#{uid}")
    RiskManagement selRMByUId(RiskManagement riskManagement);

//    增加风控信息
    @Insert("INSERT INTO riskManagement VALUES(DEFAULT,#{uid},#{city},0,0)")
    int insRMByUId(RiskManagement riskManagement);

//    改变错误登录次数
    @Update("UPDATE riskManagement SET wrongLoginN=#{wrongLoginN} WHERE rmId=#{wrongLoginN}")
    int updRMWLByRmId(RiskManagement riskManagement);

//    改变常登录城市次数。
    @Update("UPDATE riskManagement SET judgeCity=#{judgeCity} WHERE rmId=#{rmId}")
    int updRMWJCyRmId(RiskManagement riskManagement);

//    改变常登录城市
    @Update("UPDATE riskManagement SET city=#{city},judgeCity=0 WHERE rmId=#{rmId}")
    int updRMCityByRmId(RiskManagement riskManagement);

}
