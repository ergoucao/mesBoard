package com.my.interceptor;

import com.my.pojo.JWTResult;
import com.my.pojo.ResponseData;
import com.my.util.JWTUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

public class loginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        Logger logger= Logger.getLogger(loginInterceptor.class);
        String uri=httpServletRequest.getRequestURI();
        logger.debug("当前uri"+uri);
        if (uri!=null)
        {

            if(uri.equals("/mesBoard_Web_exploded/login"))
            {
                return true;
            }
            else if(uri.equals("/mesBoard_Web_exploded/regist"))
            {
                return true;
            }
            else if(uri.equals("/mesBoard_Web_exploded/captcha"))
            {
                return true;
            }
            else if(uri.equals("/mesBoard_Web_exploded/pwdReset"))
            {
                return true;
            }
            else if(uri.equals("/mesBoard_Web_exploded/reset"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/pwdDataReset"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/getPhoto"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/question"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/onequestion"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/activate"))
            {
                return true;
            }
            else if (uri.equals("/mesBoard_Web_exploded/getUsersList"))
            {
                return true;
            }
            else
            {
                String token=httpServletRequest.getHeader("Authorization");
                logger.debug("获得token"+token);
                if (token==null)
                {
                    return false;
                }
                JWTResult result= JWTUtils.validateJWT(token);
                logger.debug("进行token检验"+result);
                if (result.isSuccess())
                {
                    logger.debug("token正常");
                    if (uri.equals("/mesBoard_Web_exploded/getUses"))
                    {
                        if (!result.getClaims().getId().equals("admin"))
                        {
                            logger.debug("非管理人员");
                            return false;
                        }
                    }
                    if (uri.equals("/mesBoard_Web/upfile"))
                    {
                        return checkPicture(httpServletRequest,httpServletResponse,o);
                    }

                    return true;
                }
                else
                {
                    httpServletResponse.setContentType("text/html;charset=utf-8");
                    httpServletResponse.getWriter().write("请登录！！！");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception
    {

    }

    private boolean checkPicture(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        boolean flag = true;
        if (httpServletRequest instanceof MultipartHttpServletRequest)
        {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();

            while (iterator.hasNext())
            {
                String formKey = (String) iterator.next();
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(formKey);
                String filename = multipartFile.getOriginalFilename();
                if (!checkType(filename))
                {
                    httpServletRequest.setAttribute("typeError", "不支持的文件类型");
                    httpServletRequest.setAttribute("add", "error.html");
                    httpServletRequest.setAttribute("typeCode", "1");
                    flag = false;
                } else
                {
                    httpServletRequest.setAttribute("typeCode", "200");
                }
            }
        }
        return flag;
    }

    private boolean checkType(String filename)
    {
        String suffixList="jpg,gif,png,bmp,jpeg";
        String suffix=filename.substring(filename.lastIndexOf(".")+1,filename.length());
        if (suffixList.contains(suffix.trim().toLowerCase()))
        {
            return true;
        }
        return false;
    }
}

