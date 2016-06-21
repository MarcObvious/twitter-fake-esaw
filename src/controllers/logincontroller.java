package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;
import models.BeanLogin;
import utils.BeanUtilities;

/**
 * Servlet implementation class logincontroller
 */
public class logincontroller extends HttpServlet {
	private DAO dao = null;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logincontroller() {
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
		// TODO Auto-generated method stub
		BeanLogin login = new BeanLogin();
		BeanUtilities.populateBean(login, request);

		boolean err = false;


		try {
			String[] where = {"user", login.getUser()};
			if (!dao.exists("users", where)) {
				login.updateError(0, 1);
				err = true;
			}

			String[] where2 = {"user", login.getUser(), "passwd", login.getPassword()};
			if (!dao.exists("users", where2)) {
				login.updateError(1, 1);
				err = true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		if (login.isComplete() && dao != null && !err) {
			HttpSession session = request.getSession();
			session.setAttribute("user", login.getUser());

			try {
				ResultSet result = dao.executeSQL("select * from users where user=\"" + login.getUser()
				+ "\" and passwd=\"" + login.getPassword() + "\";");



				String resultHtml = "<html><head></head><body><table>" + "<thead><tr>" + "<th>id</th>" + "<th>user</th>"
						+ "<th>mail</th>" + "<th>name</th>" + "<th>surnmae</th>" + "<th>passwd</th>" + "<th>bday</th>"
						+ "<th>surname2</th>" + "<th>gender</th>" + "<th>description</th>" + "<th>likes</th>"
						+ "<th>date_upd</th>" + "<th>date_add</th>" + "</tr></thead><tbody>";
				String id = "";

				while (result.next()) {
					//dao.executeUpdate("INSERT INTO `ts1`.`sessions` (`session`, `user_id`, `login`) VALUES ('fafafa', " + result.getString("id") + ",\""+  new Date()+"\");");
					ResultSetMetaData rsmd = result.getMetaData();
					resultHtml += "<tr>";
					id = result.getString("id");

					for (int i = 1; rsmd.getColumnCount() >= i; i++) {
						resultHtml += "<td>" + result.getString(i) + "</td>";
					}

					resultHtml += "</tr>";
				}

				resultHtml += "</tbody></table></body><html>";
				request.setAttribute("username", login.getUser());
				request.setAttribute("user_id", id);
				request.setAttribute("date", new Date());
				request.setAttribute("result", resultHtml);

				session.setAttribute("username", login.getUser());
				session.setAttribute("user_id", id);
				session.setAttribute("date", new Date());
				session.setAttribute("result", resultHtml);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/timeline.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else {

			request.setAttribute("login", login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);

		}
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
