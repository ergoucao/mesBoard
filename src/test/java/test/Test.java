package test;

import java.io.File;

public class Test
{
    public static void main(String[] args)
    {
        String filePath="D:\\upload";
        File targetFile=new File(filePath,"123456");
        System.out.println(targetFile.getPath());
    }
}
