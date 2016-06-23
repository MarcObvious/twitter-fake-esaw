package controllers;

import java.io.IOException;
import models.BeanUser;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import utils.BeanUtilities;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;

/**
 * Servlet implementation class registercontroller
 */
public class registercontroller extends HttpServlet {
	private String taula = "users";
	private DAO dao = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registercontroller() {
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
		BeanUtilities.populateBean(user, request);

		boolean err = false;
		try {
			String[] where = { "user", user.getUser() };
			if (dao.exists("users", where)) {
				user.updateError(0, 1);
				err = true;
			}

			String[] where2 = { "mail", user.getMail() };
			if (dao.exists("users", where2)) {
				user.updateError(1, 1);
				err = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user.isComplete() && dao != null && !err) {

			System.out.println("Fem un insert a la BD");
			try {
				String sSql = "INSERT INTO " + taula
						+ " (`user`, `mail`, `name`, `surname`, `passwd`, `bday`, `surname2`, `gender`, `description`, `likes`) "
						+ "VALUES (" + "\"" + user.getUser() + "\"," + "\"" + user.getMail() + "\"," + "\""
						+ user.getName() + "\"," + "\"" + user.getSurname() + "\"," + "\"" + user.getPasswd() + "\","
						+ "\"" + user.getBday() + "\"," + "\"" + user.getSurname2() + "\"," + user.getGender() + ","
						+ "\"" + user.getDescription() + "\"," + "\"" + user.getLikes() + "\"" + ");";
				System.out.println(sSql);
				dao.executeUpdate(sSql);

				sSql = "SELECT id, is_admin, user FROM " + taula + " where mail=\"" +user.getMail()+"\" limit 1;";
				ResultSet result = dao.executeSQL(sSql);

				HttpSession session = request.getSession();

				while (result.next()) {
					session.setAttribute("is_admin", result.getString("is_admin"));
					session.setAttribute("user", result.getString("user"));
					session.setAttribute("user_id", result.getString("id"));
					session.setAttribute("date", new Date());
				}

				//Un usuari es segueix a si mateix
				sSql = "INSERT INTO followings "
						+ " (`id_user`, `id_followed`) "
						+ "VALUES (" + "\"" + session.getAttribute("user_id") + "\"," + "\"" + session.getAttribute("user_id") + "\");";

				System.out.println(sSql);
				dao.executeUpdate(sSql);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				if (dispatcher != null)
					dispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
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
		System.out.print("post fet");
		doGet(request, response);
	}

}
