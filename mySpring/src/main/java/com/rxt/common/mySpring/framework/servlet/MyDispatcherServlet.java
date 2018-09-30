/**
 * FileName: MyDispatcherServlet
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:16
 */

package com.rxt.common.mySpring.framework.servlet;

import com.rxt.common.mySpring.framework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDispatcherServlet extends HttpServlet {

    private List<String> classNames = new ArrayList<String>();    //扫描所有类，获得列表

    private Map<String, Object> instanceMapping = new HashMap<String, Object>();    //<类名，对象>键值对

    private List<Handler> handlerMapping = new ArrayList<Handler>();

    public MyDispatcherServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        long start = System.currentTimeMillis();    //MySpring启动初始化时间

        //1、读取配置文件，获取扫描那个包下的类
        String scanPackage = config.getInitParameter("scanPackage");

        //2、扫描指定包路径下的类
        scanClass(scanPackage);

        //3、把这些被扫描出来的类进行实例化(BeanFactroy)
        instance();

        //4、建立依赖关系，自动依赖注入
        autowired();

        //5、建立URL和Method的映射关系(HandlerMapping)
        handlerMapping();

        long end = System.currentTimeMillis();    //初始化完成

        System.out.println("My Spring MVC Framework startup，Time consuming " + (end - start));
        System.out.println("instanceMapping" + instanceMapping);
        System.out.println("handlerMapping" + handlerMapping);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //从上面已经初始化的信息中匹配
            //拿着用户请求url去找到其对应的Method
            boolean isMatcher = pattern(req, resp);
            if (!isMatcher) {
                resp.getWriter().write("404 Not Found");
            }
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" +
                    e.getMessage() + "\r\n" +
                    Arrays.toString(e.getStackTrace()).replaceAll("\\[\\]", "")
                            .replaceAll(",\\s", "\r\n"));
        }

    }

    /**
     * 扫描指定包路径下的类
     *
     * @param packageName 包名
     */
    private void scanClass(String packageName) {

        //拿到包路径，转换为文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        System.out.println(packageName + " ==> " + url);
        File dir = new File(url.getFile());

        //递归查找到所有的class文件
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            } else {
                //去掉文件后缀.class
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    /**
     * 将扫描到的类名全部实例化
     */
    private void instance() {

        //利用反射机制将扫描到的类名全部实例化
        if (classNames.size() == 0) {
            return;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);

                //找那些加了@MyController、@MyService
                if (clazz.isAnnotationPresent(MyController.class)) {

                    String beanName = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(beanName, clazz.newInstance());

                } else if (clazz.isAnnotationPresent(MyService.class)) {

                    MyService service = clazz.getAnnotation(MyService.class);
                    String beanName = service.value();
                    if (!"".equals(beanName.trim())) {
                        instanceMapping.put(beanName, clazz.newInstance());
                        continue;
                    }
                    //如果自己没有起名字
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        instanceMapping.put(i.getName(), clazz.newInstance());
                    }
                } else {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
        }

    }

    /**
     * 建立依赖关系，自动依赖注入
     */
    private void autowired() {
        if (instanceMapping.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }

                MyAutowired autowired = field.getAnnotation(MyAutowired.class);

                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true); //如果是私有属性，设置访问权限为可访问

                try {
                    field.set(entry.getValue(), instanceMapping.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 建立URL和方法的对应关系
     */
    public void handlerMapping() {

        if (instanceMapping.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {

            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }

            String url = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requstMapping = clazz.getAnnotation(MyRequestMapping.class);
                url = requstMapping.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {

                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }

                MyRequestMapping requstMapping = method.getAnnotation(MyRequestMapping.class);
                String customRegex = ("/" + url + requstMapping.value()).replaceAll("/+", "/");
                String regex = customRegex.replaceAll("\\*", ".*");

                Map<String, Integer> pm = new HashMap<String, Integer>();

                Annotation[][] pa = method.getParameterAnnotations();
                for (int i = 0; i < pa.length; i++) {
                    for (Annotation a : pa[i]) {
                        if (a instanceof MyRequestParam) {
                            String paramName = ((MyRequestParam) a).value();
                            if (!"".equals(paramName.trim())) {
                                pm.put(paramName, i);
                            }
                        }
                    }
                }


                //提取Request和Response的索引
                Class<?>[] paramsTypes = method.getParameterTypes();
                for (int i = 0; i < paramsTypes.length; i++) {
                    Class<?> type = paramsTypes[i];
                    if (type == HttpServletRequest.class ||
                            type == HttpServletResponse.class) {
                        pm.put(type.getName(), i);
                    }
                }

                handlerMapping.add(new Handler(Pattern.compile(regex), entry.getValue(), method, pm));

                System.out.println("Mapping " + customRegex + "  " + method);
            }
        }

    }


    public boolean pattern(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //throw new Exception("这是一个假象，是我自己定义异常，弄着玩的");
        if (handlerMapping.isEmpty()) {
            return false;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");


        for (Handler handler : handlerMapping) {
            try {
                Matcher matcher = handler.pattern.matcher(url);

                if (!matcher.matches()) {
                    continue;
                }

                Class<?>[] paramTypes = handler.method.getParameterTypes();

                Object[] paramValues = new Object[paramTypes.length];

                Map<String, String[]> params = req.getParameterMap();

                for (Map.Entry<String, String[]> param : params.entrySet()) {

                    String value = Arrays.toString(param.getValue()).replaceAll("\\]|\\[", "").replaceAll(",\\s", ",");

                    if (!handler.paramMapping.containsKey(param.getKey())) {
                        continue;
                    }

                    int index = handler.paramMapping.get(param.getKey());

                    //涉及到类型转换
                    paramValues[index] = castStringValue(value, paramTypes[index]);
                }


                //
                int reqIndex = handler.paramMapping.get(HttpServletRequest.class.getName());
                paramValues[reqIndex] = req;

                int repIndex = handler.paramMapping.get(HttpServletResponse.class.getName());
                paramValues[repIndex] = resp;

                //需要对象.方法
                handler.method.invoke(handler.controller, paramValues);

                return true;
            } catch (Exception e) {
                throw e;
            }
        }
        return false;
    }

    /**
     * 转为String类型
     *
     * @param value
     * @param clazz
     * @return
     */
    private Object castStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }

    /**
     * 首字母转小写
     *
     * @param str
     * @return
     */
    public String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


    private class Handler {
        protected Pattern pattern;
        protected Object controller;
        protected Method method;
        protected Map<String, Integer> paramMapping;

        protected Handler(Pattern pattern, Object controller, Method method, Map<String, Integer> paramMapping) {
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;
            this.paramMapping = paramMapping;
        }
    }

}
