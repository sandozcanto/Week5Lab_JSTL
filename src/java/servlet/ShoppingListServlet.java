/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 779137
 */
public class ShoppingListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ShoppingListServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ShoppingListServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            session.invalidate();
            response.sendRedirect("ShoppingList");
        } else {
            String user = (String)session.getAttribute("username");
            if (user != null && !user.equals("")) {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null && !action.equals("")) {
            if (action.equals("register")) {
                String fldUsername = request.getParameter("fldUsername");
                if (fldUsername != null && !fldUsername.equals("")) {
                    session.setAttribute("username", fldUsername);
                    ArrayList<String> items = new ArrayList<String>();
                    session.setAttribute("items", items);
                }
            } else if (action.equals("add")) {
                String fldItem = request.getParameter("fldItem");
                if (fldItem != null && !fldItem.equals("")) {
                    
                    
                    ArrayList<String> items = (ArrayList<String>)session.getAttribute("items");
                    
                    
                    if (items == null) // For safety.
                        items = new ArrayList<String>();
                    items.add(fldItem);
                    session.setAttribute("items", items);
                }
            } else if (action.equals("delete")) {
                String radSelect = request.getParameter("radSelect");
                if (radSelect != null && !radSelect.equals("")) {
                    try {
                        int selected = Integer.parseInt(radSelect);
                        ArrayList<String> items = (ArrayList<String>)session.getAttribute("items");
                       
                        if (items == null) // For safety.
                            items = new ArrayList<String>();
                        items.remove(selected);
                        session.setAttribute("items",items);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        response.sendRedirect("ShoppingList");
        
        
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
