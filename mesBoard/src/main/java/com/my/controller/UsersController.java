package com.my.controller;

import com.my.pojo.*;
import com.my.service.UsersService;
import com.my.util.EmailUtils;
import com.my.util.GenerateLinkUtils;
import com.my.util.JWTUtils;
import com.my.util.RegistCheckUtils;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class UsersController
{
    Logger logger = Logger.getLogger(UsersController.class);

    @Autowired
    private UsersService usersServiceImpl;

    @RequestMapping("/giveAnserCom")
    @ResponseBody
    public ResponseData giveAnserCom(Integer id,boolean ban,String banText,HttpServletRequest req)
    {
        logger.debug("开始处理举报");
        Complain complain=new Complain();
        complain.setId(id);
        complain=usersServiceImpl.selComplainById(complain);
        ResponseData responseData=new ResponseData();
        if (complain!=null)
        {
            complain.setDealAnswer(banText);
            if (usersServiceImpl.updDealAnswerById(complain)>0)
            {
                logger.debug("在数据库里修改成功:"+complain);
                responseData.setCode(200);
                responseData.setMsg("在数据库里修改成功");
                responseData.setAdd("dashboardIndex.html");
                ResponseData temp=changeBan(ban,complain.getInformant(),req);
                if (temp!=null)
                {
                    logger.debug("修改封禁状态成功");
                }
                else
                {
                    logger.debug("修改封禁状态失败");
                }
            }
            else
            {
                logger.debug("在数据库里修改失败:"+complain);
                responseData.setCode(1301);
                responseData.setMsg("在数据库里修改失败");
            }
        }
        else
        {
            logger.debug("查询失败");
            responseData.setCode(1302);
            responseData.setMsg("在数据库查找相应举报失败");
        }
        return responseData;
    }

    @RequestMapping("/dealComplain")
    @ResponseBody
    public List<Complain> dealComplain(HttpServletRequest req)
    {
        logger.debug("开始获取举报信息");
        List<Complain> complainList=usersServiceImpl.selALLComplain();
        if (complainList.size()<=0)
        {
            logger.debug("获取失败");
        }
        else
        {
            logger.debug("获取成功");
        }
        return complainList;
    }

    @RequestMapping("/getPhoto")
    @ResponseBody
    public ResponseData getPhoto(String uName,HttpServletRequest req)
    {
        logger.debug("开始获取头像地址");
        ResponseData responseData=checkToken(req);
        Users users=new Users();
        if (responseData.getCode()==200&&uName==null)
        {
            uName=responseData.getJsti();
        }
        users.setuName(uName);
        logger.debug(users.toString());
        String temAdd=usersServiceImpl.selUPhotoByUName(users);
        logger.debug(temAdd);
        if (temAdd!=null)
        {
            try
            {
                responseData.setAdd("image/"+temAdd.substring(temAdd.lastIndexOf("\\")+1));
                responseData.setCode(200);
                responseData.setMsg("获取头像成功");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                responseData.setCode(1203);
                responseData.setMsg("解析头像地址失败");
            }
        }
        else
        {
            responseData.setCode(1201);
            responseData.setMsg("获取头像失败");
        }
        return responseData;
    }

    @RequestMapping("/getUsersList")
    @ResponseBody
    public List<Users> getUsersList(HttpServletRequest req)
    {
        logger.debug("开始获取用户名列表");
        List<Users> usersList=usersServiceImpl.selAllUName();
        logger.debug("从数据库查到的值"+usersList.get(0).getuName());
        if (usersList!=null)
        {
            logger.debug("获取成功");
        }
        else
        {
            logger.debug("获取的为空");
        }
        return usersList;
    }


    @RequestMapping("/changeBan")
    @ResponseBody
    public ResponseData changeBan(boolean isBan,String uName,HttpServletRequest req)
    {
        logger.debug("开始更改用户"+uName+"的封禁状态："+isBan);
        Users users=new Users();
        users.setuName(uName);
        users.setBan(isBan);
        ResponseData responseData=checkToken(req);

        if (usersServiceImpl.updIsBanBuUName(users)>0)
        {
            responseData.setCode(200);
            responseData.setMsg("修改封禁状态成功");
        }
        else
        {
            responseData.setCode(11001);
            responseData.setMsg("修改封禁状态失败");
        }
        return responseData;
    }

    @RequestMapping("/getUses")
    @ResponseBody
    public  List<UsersStatis> getUses(HttpServletRequest req,HttpServletResponse resp)
    {
        logger.debug("获取所有用户的统计信息");
        List<Users> usersList=usersServiceImpl.selAllUsers();
        List<UsersStatis> usersStatisList=new ArrayList<UsersStatis>(usersList.size()+1);
        logger.debug(usersList.size());
        for (int i=0;i<usersList.size();i++)
        {

            UsersStatis ustemp=new UsersStatis();
            Users utemp=usersList.get(i);
            ustemp.setuId (utemp.getId());
            ustemp.setuName(utemp.getuName());
            ustemp.setuMail(utemp.getuMail());
            ustemp.setBan(utemp.isBan());
            ustemp.setQuestionNum(usersServiceImpl.selCountQuestionedByUName(utemp));
            ustemp.setAnswerNum(usersServiceImpl.selCountAnswerByUName(utemp));
            ustemp.setAskNum(usersServiceImpl.selCountAnswerByUName(utemp));
            usersStatisList.add(ustemp);
            logger.debug("用户统计信息："+usersStatisList.get(i));
        }
        return usersStatisList;
    }

    @RequestMapping("/changeAcceptQues")
    @ResponseBody
    public ResponseData  changeAcceptQues(boolean acceptQues,HttpServletRequest req)
    {
        ResponseData responseData=checkToken(req);
        if (responseData.getCode()!=200)
        {
            logger.debug("请登录");
        }
        else
        {
            Users users=new Users();
            users.setuName(responseData.getJsti());
            users.setAcceptQues(acceptQues);
            if (usersServiceImpl.selUsersByUName(users).isAcceptQues()==users.isAcceptQues())
            {
                responseData.setMsg("提问箱状态："+acceptQues+" 请不要重复操作");
                responseData.setCode(10001);
            }
            else
            {
                if (usersServiceImpl.updAcceptQuesByuName(users)>0)
                {
                    responseData.setMsg("修改提问状态成功");
                }
                else
                {
                    responseData.setMsg("修改提问状态失败");
                    responseData.setCode(10002);
                }
            }
        }
        return responseData;
    }


    @RequestMapping("/changeEmail")
    @ResponseBody
    public ResponseData changeEmail(String uPassword, String uMail, HttpServletRequest req)
    {
        ResponseData responseData = checkToken(req);
        Users users = new Users();
        users.setuName(responseData.getJsti());
        users.setuPassword(uPassword);
        users.setuMail(uMail);
        logger.debug("前端输入的users:"+users);
        Users temp=usersServiceImpl.selUsersByUNameAndUPwd(users);
        if (temp!=null)
        {
            logger.debug("密码输入正确");
            int t=usersServiceImpl.updUmailByuName(users);
            if (t>0)
            {
                logger.debug("邮箱修改成功");
                temp.setuMail(uMail);
                logger.debug("发送激活邮件");
                responseData.setCode(200);
                EmailUtils.sendAccountActivateEmail(temp);
            }
            else if (t==-1024)
            {
                logger.debug("邮箱已被绑定，绑定失败");
                responseData.setCode(9001);
                responseData.setMsg("邮箱已被绑定，绑定失败");
            }
            else
            {
                logger.debug("邮箱修改失败");
                responseData.setCode(9002);
                responseData.setMsg("邮箱修改失败");
            }
        }
        else
        {
            logger.debug("邮箱修改失败");
            responseData.setCode(9003);
            responseData.setMsg("邮箱修改失败");
        }
        return responseData;
    }

    @RequestMapping("/chaPwd")
    @ResponseBody
    public ResponseData chaPwd(String uPassword,String uPasswordCha,HttpServletRequest req)
    {
        ResponseData responseData = checkToken(req);
        Users users = new Users();
        users.setuName(responseData.getJsti());
        users.setuPassword(uPassword);
        users.setuPasswordSure(uPasswordCha);
        if (usersServiceImpl.updUPasswordByUname(users) > 0)
        {
            logger.debug("修改密码成功");
            users=usersServiceImpl.selUsersByUName(users);
            logger.debug("更改用户名,授予token"+users);
            users.setuPassword("");
            String JWTToken = JWTUtils.createJWT(users.getuName(),"mes-test"
                    , JWTUtils.generalSubject(users),(long)1*60*1000);
            responseData.setCode(200);
            responseData.setUsers(null);
            responseData.setMsg("登录成功");
            responseData.setToken(JWTToken);

            responseData.setMsg("修改密码成功");
        }
        else
        {
            logger.debug("修改密码失败");
            responseData.setCode(8001);
            responseData.setMsg("修改密码失败");
        }
        return responseData;
    }

    @RequestMapping("/chaName")
    @ResponseBody
    public ResponseData chaName(String changeName, HttpServletRequest req)
    {
        ResponseData responseData=checkToken(req);
        Users users=new Users();
        users.setuName(changeName);
        Users users1=new Users();
        users1.setuName(responseData.getJsti());
        if (responseData.getCode()==200)
        {
            logger.debug("身份验证通过");
            if (usersServiceImpl.selUsersByUName(users)==null)
            {
                if ((users1=usersServiceImpl.selUsersByUName(users1))!=null)
                {
                    users.setId(users1.getId());
                }
                else
                {
                    logger.debug("用户出错");
                    return null;
                }
                if (usersServiceImpl.updUnameByuId(users)>0)
                {
                    users=usersServiceImpl.selUsersByUName(users);
                    logger.debug("更改用户名,授予token"+users);
                    users.setuPassword("");
                    String JWTToken= JWTUtils.createJWT(users.getuName(),"mes-test"
                            , JWTUtils.generalSubject(users),(long)1*60*1000);
                    responseData.setCode(200);
                    responseData.setUsers(null);
                    responseData.setMsg("登录成功");
                    responseData.setToken(JWTToken);

                    responseData.setCode(200);
                    responseData.setMsg("修改用户名成功");
                }
                else
                {
                    responseData.setCode(7002);
                    responseData.setMsg("修改用户名失败");
                }
            }
            else
            {
                responseData.setCode(7001);
                responseData.setMsg("用户名已存在");
            }
        }
        return responseData;
    }

    @RequestMapping("/upfile")
    @ResponseBody
    public ResponseData upfile(@RequestParam("file") MultipartFile file, HttpServletRequest req)
    {
//        获取时间
        String filePath=req.getSession().getServletContext().getRealPath("/image");
        String originFilename=file.getOriginalFilename();
        String newFilename=UUID.randomUUID()+originFilename;
        File targetFile=new File(filePath,newFilename);
        if (!targetFile.isDirectory() && !targetFile.exists())
        {
            targetFile.mkdirs();
        }
        try
        {
            file.transferTo(targetFile);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Users users=new Users();
        ResponseData responseData=checkToken(req);
        users.setuName(responseData.getJsti());
        users.setuPhoto(targetFile.getPath());
        if (usersServiceImpl.updUPhotoByUName(users)>0)
        {
            logger.debug("图片地址存入据库成功");
            responseData.setCode(200);
            responseData.setMsg("上传成功");
        }
        else
        {
            logger.debug("图片地址存入据库失败");
            responseData.setCode(6001);
            responseData.setMsg("上传失败");
        }
        return responseData;
    }

    @RequestMapping("/complain")
    @ResponseBody
    public ResponseData complain(String uName,int questionId,String complText,HttpServletRequest req)
    {
        Question question=new Question();
        question.setQuestioned(uName);
        question.setId(questionId);
        Question temp=usersServiceImpl.selQuestionById(question);
        logger.debug("从question数据库查到的数据"+temp);
        ResponseData responseData=delete(uName,questionId,req);

        Complain complain=new Complain();
        complain.setReporter(temp.getQuestioned());
        complain.setInformant(temp.getQuestioner());
        complain.setReason(complText);

        if (responseData.getCode()==200)
        {
            if (usersServiceImpl.selComplain(complain)!=null)
            {
                responseData.setCode(5001);
                responseData.setMsg("不能重复举报");
            }
            else
            {
                if(usersServiceImpl.insComplain(complain)>0)
                {
                    responseData.setCode(200);
                    logger.debug("举报成功");
                    responseData.setMsg("举报成功");
                }
                else
                {
                    responseData.setCode(5002);
                    logger.debug("举报失败");
                    responseData.setMsg("举报失败");
                }
            }
        }
        else
        {
            responseData.setCode(5003);
            logger.debug("不是相应的用户，请登录");
            responseData.setMsg("不是相应的用户，请登录");
        }

        return responseData;
    }

    @RequestMapping("/blacklist")
    @ResponseBody
    public ResponseData blacklist(String uName,int questionId,HttpServletRequest req)
    {
        Question question=new Question();
        question.setQuestioned(uName);
        question.setId(questionId);
        Question temp=usersServiceImpl.selQuestionById(question);
        logger.debug("从question数据库查到的数据"+temp);
        ResponseData responseData=delete(uName,questionId,req);
        if (responseData.getCode()==200)
        {
            if (usersServiceImpl.selBlacklist(temp)!=null)
            {
                responseData.setCode(4001);
                responseData.setMsg("不能重复拉黑");
            }
            else
            {
                if(usersServiceImpl.insBlacklist(temp)>0)
                {
                    responseData.setCode(200);
                    logger.debug("拉黑成功");
                    responseData.setMsg("拉黑成功");
                }
                else
                {
                    responseData.setCode(4002);
                    logger.debug("拉黑失败");
                    responseData.setMsg("拉黑失败");
                }
            }
        }
        else
        {
            responseData.setCode(4003);
            logger.debug("不是相应的用户，请登录");
            responseData.setMsg("不是相应的用户，请登录");
        }
        return responseData;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(String uName,int questionId,HttpServletRequest req)
    {
        ResponseData responseData=checkToken(req);
        Question question=new Question();
        question.setQuestioned(responseData.getJsti());
        question.setId(questionId);

        Question temp=usersServiceImpl.selQuestionById(question);
        if (temp!=null&&question.getQuestioned().equals(temp.getQuestioned()))
        {
            if (usersServiceImpl.udpQueDeleteById(question)>0)
            {
                responseData.setCode(200);
                logger.debug("标记问题删除状态成功");
                responseData.setMsg("删除成功");
            }
            else
            {
                responseData.setCode(3001);
                logger.debug("标记问题删除状态失败");
                responseData.setMsg("删除失败");
            }
        }
        else
        {
            responseData.setCode(30012);
            logger.debug("不是相应的用户");
            responseData.setMsg("不是相应的用户，请登录");
        }

        responseData.setAdd("index.html?uName="+uName);
        return responseData;
    }

    @RequestMapping("/answer")
    @ResponseBody
    public ResponseData answer(String uName,int questionId,String answer,HttpServletRequest request)
    {
        ResponseData responseData=new ResponseData();

        String token=request.getHeader("Authorization");
        logger.debug("获得的token"+token);
        JWTResult result=JWTUtils.validateJWT(token);
        Question question=new Question();
        question.setQuestioned(result.getClaims().getId());
//        question.setQuestioner(result.getClaims().getId());
        question.setAnswer(answer);
        question.setId(questionId);

        Question temp=usersServiceImpl.selQuestionById(question);
        if (temp!=null&&question.getQuestioned().equals(temp.getQuestioned()))
        {
            if (usersServiceImpl.updQueAnswer(question)>0)
            {
                responseData.setCode(200);
                logger.debug("存储数据成功");
                responseData.setMsg("回复成功");
            }
            else
            {
                logger.debug("存储数据失败");
                responseData.setCode(2001);
                responseData.setMsg("回复失败");
            }
            responseData.setToken(token);
        }
        else
        {
            logger.debug("回复失败");
            responseData.setCode(2002);
            responseData.setMsg("请登录！");
        }
        responseData.setAdd("index.html?uName="+uName);
        return  responseData;
    }


    @RequestMapping("/onequestion")
    @ResponseBody
    public Question onequestion(String uName,Integer questionId,HttpServletRequest req)
    {
        logger.debug("开始查找"+uName+"的留言。"+questionId);
        ResponseData responseData=checkToken(req);
        Users users=new Users();
        if (responseData.getCode()==200&&uName==null)
        {
            uName=responseData.getJsti();
        }
        Question question=new Question();
        question.setQuestioned(uName);
        question.setId(questionId);
        Question temp=usersServiceImpl.selQuestionById(question);
        temp.setQuestioner("****");
        return temp;
    }

    @RequestMapping("/giveQues")
    @ResponseBody
    public ResponseData giveQues(String uName,String qestion,HttpServletRequest request)
    {
        ResponseData responseData=new ResponseData();

        String token=request.getHeader("Authorization");
        logger.debug("获得的token"+token);
        JWTResult result=JWTUtils.validateJWT(token);
        Question question=new Question();
        question.setQuestioned(uName);
        question.setQuestioner(result.getClaims().getId());
        question.setQuestion(qestion);

        if (usersServiceImpl.insQuestion(question)>0)
        {
            responseData.setCode(200);
            logger.debug("存储数据成功");
            responseData.setMsg("提问成功");
        }
        else
        {
            logger.debug("存储数据成功");
            responseData.setCode(1001);
            responseData.setMsg("提问失败");
        }
        responseData.setAdd("index.html?uName="+uName);
        return  responseData;
    }


    @RequestMapping("/question")
    @ResponseBody
    public List<Question> getQuestion(String uName,HttpServletRequest req)
    {
        logger.debug("开始查找" + uName + "的留言。");
        Question question = new Question();
        ResponseData temResponseData=checkToken(req);
        if (temResponseData.getCode()==200&&uName==null)
        {
            uName=temResponseData.getJsti();
        }
        question.setQuestioned(uName);
        Users users=new Users();
        users.setuName(uName);
        Users temUser=usersServiceImpl.selUsersByUName(users);
        List<Question> temp=null;
        if (temUser!=null&&temUser.isBan()==false)
        {
            ResponseData responseData = checkToken(req);
            if (responseData.getCode() == 200)
            {
//           管理员或用户本人登录。
                temp = usersServiceImpl.selQuestionByUNameALL(question);
            }
            else
            {
//            其他人登录。
                temp=usersServiceImpl.selQuestionByUName(question);
            }
            if (temResponseData.getJsti()!=null&&!temResponseData.getJsti().equals("admin"))
            {
                for (int i=0;i<temp.size();i++)
                {
                    logger.debug("获得的留言："+i+" "+ temp.get(i));
                    temp.get(i).setQuestioner("");
                }
            }
        }
        else
        {
            logger.debug("用户处于封禁状态");
        }
        return temp;
    }

    @RequestMapping("/ask")
    @ResponseBody
    public ResponseData canAsk(String uName,HttpServletRequest req,HttpServletResponse resp)
    {
        logger.debug("被提问者"+uName);
        Question question=new Question();
        question.setQuestioned(uName);
        ResponseData responseData=checkToken(req);
        logger.debug("检测token完成其返回值"+responseData);
        if (responseData.getCode()==200)
        {
            logger.debug("开始检查提问者的状态");
//            提问者
            Users temp=new Users();
            temp.setuName(responseData.getJsti());
            question.setQuestioner(responseData.getJsti());
            Users questioner=usersServiceImpl.selUsersByUName(temp);
            if (questioner!=null)
            {
                if (questioner.isActive())
                {
                    logger.debug(questioner.getuName()+"处于可提问状态");
                    temp.setuName(uName);
                    Users questioned=usersServiceImpl.selUsersByUName(temp);
                    if (questioned.isActive())
                    {
                        logger.debug(questioned.getuName()+"处于可被提问状态");
                        if (usersServiceImpl.selBlacklist(question)==null)
                        {
                            logger.debug("两者无黑名单的关系");
                            responseData.setAdd("ask.html?uName="+uName);
                        }
                        else
                        {
                            logger.debug("两者有黑名单的关系");
                            responseData.setMsg("两者有黑名单的关系");
                            responseData.setCode(1104);
                        }
                    }
                    else
                    {
                        logger.debug(questioned.getuName()+"未被激活");
                        responseData.setMsg(questioned.getuName()+"未被激活");
                        responseData.setCode(1103);
                    }
                }
                else
                {
                    logger.debug(questioner.getuName()+"不处于可提问状态");
                    responseData.setMsg(questioner.getuName()+"不处于可提问状态");
                    responseData.setCode(1102);
                }
            }
            else
            {
                logger.debug("找不到用户");
                responseData.setMsg("找不到用户");
                responseData.setCode(1101);
            }
        }
        return  responseData;
    }


    @RequestMapping("/check")
    @ResponseBody
    public ResponseData checkJWT(HttpServletRequest req, HttpServletResponse resp)
    {
        return checkToken(req);
    }

//    @RequestMapping("/main")
//    @ResponseBody
//    public ResponseData main(HttpServletRequest req,HttpServletResponse resp)
//    {
//
//        ResponseData responseData=checkToken(req);
//
//        logger.debug("在resopnseData.getCode()"+responseData.getCode());
//        if (responseData.getCode()==200)
//        {
//            try
//            {
//                responseData.setAdd("main.html");
//                resp.setContentType("utf-8");
//                resp.getWriter().write("这里是主页面");
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            try
//            {
//                responseData.setAdd("login.html");
//                resp.getWriter().write(responseData.getMsg());
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        return responseData;
//    }

    @RequestMapping("getUsers")
    @ResponseBody
    public ResponseData getUsers(HttpServletRequest req )
    {
        String token=req.getHeader("Authorization");
        JWTResult result=JWTUtils.validateJWT(token);
        logger.debug("通过token获得payload"+result.toString());
        ResponseData responseData=new ResponseData();
        return responseData;
    }


    @RequestMapping("/pwdReset")
    @ResponseBody
    public ResponseData pwdReset(String uMail,HttpSession httpSession)
    {
        ResponseData responseData=new ResponseData();
        Users users=new Users();
        users.setuMail(uMail);
        Users u1=usersServiceImpl.selUsersByEmail(users);
        if (u1==null)
        {
            logger.debug("邮箱不存在");
            responseData.setCode(901);
        }
        else
        {
            logger.debug("即将发送重置链接到邮箱");
            responseData.setCode(200);
            responseData.setAdd("login.html");
//            更改用户唯一识别码
            u1.setCodeUrl(UUID.randomUUID().toString());
            usersServiceImpl.updCodeUrlByUmail(u1);
            logger.debug("发送邮件的users"+u1);

            httpSession.setAttribute("user",u1);
            EmailUtils.sendPwdResetEmail(u1);
        }
        return responseData;
    }


    @RequestMapping("/pwdDataReset")
    @ResponseBody
    public ResponseData pwdDataReset(String uName,String uPassword,String pwdReset,String checkCode,HttpServletRequest request)
    {
        logger.debug("开始更改密码，输入信息"+uName+uPassword);
        Users users = new Users();
        users.setuName(uName);
        users.setuPassword(uPassword);
        users.setuPasswordSure(pwdReset);
        ResponseData responseData=new ResponseData();
        Users temp= usersServiceImpl.selUsersByUName(users);

        if (temp==null)
        {
            responseData.setCode(801);//更改失败编码
            logger.debug("用户不存在");
            responseData.setMsg("用户不存在");
        }
        else if (GenerateLinkUtils.verifyCheckcode(temp,checkCode))
        {
            temp.setuPasswordSure(users.getuPasswordSure());
            logger.debug("输入的密码正确找到相应的用户"+users);
            usersServiceImpl.updUsersByUName(users);
            responseData.setCode(200);//更改成功编码
            responseData.setAdd("login.html");
        }
        else
        {
            responseData.setCode(802);
            responseData.setMsg("无效的重置密码链接");
            logger.debug("无效的重置密码链接");
        }
        return responseData;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseData login(String uName,String uPassword,String code,HttpServletRequest request)
    {
        logger.debug("开始登录，登录信息"+uName+uPassword+code);
        Users users = new Users();
        users.setuName(uName);
        users.setuPassword(uPassword);
        ResponseData responseData=new ResponseData();
        String mes_test=usersServiceImpl.selUsersByUName(users).getCodeUrl();

        if (CaptchaUtil.ver(code, request))
        {
            logger.debug("验证码正确");
            Users u1=usersServiceImpl.selUsersByUNameAndUPwd(users);
            if (u1==null)
            {
                logger.debug("使用邮箱登录 ");
                usersServiceImpl.selUsersByEmail(users);
            }
            if (u1 != null)
            {
                logger.debug("登录成功，授予token");
                users.setuPassword("");
                String JWTToken= JWTUtils.createJWT(users.getuName(),mes_test
                        , JWTUtils.generalSubject(users),(long)10*60*1000);
                responseData.setCode(200);
                responseData.setUsers(null);
                responseData.setMsg("登录成功");
                responseData.setToken(JWTToken);
                if (users.getuName().equals("admin"))
                {
                    responseData.setAdd("dashboardIndex.html?uName=admin");
                }
                else
                {
                    responseData.setAdd("manage.html");
                }
            }
            else
            {
                logger.debug("登录失败，未授予token");
                responseData.setCode(500);
                responseData.setUsers(null);
                responseData.setMsg("登录失败");
                responseData.setToken(null);
                responseData.setAdd("login.html");
            }
        }
        else
        {
            logger.debug("验证码错误");
            CaptchaUtil.clear(request);
            responseData.setMsg("验证码错误");
        }
        return responseData;
    }

//    @RequestMapping("/register")
//    public String register(Users users, MultipartFile file, HttpServletRequest req)
//    {
//        String fileName= UUID.randomUUID().toString()+file.getOriginalFilename().
//                substring(file.getOriginalFilename().lastIndexOf("."));
//        String path=req.getServletContext().getRealPath("images")+'/'+fileName;
//        try
//        {
//            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path));
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
////        webapps文件夹内容。
//        users.setuPhoto(fileName);
//        int index=usersServiceImpl.insRegister(users);
//        if (index>0)
//        {
//            req.getSession().setAttribute("user",users);
//            return "redirect:/show";
//        }
//        else
//        {
//            return "redirect:/register.jsp";
//        }
//    }

    @RequestMapping("/register")
    public  void register(HttpServletResponse resp)
    {
        try
        {
            resp.sendRedirect("/mesBoard_Web_exploded/register.html");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//    e10adc3949ba59abbe56e057f20f883e
//    e10adc3949ba59abbe56e057f20f883e
    @RequestMapping("/regist")
    @ResponseBody
    public ResponseData regist(Users user, HttpSession httpSession , String code, HttpServletResponse resp,HttpServletRequest request)
    {
        logger.debug("开始注册："+user.toString());
        user.setActive(false);
        user.setCodeUrl(UUID.randomUUID().toString());
        ResponseData responseData=new ResponseData();
        String msg=null;
        int msgCode=500;
        String add=null;
        String JWTToken=null;
        boolean userExist=false;

//        判断输入格式是否正确
        responseData= RegistCheckUtils.registCheck(user,code);
        if (responseData.getCode()==900)
        {
            logger.debug("注册信息格式不对");
            responseData.setAdd("register.html");
            return responseData;
        }

        if (CaptchaUtil.ver(code, request))
        {
            if (usersServiceImpl.selUsersByEmail(user)==null)
            {
                logger.debug("注册表的user"+user);
                if (usersServiceImpl.selUsersByUName(user)==null)
                    usersServiceImpl.insRegist(user);
                else
                {
                    logger.debug("该用户已注册");
//                    resp.sendRedirect("/mesBoard_Web_exploded/register.html");
                    add="register.html";
                    msgCode=500;
                    msg="该用户已注册";
                    userExist=true;
                }
            }
            else
            {
                logger.debug("该邮箱已注册");
//                    resp.sendRedirect("/mesBoard_Web_exploded/register.html");
                    add="register.html";
                    msgCode=501;
                    msg="该邮箱已注册";
            }
            if (userExist==false)
            {
                Users u=usersServiceImpl.selUsersByEmail(user);
                if (u!=null)
                {
                    user.setId(u.getId());
                    logger.debug("登录成功，授予token");
                    JWTToken= JWTUtils.createJWT(u.getuName(),"mes-test"
                            , JWTUtils.generalSubject(u),(long)1*60*1000);
                    msgCode=200;
                }
                else
                {
                    logger.debug("注册失败（为存入用户信息）");
                }

                httpSession.setAttribute("user",user);

                EmailUtils.sendAccountActivateEmail(user);

//            try {
//                resp.setContentType("text/html;charset=utf-8");
//                resp.getWriter().write("激活邮件已经发送，请注意提醒查收");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
                msg="激活邮件已经发送，请注意提醒查收";
                add="manage.html";
            }
        }
        else
        {
            logger.debug("验证码错误");
            msgCode=502;
            msg="验证码错误";
            add="register.html";
//            try
//            {
//                resp.sendRedirect("/mesBoard_Web_exploded/register.html");
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
        }
        responseData.setCode(msgCode);
        responseData.setUsers(null);
        responseData.setMsg(msg);
        responseData.setToken(JWTToken);
        responseData.setAdd(add);
        return responseData;
    }

    @RequestMapping("/activate")
    public void activate(String id,String checkCode,HttpServletResponse resp)
    {
//        根据用户id查找用户
        Users temp=new Users();
        temp.setId(Integer.parseInt(id));
        Users user=usersServiceImpl.selUsersById(temp);
        logger.debug("通过id获得的用户："+user);

//        进行验证
        if (GenerateLinkUtils.verifyCheckcode(user,checkCode))
        {
            user.setActive(true);
            usersServiceImpl.updIsActiveById(user);
            try
            {
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("激活成功");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private ResponseData checkToken(HttpServletRequest req)
    {
        String token=req.getHeader("Authorization");
        logger.debug("获得的token"+token);
        JWTResult result=JWTUtils.validateJWT(token);
        ResponseData responseData=new ResponseData();

        if (result.isSuccess())
        {
            if (judgeUsersWhenCgePwd(result)==false)
            {
                responseData.setCode(501);
                responseData.setMsg("请登录");
            }
            else
            {
                responseData.setCode(200);
                responseData.setUsers(result.getClaims().getSubject());
                responseData.setJsti(result.getClaims().getId());
                responseData.setMsg("welcome "+responseData.getJsti());
//            更新token
                String newToken=JWTUtils.createJWT(result.getClaims().getId(),
                        result.getClaims().getIssuer(), result.getClaims().getSubject(),
                        (long)10*60*1000);
                responseData.setToken(newToken);
                logger.debug("check:"+responseData.toString());
            }
            return  responseData;
        }
        else
        {
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }
    }

    private boolean judgeUsersWhenCgePwd(JWTResult result)
    {
        Users users=new Users();
        users.setuName(result.getClaims().getId());
        if (usersServiceImpl.selUsersByUName(users).getCodeUrl().equals(result.getClaims().getIssuer()))
        {
            return true;
        }
        return  false;
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

//    实现验证码。
    @RequestMapping("/validCode")
    public void ValidCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //创建一张图片
        //单位：像素；
        BufferedImage image=new BufferedImage(200,100,BufferedImage.TYPE_3BYTE_BGR);

        //画板
        Graphics2D gra=image.createGraphics();
        gra.setColor(Color.WHITE);
//        原点
        gra.fillRect(0,0,200,100);
//        获取0~9随机数。
        List<Integer> code=new ArrayList<Integer>();
        Random random=new Random();
        for (int i=0;i<4;i++)
        {
            code.add(random.nextInt(10));
        }

        gra.setColor(Color.BLACK);
        Color[] colors=new Color[]{Color.RED,Color.YELLOW,Color.BLACK,Color.BLUE};
        gra.setFont(new Font("宋体",Font.ITALIC|Font.BOLD,40));
        for(int i=0;i<code.size();i++)
        {
            gra.setColor(colors[random.nextInt(colors.length)]);
            gra.drawString(code.get(i)+"",i*40,70+(random.nextInt(21)-10));
        }
        gra.setColor(colors[random.nextInt(colors.length)]);
        //画横线
        for (int i=0;i<2;i++)
        {
            gra.setColor(colors[random.nextInt(colors.length)]);
            gra.drawLine(0,random.nextInt(101),200,random.nextInt(101));
        }

        ServletOutputStream outputStream=resp.getOutputStream();
        //工具类。
        ImageIO.write(image,"jpg",outputStream);
//        把验证码放入session中
//        HttpSession session= req.getSession();
//        session.setAttribute("code",""+code.get(0)+code.get(1)+code.get(2)+code.get(3));
    }
}