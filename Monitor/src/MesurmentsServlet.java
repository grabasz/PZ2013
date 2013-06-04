

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mesurments
 */
public class MesurmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesurmentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        System.out.println(id);
        if(id==null){
	        response.setContentType("text/html");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<body>");
	        ArrayList<Integer> ids=MesurmentContainer.getIDs();
	        System.out.println(ids);
	        for(int i:ids)
	        	out.println("<a href=\"Mesurments?id="+i+"\">"+i+"</a>");
	        out.println("</body>");
	        out.println("</head>");
	        out.println("</html>");
        }else{
        	response.setContentType("application/json");
        	Mesurment m=MesurmentContainer.getById(Integer.parseInt(id));
        	out.println(m.getJSON());
        }
        
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
