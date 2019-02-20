package com.ts.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 测试web项目
 * @author: Owen Jia
 * @time: 2018/11/26 13:27
 */
@WebServlet(name = "DemoServlet",urlPatterns = "demo",loadOnStartup = 1)
public class DemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("i am DemoServlet,i do test connect.");
        resp.flushBuffer();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
