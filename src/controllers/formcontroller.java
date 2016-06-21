package controllers;

import java.io.IOException;
import models.BeanUser;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import utils.BeanUtilities;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAO;

/**
 * Servlet implementation class formcontroller
 */
public class formcontroller extends HttpServlet {
	private String taula = "users";
	private DAO dao = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcontroller() {
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

				ResultSet result = dao.readDataFromTable("users");
				String resultHtml = "<html><head></head><body><table>" + "<thead><tr>" + "<th>id</th>" + "<th>user</th>"
						+ "<th>mail</th>" + "<th>name</th>" + "<th>surnmae</th>" + "<th>passwd</th>" + "<th>bday</th>"
						+ "<th>surname2</th>" + "<th>gender</th>" + "<th>description</th>" + "<th>likes</th>"
						+ "<th>date_upd</th>" + "<th>date_add</th>" + "</tr></thead><tbody>";

				while (result.next()) {
					ResultSetMetaData rsmd = result.getMetaData();
					resultHtml += "<tr>";

					for (int i = 1; rsmd.getColumnCount() >= i; i++) {
						resultHtml += "<td>" + result.getString(i) + "</td>";
					}

					resultHtml += "</tr>";
				}

				resultHtml += "</tbody></table></body><html>";

				request.setAttribute("result", resultHtml);
				RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
				if (dispatcher != null)
					dispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
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
