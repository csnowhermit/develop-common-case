/**
 * FileName: Tomcat
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 13:29
 */

package com.rxt.common.myTomcat.catalina;

import jdk.internal.util.xml.impl.Input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Tomcat核心类
 */
public class Tomcat {
    private int port = 8080;    //端口号，默认8080
    private ServerSocket server;
    private Map<Pattern, Class<?>> servletMapping = new HashMap<Pattern, Class<?>>();
    private Properties webxml = new Properties();
    private String WEB_INF = this.getClass().getResource("").getPath();


    public Tomcat() { }

    public Tomcat(int port) {
        this.port = port;
    }

    /**
     * 在容器启动之前，要加载所有的配置文件
     */
    private void init() {
//        FileInputStream fis = null;
        InputStream inputStream = null;
        try {
//            System.out.println(this.getClass().getResource("").getPath());
//            fis = new FileInputStream(WEB_INF + "myTomcat.properties");
            inputStream =  this.getClass().getClassLoader().getResourceAsStream("myTomcat.properties");
            webxml.load(inputStream);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    Pattern pattern = Pattern.compile(url);

                    String className = webxml.getProperty(servletName + ".className");

                    Class<?> servletClass = Class.forName(className);

                    servletMapping.put(pattern, servletClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 等待客户端请求并处理
     *
     * @param client
     * @throws Exception
     */
    private void process(Socket client) throws Exception {
        //首先要把Request和Respose搞出来
        //Request 实际上就是对我们的InputStream的一个封装
        //Respose OutputStream的封装
        InputStream is = client.getInputStream();
        OutputStream out = client.getOutputStream();

        com.rxt.common.myTomcat.http.MyRequest request = new com.rxt.common.myTomcat.http.MyRequest(is);

        com.rxt.common.myTomcat.http.MyResponse response = new com.rxt.common.myTomcat.http.MyResponse(out);

        try {
            //此时此刻，还缺一个Serlvet
            //service(Request,Reponse)　　　　　doGet doPost

            //这个Servlet自己从来没有亲手new过？
            //通过读取web.xml 文件来获取自己的servlet
            //利用反射机制给new出来

            String url = request.getUrl();
            boolean isPattern = false;

            for (Entry<Pattern, Class<?>> entry : servletMapping.entrySet()) {
                if (entry.getKey().matcher(url).matches()) {
                    com.rxt.common.myTomcat.http.MyServlet servlet = (com.rxt.common.myTomcat.http.MyServlet) entry.getValue().newInstance();
                    servlet.service(request, response);

                    isPattern = true;
                    break;
                }
            }
            if (!isPattern) {
                response.write("404 - Not Found");
            }

        } catch (Exception e) {
            response.write("500 ," + e.getMessage());
        } finally {
            is.close();
            out.close();
            //HTTP本身是无状态的协议
            client.close();
        }

    }

    /**
     * Tomcat启动
     */
    public void start() {
        init();

        //BIO的写法,现在新的tomcat已经支持nio了
        try {
            server = new ServerSocket(this.port);
            System.out.println("MyTomcat 已启动，监听端口是：" + this.port);
        } catch (IOException e) {
            System.out.println("MyTomcat 启动失败，" + e.getMessage());
            System.exit(1);
        }

        while (true) {    //容器能够持续提供服务了
            try {
                Socket client = server.accept();

                process(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动Tomcat服务
     * @param args
     */
    public static void main(String[] args) {
        new Tomcat().start();
    }
}

