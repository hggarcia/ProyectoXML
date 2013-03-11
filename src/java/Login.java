/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author domingocortinez
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
                  File archivo = null;
                  FileReader fr = null;
                  BufferedReader br = null;
                  try {
                     List<String> user = new ArrayList<String>();
                     List<String> pass = new ArrayList<String>();
                     
                     archivo = new File ("usuarios.txt");
                     fr = new FileReader (archivo);
                     br = new BufferedReader(fr);
                     String temp = null;
                     int select = 0;
                     while((br.readLine())!=null)
                     { 
                         temp = br.readLine();
                         if(select%2==0) {
                            user.add(temp);
                         }
                         else {
                            pass.add(temp);
                         }
                         select++;
                     }
                     
                     String username = request.getParameter("username");
                     String password = request.getParameter("password");
                     
                     if (user.contains(username))
                     {
                         int ind = user.indexOf(username);
                         if (ind == pass.indexOf(password))
                         {
                             request.getRequestDispatcher( "cargarxml.jsp" ).forward( request, response ); 
                         }
                         else
                         {
                             request.getRequestDispatcher( "index.jsp" ).forward( request, response ); 
                         }
                     }
                     else
                     {
                         request.getRequestDispatcher( "index.jsp" ).forward( request, response ); 
                     }
                     
                  }
                  catch(Exception e){
                     e.printStackTrace();
                  }
            
            
            
        } finally {            
            out.close();
        }
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
    }// </editor-fold>
}
