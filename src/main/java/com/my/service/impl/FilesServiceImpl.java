package com.my.service.impl;

import com.my.mapper.FilesMapper;
import com.my.pojo.Files;
import com.my.pojo.Users;
import com.my.service.FilesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesServiceImpl implements FilesService
{
    @Autowired
    private FilesMapper filesMapper;

    @Override
    public List<Files> show()
    {
        return filesMapper.selAll();
    }

    @Override
    public int updCount(int id, Users users, String name)
    {
        Logger logger=Logger.getLogger(FilesServiceImpl.class);
//        logger.debug(users.getUsername()+"下载了"+name);
        return filesMapper.updCountById(id);
    }


}
