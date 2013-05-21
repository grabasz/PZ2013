package programowanie.zespolowe.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GUIEngine extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private class Sample {

        Long time;
        Double value;

        public Sample(Long time, Double value) {
            this.time = time;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + time + " , " + value + "]";
        }
    }
    private ArrayList<Sample> data = new ArrayList<Sample>();
    private Random rn = new Random();
    Long tim = new Date().getTime();

    public GUIEngine() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long tmp = new Date().getTime();
        if (tmp > tim + 2000) {
            tim = tmp;
            data.add(new Sample(new Date().getTime(), rn.nextDouble()));
            if (data.size() > 10) {
                data.remove(0);
            }
        }
        response.setContentType("text/javascript;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GUIEngine</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GUIEngine at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }//}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(dataToJson());
        } finally {
            out.close();
        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public String dataToJson() {
        StringBuilder sb = new StringBuilder("[");
        if (!data.isEmpty()) {
            for (Sample s : data) {
                sb.append(s.toString()).append(",\n");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        sb.append("]");

        return sb.toString();
    }
}
