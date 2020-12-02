package servlet;

import DBUtil.Db;
import entity.Game;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("gbk");
        List<Game> lists = new ArrayList<>();
        String num = req.getParameter("usetime");
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            if (num == null) {
                String sql = "select * from game";
                 preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Game game = new Game();
                        game.setId(resultSet.getInt("id"));
                        game.setName(resultSet.getString("name"));
                        game.setUsetime(Integer.valueOf(resultSet.getString("use_time")));
                        game.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(game);
                    }
                }
            } else {
                String sql = "select * from game where use_time > ?";
                preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    preparedStatement.setObject(1,num);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Game game = new Game();
                        game.setId(resultSet.getInt("id"));
                        game.setName(resultSet.getString("name"));
                        game.setUsetime(Integer.valueOf(resultSet.getString("use_time")));
                        game.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(game);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("games", lists);
        resp.sendRedirect("/game.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
