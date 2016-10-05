package userInterface;

import businessLogic.exception.DuplicateUniqueCodeException;
import businessLogic.LegalCustomerBusinessLogic;
import businessLogic.exception.EmptyFieldException;
import dataAccess.entity.LegalCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LegalCustomerRegistrationServlet", urlPatterns = {"/LegalCustomerRegistrationServlet"})
public class LegalCustomerRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LegalCustomer legalCustomer = userUtility.initLegalCustomer(request);
            LegalCustomerBusinessLogic.createNewCustomer(legalCustomer);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createSuccessfulRegisteredHTML(legalCustomer.getCustomerId()));
        } catch (DuplicateUniqueCodeException e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createLegalCustomerRegistrationErrorHTML(request, e.getMessage()));
        } catch (EmptyFieldException e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createLegalCustomerRegistrationErrorHTML(request, e.getMessage()));
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

    public static String createLegalCustomerRegistrationErrorHTML(HttpServletRequest request, String errorMessage) {
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
                "<br> <form action=\"LegalCustomerRegistrationServlet\" method=\"POST\">" +


                "companyName:<br>\n" +
                "<input type = \"text\" name = \"companyName\" value = \"" + request.getParameter("companyName") + "\" required> <br>\n" +
                "registrationDate:<br>\n" +
                "<input type = \"date\" name = \"registrationDate\" value = \"" + request.getParameter("registrationDate") + "\" required> <br>\n" +
                "economicCode:<br>\n" +
                "<input type = \"number\" name = \"economicCode\" value = \"" + request.getParameter("economicCode") + "\" required> <br>\n" +


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
