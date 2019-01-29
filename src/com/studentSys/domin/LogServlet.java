package com.studentSys.domin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "LogServlet",value = "/logServlet")
public class LogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取修改值
        String name = request.getParameter("id");
        String pswd = request.getParameter("pswd");

        //检查数据
        System.out.println("name=" + name);
        System.out.println("pswd=" + pswd);
        //获取学生集合
        File file1 = new File("E:\\xmcc_code\\xmccSecond\\stuSysjstl\\src\\com\\studentSys\\database\\student\\student.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
        ArrayList<Student> listStu = null;
        try {
            listStu = (ArrayList<Student>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        boolean flag = true;
        //比较
        for (Student lis:listStu
             ) {
            System.out.println("id="+lis.getId());
            System.out.println("pswd="+lis.getPswd());
            if (name.equals(lis.getId())&&pswd.equals(lis.getPswd())){
                request.getSession().setAttribute("name", lis.getName());
                request.getSession().setAttribute("date", new Date());
                try {
                    show(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                response.setHeader("refresh", "0;index.jsp");
                flag = false;
            }
        }

        if (flag){
            System.out.println("密码错误");
                request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void show(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        File file1 = new File("E:\\xmcc_code\\xmccSecond\\stuSysjstl\\src\\com\\studentSys\\database\\student\\student.txt");
        System.out.println(file1.length());
        if (file1.length() == 0){
            //不展示
        }else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
            ArrayList<Student> listStu =(ArrayList<Student>) ois.readObject();
            ois.close();
            request.getSession().setAttribute("listStu", listStu);
        }
    }
}




