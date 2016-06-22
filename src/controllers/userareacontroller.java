package controllers;

import DAO.DAO;
import models.BeanUser;
import utils.BeanUtilities;

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

/**
 * Servlet implementation class formcontroller
 */
public class userareacontroller extends HttpServlet {
	private String taula = "users";
	private DAO dao = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userareacontroller() {
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

		BeanUser user = new BeanUser();

        HttpSession session = request.getSession(false);

        if (request.getQueryString() == null) {
            System.out.println("user not completed loading from db\n");

            if ((String) session.getAttribute("user") != null && dao != null) {
                System.out.print("Usuari registrat, userarea : + " + (String) session.getAttribute("user") + "," + (String) session.getAttribute("user_id")  +"\n");
                String query = "select user, mail, name, surname, bday, surname2, gender, description, likes from users u where u.id="+(String) session.getAttribute("user_id")+";";
                try {
                    ResultSet  result = dao.executeSQL(query);

                    while (result.next()) {
                        user.setUser(result.getString("user"));
                        user.setMail(result.getString("mail"));
                        user.setName(result.getString("name"));
                        user.setSurname(result.getString("surname"));
                        user.setBday(result.getString("bday"));
                        user.setSurname2(result.getString("surname2"));
                        user.setGender(result.getInt("gender"));
                        user.setDescription(result.getString("description"));
                        user.setLikes(result.getString("likes"));
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        else {
            System.out.println("User completed, saving to db\n");
            System.out.println(request.getParameter("name") + "\n");

            user.setUser(request.getParameter("user"));
            user.setMail(request.getParameter("mail"));
            user.setName(request.getParameter("name"));
            user.setSurname(request.getParameter("surname"));
            user.setBday(request.getParameter("bday"));
            user.setSurname2(request.getParameter("surname2"));
            user.setGender(Integer.parseInt(request.getParameter("gender")));
            user.setDescription(request.getParameter("description"));
            user.setLikes(request.getParameter("likes"));

            String sSql = "UPDATE "+ taula +"\n" +
                    "SET name=\"" + request.getParameter("name") + "\",\n" +
                    "surname=\"" + request.getParameter("surname") + "\",\n" +
                    "bday=\"" + request.getParameter("bday") + "\",\n" +
                    "surname2=\"" + request.getParameter("surname2") + "\",\n" +
                    "gender=\"" + request.getParameter("gender") + "\",\n" +
                    "description=\"" + request.getParameter("description") + "\",\n" +
                    "likes=\"" + request.getParameter("likes") + "\"\n" +
                    "WHERE id="+  (String) session.getAttribute("user_id") +";";
            System.out.println(sSql);
            try {
                dao.executeUpdate(sSql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String query2 = "select u.user, f.date_add as since from followings f " +
                "inner join users u on f.id_followed=u.id " +
                "where f.id_user=\"" + (String) session.getAttribute("user_id") + "\";";

        String query3 = "select u.user, u.id as id_user from users u;";

        String query5 = "select * from users;";


        try {
            System.out.println(query2);
            ResultSet result = dao.executeSQL(query5);
            request.setAttribute("following", result);
           /* while (result.next()) {
                System.out.println(query2);
            }*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(query3);
            ResultSet result2 = dao.executeSQL(query5);
            request.setAttribute("nofollowing", result2);
           /* while (result2.next()) {
                System.out.println(query3);
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }



        request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userarea1.jsp");
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
		System.out.print("post fet\n");
		doGet(request, response);
	}

}
