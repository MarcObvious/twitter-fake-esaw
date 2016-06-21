package controllers;

import DAO.DAO;
import models.BeanNewtweet;
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
public class newtweetcontroller extends HttpServlet {
	private String taula = "tweets";
	private DAO dao = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public newtweetcontroller() {
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

		BeanNewtweet newtweet = new BeanNewtweet();
		BeanUtilities.populateBean(newtweet, request);

		HttpSession session = request.getSession(false);

		if ((String) session.getAttribute("user") != null && newtweet.isComplete() && dao != null) {
			String query = "INSERT INTO " + taula +
					" (`tweet`, `title`, `id_user`, `private`) "+
					"VALUES (" + "\"" + newtweet.getTweet() + "\"," + "\"" + newtweet.getTitle() + "\"," + "\"" + (String) session.getAttribute("user_id") + "\"," + "\"" + newtweet.getPrivacity() + "\"" + ");";;
			System.out.println(query);
			try {
				dao.executeUpdate(query);
				System.out.println("twwet done");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
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
		System.out.print("post fet");
		doGet(request, response);
	}

}
