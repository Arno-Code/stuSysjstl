package com.studentSys;

import com.studentSys.domin.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "RemoveServlet",value = "/removeServlet")
public class RemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String removeStu = request.getParameter("remove");
        //获取删除学生下标
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
        //删除
        listStu.remove(index);
        //存入文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file1));
        oos.writeObject(listStu);
        oos.flush();
        oos.close();
        //返回前端
        try {
            show(request,response );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //定时刷新
        response.setHeader("refresh", "0;index.jsp");

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
