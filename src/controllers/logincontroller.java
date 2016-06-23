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
			e1.printStackTrace();
		}


		if (login.isComplete() && dao != null && !err) {
			HttpSession session = request.getSession();

			try {
				String query = "select id from users where user=\"" + login.getUser()
						+ "\" and passwd=\"" + login.getPassword() + "\";";
				ResultSet result = dao.executeSQL(query);


				String id = "";
				while (result.next()) {
					id = result.getString("id");

				}

				session.setAttribute("user", login.getUser());
				session.setAttribute("user_id", id);
				session.setAttribute("date", new Date());
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
