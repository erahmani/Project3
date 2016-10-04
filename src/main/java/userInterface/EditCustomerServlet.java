package userInterface;

import businessLogic.LegalCustomerBusinessLogic;
import businessLogic.RealCustomerBusinessLogic;
import businessLogic.exception.DuplicateUniqueCodeException;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "EditCustomerServlet", urlPatterns = {"/EditCustomerServlet"})
public class EditCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerType = request.getParameter("customerType");
        try {
            System.out.println("Economic Code: " + request.getParameter("economicCode"));
            System.out.println("customertype " + customerType);
            if (customerType.equals("RealCustomer")) {
                RealCustomer realCustomer = userUtility.initRealCustomer(request);
                RealCustomerBusinessLogic.editCustomer(realCustomer);
                System.out.println("RalCustiner  ");
            } else if (customerType.equals("LegalCustomer")) {
                LegalCustomer legalCustomer = userUtility.initLegalCustomer(request);
                System.out.println("ELSDFKASDFAF   " + legalCustomer.getEconomicCode());
                LegalCustomerBusinessLogic.editCustomer(legalCustomer);
            }
            PrintWriter printWriter = response.getWriter();
            printWriter.println(createEditSuccessfulHTML());
        } catch (DuplicateUniqueCodeException e) {
            String html = null;
            if (customerType.equals("LegalCustomer")) {
                html = createLegalCustomerEditErrorHTML(request, e.getMessage());
            } else if (customerType.equals("RealCustomer")) {
                html = createRealCustomerEditErrorHTML(request, e.getMessage());
            }
            PrintWriter printWriter = response.getWriter();
            printWriter.println(html);
        }
    }

    public static String createEditSuccessfulHTML() {
        return "<!DOCTYPE html>" +
                "<head>" +
                "<title>Title</title>" +
                "</head>" +
                "<body>" +
                "<p ><font color=\"green\"><h2 >Successful Edit !!</h2></font></p>" +
                "</body>" +
                "<div class=\"home-button\">" +
                "<a href=\"Main.html\">" +
                "<button>Home</button>" +
                "</a>" +
                "</div>" +
                "</html>";
    }

    public static String createRealCustomerEditErrorHTML(HttpServletRequest request, String errorMessage) {
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

                "customerId:<br>\n" +
                "<input type = \"number\" name = \"customerId\" value = \"" + request.getParameter("customerId") + "\" readonly> <br>\n" +
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

                "<div class=\"home-button\">" +
                "<a href=\"Main.html\">" +
                "<button>Home</button>" +
                "</a>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    public static String createLegalCustomerEditErrorHTML(HttpServletRequest request, String errorMessage) {
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

                "customerId:<br>\n" +
                "<input type = \"number\" name = \"customerId\" value = \"" + request.getParameter("customerId") + "\" readonly> <br>\n" +
                "companyName:<br>\n" +
                "<input type = \"text\" name = \"companyName\" value = \"" + request.getParameter("companyName") + "\" required> <br>\n" +
                "registrationDate:<br>\n" +
                "<input type = \"date\" name = \"registrationDate\" value = \"" + request.getParameter("registrationDate") + "\" required> <br>\n" +
                "economicCode:<br>\n" +
                "<input type = \"number\" name = \"economicCode\" value = \"" + request.getParameter("economicCode") + "\" required> <br>\n" +

                "<div class=\"home-button\">" +
                "<a href=\"Main.html\">" +
                "<button>Home</button>" +
                "</a>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
