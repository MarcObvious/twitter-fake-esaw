package controllers;

import DAO.DAO;
import models.BeanNewtweet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Content
 */
public class timelinecontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAO dao = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public timelinecontroller() {
        super();
        try {
            dao = new DAO();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        System.out.print("Hem entrat a timeline controller!\n");
        String query = "";

        HttpSession session = request.getSession(false);

        String method = request.getParameter("method");

        if (method != null && method.equals("deletetweet")) {
            query = "UPDATE tweets\n" +
                    "SET deleted=1\n" +
                    "WHERE  id=\"" + request.getParameter("id_tweet")+"\";";
            try {

                dao.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if ((String) session.getAttribute("user") == null) {

            System.out.print("Usuari no registrat!\n");

            query = "select t.id as id_tweet, t.id_user, u.user, t.tweet, t.title, t.date_add as date_tweet  \n" +
                    "from tweets t  \n" +
                    "inner join users u on t.id_user=u.id\n" +
                    "where t.deleted=0 AND t.private=0\n" +
                    "order by t.date_add DESC;";
        }
        else {
            System.out.print("Usuari registrat: + " + (String) session.getAttribute("user") + "," + (String) session.getAttribute("user_id")  +"\n");

            query = "select t.id as id_tweet, t.id_user, u.user, t.tweet, t.title, t.date_add as date_tweet  \n" +
                    "from tweets t  \n" +
                    "inner join followings f on t.id_user=f.id_followed\n" +
                    "inner join users u on t.id_user=u.id\n" +
                    "where t.deleted=0 AND f.id_user=" + (String) session.getAttribute("user_id") + "\n" +
                    "order by t.date_add DESC;";
        }

        try {
            ResultSet result = dao.executeSQL(query);

            ArrayList<BeanNewtweet> list = new ArrayList<BeanNewtweet>();

            while (result.next()) {
                BeanNewtweet b = new BeanNewtweet();

                b.setUser(result.getString("user"));
                b.setIduser(result.getInt("id_user"));
                b.setIdtweet(result.getInt("id_tweet"));
                b.setTitle(result.getString("title"));
                b.setTweet(result.getString("tweet"));
                b.setDatetweet(result.getString("date_tweet"));
                list.add(b);
            }
            request.setAttribute("timeline", list);

            //request.setAttribute("timeline", result);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //    String timelineq = (String)request.getParameter("timeline");


        RequestDispatcher dispatcher = request.getRequestDispatcher("/timeline1.jsp");
        if (dispatcher != null)
            dispatcher.forward(request, response);

        // if (dispatcher != null) dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request,response);
    }

}
