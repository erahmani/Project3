package userInterface;

import businessLogic.exception.DuplicateUniqueCodeException;
import businessLogic.RealCustomerBusinessLogic;
import businessLogic.exception.EmptyFieldException;
import dataAccess.entity.RealCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RealCustomerRegistrationServlet", urlPatterns = {"/RealCustomerRegistrationServlet"})
public class RealCustomerRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RealCustomer realCustomer = userUtility.initRealCustomer(request);
            RealCustomerBusinessLogic.createNewCustomer(realCustomer);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createSuccessfulRegisteredHTML(realCustomer.getCustomerId()));
        } catch (DuplicateUniqueCodeException e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createRealCustomerRegistrationErrorHTML(request, e.getMessage()));
        } catch (EmptyFieldException e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createRealCustomerRegistrationErrorHTML(request, e.getMessage()));
        }
    }

    private static String createSuccessfulRegisteredHTML(String customerId) {
        return "<!DOCTYPE html>" +
                "<head>" +
                "<title>Title</title>" +
                "</head>" +
                "<body>" +
                "<p ><font color=\"green\"><h2 >Successful registration, customer Id is " + customerId + "</h2></font></p>" +
                "</body>" +
                "<div class=\"home-button\">" +
                "<a href=\"Main.html\">" +
                "<button>Home</button>" +
                "</a>" +
                "</div>" +
                "</html>";
    }

    public static String createRealCustomerRegistrationErrorHTML(HttpServletRequest request, String errorMessage) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title></title>" +
                "<style>" +
                "       html, body {" +
                "  height: 100%;" +
                "}" +
                " .dropdown-content text {" +
                "   color: black;" +
                "  padding: 12px 16px;" +
                " text-decoration: none;" +
                "   display: block;" +
                "}" +
                ".dropdown-content text:hover {" +
                "   background-color: #f1f1f1" +
                "}" +
                "div.relative {" +
                "position: relative;" +
                "top: 25%;" +
                "left: 15%;" +
                "width: 70%;" +
                "height: 50%;" +
                "border: solid lightslategrey;" +
                "   background-color: whitesmoke" +
                "}" +
                "div.home-button {" +
                "position: relative;" +
                "top: 30%;" +
                "   left: 48%;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body bgcolor=\"#20b2aa\">" +
                "<p ><font color=\"red\"><h2 >" + errorMessage + "</h2></font></p>" +

                "<div class=\"relative\">" +
                "<center>" +
                "<br> <form action=\"RealCustomerRegistrationServlet\" method=\"POST\">" +


                "firstName:<br>\n" +
                "<input type = \"text\" name = \"firstName\" value = \"" + request.getParameter("firstName") + "\" required> <br>\n" +
                "lastName:<br>\n" +
                "<input type = \"text\" name = \"lastName\" value = \"" + request.getParameter("lastName") + "\" required> <br>\n" +
                "fatherName:<br>\n" +
                "<input type = \"text\" name = \"fatherName\" value = \"" + request.getParameter("fatherName") + "\" required> <br>\n" +
                "birthDay:<br>\n" +
                "<input type = \"date\" name = \"birthDay\" value = \"" + request.getParameter("birthDay") + "\" required> <br>\n" +
                "nationalId:<br>\n" +
                "<input type = \"number\" name = \"nationalId\" value = \"" + request.getParameter("nationalId") + "\" required> <br>\n" +

                "<input type = \"hidden\" name = \"customerType\" value = \"LegalCustomer\" required> <br>\n" +
                "<input type=\"submit\" value=\"Submit\">" +
                "<input type=\"reset\" value=\"Reset\">" +
                "</form>" +
                "</center>" +
                "</div>" +
                "<div class=\"home-button\">" +
                "<a href=\"Main.html\">" +
                "<button>Home</button>" +
                "</a>" +
                "</div>" +
                "<div class=\"home-button\">" +
                "<a href=\"CustomerRegistration.html\">" +
                "<button>Prev</button>" +
                "</a>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
