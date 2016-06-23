package controllers;

import DAO.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class registercontroller
 */
public class admincontroller extends HttpServlet {
    private String taula = "users";
    private DAO dao = null;

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admincontroller() {
        super();
        try {
            dao = new DAO();
        } catch (Exception e1) {
            e1.printStackTrace();
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

        //Eliminem usuari, els seus tweets i les seves relacions de seguiment.
        if (method != null && method.equals("deleteuser")) {
            try {
                String sql = "update followings f set deleted=1 where f.id_user=\""+ request.getParameter("id_user") +"\" or f.id_followed=\"" + request.getParameter("id_user") +"\";";
                dao.executeUpdate(sql);

                sql = "update tweets t set t.deleted=1 where t.id_user=\""+ request.getParameter("id_user")+ "\";";
                dao.executeUpdate(sql);

                sql = "update users u set u.deleted=1 where u.id=\""+ request.getParameter("id_user")+ "\";";
                dao.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userarea.jsp");
        if (dispatcher != null)
            dispatcher.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
