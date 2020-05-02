package com.my.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;


public class XSSWrapper extends HttpServletRequestWrapper {

    public XSSWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String parameter) {
        String rq = super.getParameter(parameter);
        return stripXSS(rq);
    }

    private String stripXSS(String rq)
    {
        if (rq != null) {
            //若符合脚本格式则清除，不区分大小写。
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            rq = scriptPattern.matcher(rq).replaceAll("");
            //清除脚本标签，不区分大小写。            
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            rq = scriptPattern.matcher(rq).replaceAll("");
            //清除脚本。     
            scriptPattern = Pattern.compile("<script(.*?)>",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            rq = scriptPattern.matcher(rq).replaceAll("");
            //清除eval,
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);//不区分大小写，匹配多行，匹配换行符号。
            rq = scriptPattern.matcher(rq).replaceAll("");
            //清除javascript,不区分大小写。            
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            rq = scriptPattern.matcher(rq).replaceAll("");
        }
        return rq;
    }
}