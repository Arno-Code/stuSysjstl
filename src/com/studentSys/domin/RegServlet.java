package com.studentSys.domin;

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

@WebServlet(name = "RegServlet",value = "/regServlet")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            save(request, response);
            //定时刷新
            response.setHeader("refresh", "0;login.html");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void save (HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException, ClassNotFoundException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024*1024*5);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> list = upload.parseRequest(request);
        HashMap<String, String> map = new HashMap<>();
        //获取学生id+文件后缀
        String filename = "";
        for (FileItem item:list
             ) {
            if (item.isFormField()){
                String fieldName = item.getFieldName();
                if (fieldName.equals("id")){
                    filename = item.getString();
                }
            }
        }
        //遍历
        for (FileItem item:list
             ) {
            if (item.isFormField()){
                String name = item.getFieldName();
                String value = item.getString("utf-8");
                map.put(name, value);
            }
            else {
                String file = item.getName();
                int index = file.lastIndexOf(".");
                String s = file.substring(index);
                //用户名+文件后缀
                filename = filename + s;
                String filePath ="E:\\xmcc_code\\xmccSecond\\stuSysjstl\\src\\com\\studentSys\\database\\img";
                File file1 = new File(filePath);
                if (!file1.exists()){
                    file1.mkdirs();
                }
                //文件全路径名
                filePath = filePath +File.separator +filename;
                //存图片
                InputStream in = item.getInputStream();
                FileOutputStream out = new FileOutputStream(filePath);
                IOUtils.copy(in, out);
                out.close();
                in.close();
            }
        }
        //存入学生对象
        Student student = new Student();
        student.setFileName(filename);
        student.setId(map.get("id"));
        student.setPswd(map.get("pswd"));
        student.setName(map.get("name"));
        student.setSex(map.get("sex"));
        student.setScore(map.get("score"));
        //学生对象存入文件
        File file = new File("E:\\xmcc_code\\xmccSecond\\stuSysjstl\\src\\com\\studentSys\\database\\student");
        if (!file.exists()){
            file.mkdirs();
        }
        File file1 = new File("E:\\xmcc_code\\xmccSecond\\stuSysjstl\\src\\com\\studentSys\\database\\student\\student.txt");
        if (file1.length() == 0){//第一次直接存
            ArrayList<Student> listStu = new ArrayList<>();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file1));
            listStu.add(student);
            //检查是否存入
            System.out.println(listStu.get(0).getId()+listStu.get(0).getFileName());
            oos.writeObject(listStu);
            oos.close();
        }
        else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
            ArrayList<Student> listStu =(ArrayList<Student>) ois.readObject();
            ois.close();
            listStu.add(student);
            //检查
//            for (Student lis:list1
//            ) {
//                System.out.println(lis.getFileName());
//            }
            for (Student s:listStu
                 ) {
                System.out.println(s.getId()+"---"+s.getFileName());
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file1));
            oos.writeObject(listStu);
            oos.flush();
            oos.close();
        }

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
