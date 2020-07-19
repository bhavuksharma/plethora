package org.bhopal.uiApp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
public class FirstServlet extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
         		String name=req.getParameter("nm");
         		String place=req.getParameter("pl");
         		PrintWriter out=resp.getWriter();
         		out.println("<html><body bgcolor='57388B'> <h1>hello "
         				+ name+"</h1> from place: "
         						+ place+"</body></html>");
         		out.flush();
         		out.close();
	}
}
