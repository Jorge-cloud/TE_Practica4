<%@page import="cpm.emergentes.modelo.Tareas"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%
    List<Tareas> lista = (List<Tareas>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Tarea</h1>
        <form action="MainController" method="POST">
            <table>               
                <c:forEach var="item" items="${lista}">
                    <input type="hidden" name="id" value="${item.id}">
                    <tr>
                        <td>TAREA</td>
                        <td><input type="text" name="tarea" value="${item.tarea}"></td>
                    </tr>
                    <tr>
                        <td>PRIORIDAD</td>
                        <td>
                            <select name="prioridad">
                            <option value="1" <c:if test="${item.prioridad == 1}">selected</c:if>>Alta</option>
                            <option value="2" <c:if test="${item.prioridad == 2}">selected</c:if>>Media</option>
                            <option value="3" <c:if test="${item.prioridad == 3}">selected</c:if>>Baja</option>

                        </select>
                        </td>
                        
                    </tr>
                    <tr>
                        <td>COMPLETADO</td>
                        <td>
                            <input type="radio" name="completado" value="1"<c:if test="${item.completado == 1}">checked</c:if>>Concluido<br>
                            <input type="radio" name="completado" value="2"<c:if test="${item.completado == 2}">checked</c:if>>Pendiente
                        </td>
                        
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enviar"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>  
    </body>
</html>