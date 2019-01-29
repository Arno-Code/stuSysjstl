package com.studentSys;

import com.studentSys.domin.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "UpdateServlet",value = "/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取修改值
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String score = request.getParameter("score");
        //获取修改学生下标
        String removeStu = request.getParameter("index");
        //检查数据
        System.out.println("name=" + name);
        System.out.println("sex=" + sex);
        System.out.println("score=" + score);
        System.out.println("index=" + removeStu);
        int index = Integer.parseInt(removeStu);
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
        //修改
        Student student = listStu.get(index);
        student.setName(name);
        student.setSex(sex);
        student.setScore(score);
        //存入文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file1));
        oos.writeObject(listStu);
        oos.flush();
        oos.close();
        request.getSession().setAttribute("listStu", listStu);
        request.getRequestDispatcher("index.jsp").forward(request,response );
        try {
            show(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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




