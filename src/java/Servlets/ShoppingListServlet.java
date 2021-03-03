package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 468181
 */
@WebServlet(name = "ShoppingListServlet", urlPatterns = {"/ShoppingList"})
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action != null && action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            String message = "Successfully logged out";
            request.setAttribute("message", message);
        }

        String username = (String) session.getAttribute("username");
        ArrayList<String> listItems = (ArrayList<String>) session.getAttribute("listItems");

        if (listItems != null) {
            request.setAttribute("listitems", listItems);
        }

        if (username != null && !username.equals("")) {
            request.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> listItems = (ArrayList<String>) session.getAttribute("listItems");
        if (listItems == null) {
            listItems = new ArrayList<String>();
        }

        boolean forward = true;

        if (action.equals("register")) {
            String username = request.getParameter("username");
            if (username != null && !username.equals("")) {
                session.setAttribute("username", username);
                request.setAttribute("username", username);
            } else {
                String message = "Invalid name";
                request.setAttribute("message", message);
                forward = false;
            }
        }

        if (action.equals("add")) {
            String listItem = request.getParameter("listitem");
            if (listItem != null && !listItem.equals("")) {
                listItems.add(listItem);
            }
        }

        if (action.equals("delete")) {
            String deleteItem = request.getParameter("deleteitem");
            if (deleteItem != null && !deleteItem.equals("")) {
                listItems.remove(deleteItem);
            }
        }

        session.setAttribute("listItems", listItems);
        request.setAttribute("listitems", listItems);

        if (forward) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }

    }
}
