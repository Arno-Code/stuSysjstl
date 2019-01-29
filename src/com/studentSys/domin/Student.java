package com.studentSys.domin;

import java.io.Serializable;
import java.util.Date;

/**
 * Create Time: 2019年01月26日 21:10
 * Create Author: 郑发
 **/
public class Student implements Serializable {
    private String id;
    private String pswd;
    private String name;
    private String sex;
    private String score;
    private String fileName;
    public Student() {
    }

    public Student(String id, String name, String sex, String score, String fileName) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.score = score;
        this.fileName = fileName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", score='" + score + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
