package ua.my.oblikchasu.util;

import java.io.PrintWriter;

public class JavaScriptMessage {
    public static void alert (PrintWriter out, String message, String redirect) {
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + message + "');");
        out.println("location='" + redirect + "';");
        out.println("</script>");
    }
}
