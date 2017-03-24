package de.sisao.simpleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String param1 = getServletContext().getInitParameter("param1");

		try {
			Context initialContext = new InitialContext();
			Context environmentContext = (Context) initialContext.lookup("java:/comp/env");

			String bar = (String) environmentContext.lookup("de.sisao.simpleservlet.bar");
			String foo = (String) environmentContext.lookup("de.sisao.simpleservlet.foo");

			ServletContext cntxt = getServletContext();
			String fName = "/WEB-INF/data/info.txt";
			InputStream inputStream = cntxt.getResourceAsStream(fName);
			String theString = IOUtils.toString(inputStream, "UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello Servlet Get</h1>");
			out.println("<p>param1: " + param1 + "</p>");
			out.println("<p>foo: " + foo + "</p>");
			out.println("<p>bar: " + bar + "</p>");
			out.println("<p>info.txt: " + theString + "</p>");
			out.println("</body>");
			out.println("</html>");
		} catch (NamingException nex) {
			System.out.println(nex.getMessage());
		}
	}
}