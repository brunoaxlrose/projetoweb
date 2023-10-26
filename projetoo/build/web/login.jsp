<%-- 
    Document   : login
    Created on : 5 de set. de 2023, 18:59:00
    Author     : Bruno Oliveira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.*" import="java.lang.*" %>
<%@page import="model.Usuario"%>
<%@page import="control.ControleUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema JBM - Login</title>
    </head>
    <body>
        <h1>Login</h1>
                <%
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            
            ControleUsuario controleUsuario = new ControleUsuario();
            if (controleUsuario.logar(login, senha)){
                //out.println("Acesso Permitido");
        %>
                <script> window.location.href='menu.jsp'; </script>
        <%
            }else{
                out.println("Acesso NEGADO!!!");
            }
        %>
    </body>
</html>
