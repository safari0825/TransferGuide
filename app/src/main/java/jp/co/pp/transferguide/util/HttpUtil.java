package jp.co.pp.transferguide.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liujin on 2016/2/21.
 */
public class HttpUtil {

    private static final String DEBUG_TAG = "Debug" ;

    /**
     * 从指定的URL中获取数组
     * @param urlPath
     * @return
     * @throws Exception
     */
    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
    }


    public void dopost(String val){
        //封装数据
        Map<String, String> parmas = new HashMap<String, String>();
        parmas.put("name", val);

        URL url = null;
        try {
            //构造一个URL对象
            url = new URL(val);
        }
        catch (MalformedURLException e){
            Log.e(DEBUG_TAG, "MalformedURLException");
        }
        //HttpURLConnection con = (HttpURLConnection)reqUrl.openConnection(); //http客户端
        String httpUrl = "http://192.168.1.110:8080/httpget.jsp";
        //获得的数据
        String resultData = "";


        if (url != null) {
            try {
                // 使用HttpURLConnection打开连接
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                //因为这个是post请求,设立需要设置为true
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);
                // 设置以POST方式
                urlConn.setRequestMethod("POST");
                // Post 请求不能使用缓存
                urlConn.setUseCaches(false);
                urlConn.setInstanceFollowRedirects(true);
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
                // 要注意的是connection.getOutputStream会隐含的进行connect。
                urlConn.connect();
                //DataOutputStream流
                DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
                //要上传的参数
                String content = "par=" + URLEncoder.encode("ABCDEFG", "gb2312");
                //将要上传的内容写入流中
                out.writeBytes(content);
                //刷新、关闭
                out.flush();
                out.close();

                //返回响应结果
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = br.readLine();
                }
            }catch(Exception e){}
        }
    }
}
