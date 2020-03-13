package com.ssp.share.listener;


import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



@WebListener
public class OnlineNumberListener implements HttpSessionListener {


    /*
    用户登录
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        Integer online_number = (Integer) application.getAttribute("online_number");
        if (null == online_number)
            online_number = 0;
        application.setAttribute("online_number", online_number);
        System.out.println("新增一位在线用户");

    }

    /*
    用户下线
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        Integer online_number = (Integer) application.getAttribute("online_number");
        if(null==online_number){
            online_number = 0;
        }
        else
            online_number--;
        application.setAttribute("online_number", online_number);
        System.out.println("一位用户离线");
    }
}
