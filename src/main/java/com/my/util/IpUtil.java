package com.my.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;

public class IpUtil
{
    /**
     * 获取登录用户的IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip=request.getHeader("x-forwarded-for");
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip="127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip=ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 通过IP获取地址(需要联网，调用淘宝的IP库)
     *
     * @param ip
     * @return
     */
//    public static String getIpInfo(String ip) {
//        if ("127.0.0.1".equals(ip)) {
//            ip = "127.0.0.1";
//        }
//        String info = "";
//        try {
//            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
//            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
//            htpcon.setRequestMethod("GET");
//            htpcon.setDoOutput(true);
//            htpcon.setDoInput(true);
//            htpcon.setUseCaches(false);
//
//            InputStream in = htpcon.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer temp = new StringBuffer();
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                temp.append(line).append("\r\n");
//                line = bufferedReader.readLine();
//            }
//            bufferedReader.close();
//            JSONObject obj = (JSONObject) JSONObject.wrap(temp.toString());
//            if (obj.getInt("code") == 0) {
//                JSONObject data = obj.getJSONObject("data");
//                info += data.getString("country") + " ";
//                info += data.getString("region") + " ";
//                info += data.getString("city") + " ";
//                info += data.getString("isp");
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return info;
//    }

    public static String getIpInfo(String ip) throws IOException, GeoIp2Exception
    {
        // 创建 GeoLite2 数据库
        File database=new File("E:\\java idea\\mesBoard\\src\\main\\resources\\GeoLite2-City.mmdb");
        // 读取数据库内容
        DatabaseReader reader=new DatabaseReader.Builder(database).build();
        InetAddress ipAddress=InetAddress.getByName(ip);

        // 获取查询结果
        try
        {
            CityResponse response=reader.city(ipAddress);

            // 获取国家信息
            Country country=response.getCountry();
//            System.out.println(country.getIsoCode());               // 'CN'
//            System.out.println(country.getName());                  // 'China'
//            System.out.println(country.getNames().get("zh-CN"));    // '中国'

            // 获取省份
            Subdivision subdivision=response.getMostSpecificSubdivision();
//            System.out.println(subdivision.getName());   // 'Guangxi Zhuangzu Zizhiqu'
//            System.out.println(subdivision.getIsoCode()); // '45'
//            System.out.println(subdivision.getNames().get("zh-CN")); // '广西壮族自治区'
//
//            // 获取城市
//            City city=response.getCity();
//            System.out.println(city.getName()); // 'Nanning'
//            Postal postal = response.getPostal();
//            System.out.println(postal.getCode()); // 'null'
//            System.out.println(city.getNames().get("zh-CN")); // '南宁'
//            Location location = response.getLocation();
//            System.out.println(location.getLatitude());  // 22.8167
//            System.out.println(location.getLongitude()); // 108.3167

            return subdivision.getNames().get("zh-CN");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
}