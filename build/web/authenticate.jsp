<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<%@ page import="filesystem.*" %>

<%
    Integer id = Integer.parseInt(request.getParameter("id"));
    String password = request.getParameter("password");
    
    boolean found = DatabaseHandler.isValidUserById(id, password);
    // boolean found = new FileSystemHandler().isValidUserById(id, password);

    if (found) {
        out.println("OK");
        
    } else {
        out.println("NO");
        
    }
%>