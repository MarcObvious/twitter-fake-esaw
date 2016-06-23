package controllers;

import DAO.DAO;
import models.BeanTweet;
import utils.BeanUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class registercontroller
 */
public class tweetcontroller extends HttpServlet {
	private String taula = "tweets";
	private DAO dao = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public tweetcontroller() {
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

		BeanTweet tweet = new BeanTweet();
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(false);

		String method = request.getParameter("method");

		//Fem retweet
		if (method != null && method.equals("retweet")) {
			try {

				String query = "SELECT t.id as id_tweet, t.title, t.tweet, t.private\n" +
						"FROM "+ taula + " t\n" +
						"where t.id=\"" + request.getParameter("id_tweet") + "\";";

				ResultSet result = dao.executeSQL(query);

				while (result.next()) {
					tweet.setIdtweet(result.getInt("id_tweet"));
					tweet.setTitle(result.getString("title"));
					tweet.setTweet("RETWEETED from <strong>" + request.getParameter("username") +":</strong> "+ result.getString("tweet"));
					tweet.setPrivacity(result.getInt("private"));
				}

				query = "INSERT INTO " + taula +
						" (`tweet`, `title`, `id_user`, `private`) "+
						"VALUES (" +
						"\"" + tweet.getTweet() + "\"," +
						"\"" + tweet.getTitle() + "\"," +
						"\"" + (String) session.getAttribute("user_id") + "\"," +
						"\"" + tweet.getPrivacity() + "\"" + ");";

				dao.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			dispatcher = request.getRequestDispatcher("/index.jsp");

		}
		//Editem tweet
		else if (method != null && method.equals("edittweet")) {
			String query = "SELECT t.id as id_tweet, t.title, t.tweet, t.private\n" +
					"FROM "+ taula + " t\n" +
					"where t.id=\"" + request.getParameter("id_tweet") + "\";";

			try {
				ResultSet result = dao.executeSQL(query);
				while (result.next()) {
					tweet.setIdtweet(result.getInt("id_tweet"));
					tweet.setTitle(result.getString("title"));
					tweet.setTweet(result.getString("tweet"));
					tweet.setPrivacity(result.getInt("private"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("tweet",tweet);
			dispatcher = request.getRequestDispatcher("/tweet.jsp");
		}
		else {

			BeanUtilities.populateBean(tweet, request);
			String query = "";
			if (tweet.getIdtweet() == 0) {
				query = "INSERT INTO " + taula +
						" (`tweet`, `title`, `id_user`, `private`) "+
						"VALUES (" +
						"\"" + tweet.getTweet() + "\"," +
						"\"" + tweet.getTitle() + "\"," +
						"\"" + (String) session.getAttribute("user_id") + "\"," +
						"\"" + tweet.getPrivacity() + "\"" + ");";
			}
			else {
				query = "UPDATE "+ taula +"\n" +
						"SET tweet=\"" + tweet.getTweet()  + "\",\n" +
						"title=\"" + tweet.getTitle() + "\",\n" +
						"id_user=\"" + (String) session.getAttribute("user_id") + "\",\n" +
						"private=\"" + tweet.getPrivacity()  + "\"\n" +
						"WHERE id="+  tweet.getIdtweet() +";";

			}
			if ((String) session.getAttribute("user") != null && tweet.isComplete() && dao != null) {

				try {
					dao.executeUpdate(query);
					System.out.println("twwet done");
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			dispatcher = request.getRequestDispatcher("/index.jsp");
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
		doGet(request, response);
	}

}
