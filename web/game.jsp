
<%@ page import="java.util.List" %>
<%@ page import="entity.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>游戏列表</title>
</head>
<body>
<div style="height:500px; width:500px; margin:0 auto;text-align: center;border: 2px solid; border-radius: 10px;background-color: bisque;">
    <h1 align="center">游戏列表:</h1><br>
    <form action="/game" method="get">
        筛选大于该使用时间的游戏：
        <input type="number" name="usetime">&nbsp;<input type="submit" value="搜索">
        <%List<Game> games = (List<Game>) session.getAttribute("games");%>
        <table style="margin-top: 80px;margin-left: 130px;border: 2px solid;">
            <th>游戏名称：</th>
            <th>游戏时间：</th>
            <th>游戏价格：</th>
            <%
                for (Game game : games) {
            %>
            <tr>
                <td><%=game.getName()%>
                </td>
                <td><%=game.getUsetime()%>h
                </td>
                <td>$<%=game.getPrice()%>.00
                </td>
                <%}%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
