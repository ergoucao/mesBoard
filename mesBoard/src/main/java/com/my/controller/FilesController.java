package com.my.controller;

import com.my.pojo.Users;
import com.my.service.FilesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class FilesController
{
    @Autowired
    private FilesService filesServiceImpl;

    @RequestMapping("show")
    public String show(Model model)
    {
        Model list = model.addAttribute("list", filesServiceImpl.show());
        return "/main.jsp";
    }

    @RequestMapping("download")
    public void download(int id, String name, HttpServletResponse resp,HttpServletRequest req)
    {
        resp.setHeader("Content-Disposition","attachment;filename="+name);
        try
        {
            ServletOutputStream os=resp.getOutputStream();
            File file=new File(req.getServletContext().getRealPath("files"),name);
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
            os.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(id);
        filesServiceImpl.updCount(id,(Users)req.getSession().getAttribute("user"),name);
    }
}
