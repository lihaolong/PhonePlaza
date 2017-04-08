package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/PhoneQueryServlet")
public class PhoneQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PhoneQueryServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info1 = request.getParameter("info1");
		String info2 = request.getParameter("info2");
		System.out.println(info1+info2);
		response.getWriter().write("mate9");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
