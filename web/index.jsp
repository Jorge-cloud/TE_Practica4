<%@page import="java.util.List"%>
<%@page import="cpm.emergentes.modelo.Tareas"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <h1>Listado de Libros</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="i" items="${lista}">
                <tr>
                    <td>${i.id}</td>
                    <td>${i.tarea}</td>
                    <td>
                        <c:if test="${i.prioridad == 1}">Alta</c:if>
                        <c:if test="${i.prioridad == 2}">Media</c:if>
                        <c:if test="${i.prioridad == 3}">Baja</c:if>
                        
                    </td>
                    <td>
                    <c:if test="${i.completado == 1}"><input type="checkbox"  readonly="readonly" checked onclick="javascript: return false;"/></c:if>
                    <c:if test="${i.completado == 2}"><input type="checkbox"  readonly="readonly" onclick="javascript: return false;"/></c:if>
                    </td>
                    <td><a href="MainController?op=editar&id=${i.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${i.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
                
            
                                  
            
        </table>
        
    </body>
</html>
