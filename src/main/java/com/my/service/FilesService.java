package com.my.service;

import com.my.pojo.Files;
import com.my.pojo.Users;

import java.util.List;

public interface FilesService
{
    List<Files> show();
    int updCount(int id, Users users, String name);
}
