package cn.yorick.serialization;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldTest extends HttpServlet{
	public void doTest(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String msg = "µÇÂ½³É¹¦";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>JSP 2.0 Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(msg);
		out.println("</body>");
		out.println("</html>");
		
	}
}
