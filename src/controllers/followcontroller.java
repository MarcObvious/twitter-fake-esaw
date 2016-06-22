package controllers;

import DAO.DAO;
import models.BeanFollow;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class formcontroller
 */
public class followcontroller extends HttpServlet {
    private String taula = "users";
    private DAO dao = null;

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public followcontroller() {
        super();
        try {
            dao = new DAO();
        } catch (Exception e1) {
            e1.printStackTrace();// http://localhost:8080/Practica3/form.jsp
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String method = request.getParameter("method");

        String query = "";
        if (method.equals("unfollow")) {
            query = "UPDATE followings\n" +
                    "SET deleted=1\n" +
                    "WHERE id_user=\""+ session.getAttribute("user_id") + "\" AND id_followed=\"" + request.getParameter("id_followed")+"\";";
        }

        if (method.equals("follow")) {
            query = "INSERT INTO followings (id_user, id_followed) " +
                    "VALUES("+session.getAttribute("user_id")+","+request.getParameter("id_followed")+") " +
                    "ON DUPLICATE KEY UPDATE deleted=0";
        }
        if (!query.equals("")){
            method = "following";
            try {
                dao = new DAO();
                dao.executeUpdate(query);
                dao.disconnectBD();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (method.equals("following")) {
            query = "SELECT u.user, u.id AS id_user, f.id_followed, f.date_add AS since\n" +
                    "FROM followings f\n" +
                    "INNER JOIN users u ON f.id_followed=u.id " +
                    "where f.deleted=0 AND f.id_user=\"" + (String) session.getAttribute("user_id") + "\";";
        }
        else if (method.equals("nofollowing"))
            query = "SELECT u.id AS id_user, 0 as id_followed, u.user, u.date_add AS since\n" +
                    "FROM users u;";

        if (!query.equals("")){
            try {
                System.out.println(query);
                dao = new DAO();
                ResultSet result = dao.executeSQL(query);

                result.first();

                ArrayList<BeanFollow> list = new ArrayList<BeanFollow>();

                while (result.next()) {
                    BeanFollow b = new BeanFollow();

                    b.setIdfollowed(result.getInt("id_followed"));
                    b.setIduser(result.getInt("id_user"));
                    b.setName(result.getString("user"));
                    b.setSince(result.getString("since"));
                    list.add(b);
                    request.setAttribute(method, list);
                }
                dao.disconnectBD();
                System.out.println(query);



            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RequestDispatcher dispatcher;
        if (method.equals("following")) {
            dispatcher = request.getRequestDispatcher("/following.jsp");
        }
        else {
            dispatcher = request.getRequestDispatcher("/nofollowing.jsp");
        }

        if (dispatcher != null)
            dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
