<%@ page import="com.studentSys.domin.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/26
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>学生信息</title>

  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
  <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
  <![endif]-->

  <style>
    table{text-align: center;margin: auto}
    tr{height: 40px;border-bottom: darkgray 3px}
    th{width: 20%;text-align: center;font-size: 25px}
    td{width: 20%;text-align: center;font-size: 25px}
      img{height: 50px}
  </style>
</head>
<body>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<% String [] arr = {"active","success","warning","danger","info"};
    ArrayList<Student> listStu = (ArrayList<Student>)session.getAttribute("listStu");
    pageContext.setAttribute("lis", listStu);
    String name = (String) session.getAttribute("name");
    Date date = (Date)session.getAttribute("date");
%>

<div style="height: 200px"></div>
<c:if test="${sessionScope.name == null}" var="result">
    <span style="text-align: center;font-size: 30px">
        <a href="login.html">请登录....</a>
    </span>
</c:if>
<c:if test="${sessionScope.name != null}" var="result">
欢迎你<%=name%>,登录时间：<%=date%>
<form action="/manageServlet" method="post" enctype="multipart/form-data">
  <table>
    <tr>
      <td> <input id="id" type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" name="id"></td>
      <td> <input id="name" type="text" class="form-control" placeholder="姓名" aria-describedby="basic-addon1" name="name"></td>
      <td> <input id="sex" type="text" class="form-control" placeholder="性别" aria-describedby="basic-addon1" name="sex"></td>
      <td> <input id="score" type="text" class="form-control" placeholder="成绩" aria-describedby="basic-addon1" name="score"></td>
      <td> <input id="file" type="file" class="form-control" placeholder="头像" aria-describedby="basic-addon1" name="file"></td>
      <td> <button type="submit" class="btn btn-primary" onclick="add()">添加学生</button></td>
    </tr>
  </table>
</form>

<table class="table table-hover" id="tableid">
  <tr >
    <th>ID</th>
    <th>姓名</th>
    <th>性别</th>
    <th>成绩</th>
    <th>头像</th>
    <form>
      <td><input type="text" id="query" class="form-control" placeholder="ID" aria-describedby="basic-addon1"></td>
      <td><button type="button" class="btn btn-primary" onclick="GetInfoFromTable()">查询</button></td>
    </form>
  </tr>
    <%  String color1 = "";
        for (int i = 0; i < listStu.size(); i++) {
            if (i%2 == 0){
                color1 = "info";
            }else {
                color1 = "";
            }
    %>
            <tr class="<%=color1%>">
                <td><%=listStu.get(i).getId()%></td>
                <td id="name+<%=i%>"><%=listStu.get(i).getName()%></td>
                <td id="sex+<%=i%>"><%=listStu.get(i).getSex()%></td>
                <td id="score+<%=i%>"><%=listStu.get(i).getScore()%></td>
                <td id="img+<%=i%>"><img src="/studentImg1/<%=listStu.get(i).getFileName()%>" alt=""></td>
                <td><button type="button" class="btn btn-primary" value="<%=i%>" onclick="updateRow(this);">修改</button></td>

                <form action="/removeServlet">
                    <input type="hidden" name="remove" value="<%=i%>">
                <td><button type="submit" class="btn btn-primary">删除</button></td>
                </form>
            </tr>
       <% }%>

    <%--<c:forEach begin="0" var="lis" items="${lis}" varStatus="vs">--%>
        <%--<c:choose>--%>
            <%--<c:when test="${vs.index%2 == 0}">--%>
                <%--<%color1 = "info"%>--%>
            <%--</c:when>--%>
            <%--<c:when test="${vs.index%2 != 0}">--%>
                <%--<%color1 = "";%>--%>
            <%--</c:when>--%>
        <%--</c:choose>--%>
        <%--<tr class="<%=color1%>">--%>
            <%--<td>${lis.id}</td>--%>
            <%--<td>${lis.name}</td>--%>
            <%--<td>${lis.sex}</td>--%>
            <%--<td>${lis.score}</td>--%>
            <%--<td><img src="/studentImg1/${lis.fileName}" alt=""></td>--%>
            <%--<td><button type="button" class="btn btn-primary" value="${vs.index}" onclick="updateRow(this);">修改</button></td>--%>

            <%--<form action="/removeServlet">--%>
                <%--<input type="hidden" name="remove" value="${vs.index}">--%>
                <%--<td><button type="submit" class="btn btn-primary">删除</button></td>--%>
            <%--</form>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
</table>
<button style="margin-right: 30px" type="button" onclick="zhuxiao()">注销<button>
</c:if>
</body>
<script>
    //query
    function GetInfoFromTable() {
        //获取查询内容
        var query = document.getElementById("query");
        var tableInfo = "";
        var tableObj = document.getElementById("tableid");
        for (var i = 1; i < tableObj.rows.length; i++) {    //遍历Table的所有Row
            tableInfo = tableObj.rows[i].cells[0].innerText;   //获取Table中单元格的内容
            if (tableInfo == query.value){
                alert("id="+tableObj.rows[i].cells[0].innerText+"name="+tableObj.rows[i].cells[1].innerText+"sex="+tableObj.rows[i].cells[2].innerText+"score="+tableObj.rows[i].cells[0].innerText);
            }
        }
    }
    //update
    function updateRow(btn) {
        var tr = btn.parentNode.parentNode;
        var cell1 = tr.cells[1];//name
        var cell2 = tr.cells[2];//sex
        var cell3 = tr.cells[3];//score
        //值
        var name = cell1.innerText;
        var sex = cell2.innerText;
        var score = cell3.innerText;
        var index = btn.value;
        if (btn.innerHTML == "修改") {
            cell1.innerHTML = "<input type='text' value='" + cell1.innerHTML + "' id='inp1' onblur='updatePow1(this)'/>"
            cell2.innerHTML = "<input type='text' value='" + cell2.innerHTML + "' id='inp2' onblur='updatePow2(this)'/>"
            cell3.innerHTML = "<input type='text' value='" + cell3.innerHTML + "' id='inp3' onblur='updatePow3(this)'/>"
            btn.innerHTML = "确认"
        } else {
            var inp1 = document.getElementById("inp1");
            cell1.innerHTML = inp1.value;
            var inp2 = document.getElementById("inp2");
            cell2.innerHTML = inp2.value;
            var inp3 = document.getElementById("inp3");
            cell3.innerHTML = inp3.value;
            btn.innerHTML = "修改"
            location.href = "/updateServlet?name=" + cell1.innerHTML + "&sex=" + cell2.innerHTML + "&score=" + cell3.innerHTML + "&index=" + index
        }
    }
</script>
</html>