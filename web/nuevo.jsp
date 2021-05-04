<%@page import="cpm.emergentes.modelo.Tareas"%>

<%@page import="java.util.List"%>
<%
    Tareas tarea = (Tareas) request.getAttribute("tarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nueva Tarea</h1>
        <form action="MainController" method="POST">
            <table>               

                <input type="hidden" name="id" value="${tarea.id}">
                <tr>
                    <td>TAREA</td>
                    <td><input type="text" name="tarea" value="${tarea.tarea}"></td>
                </tr>
                <tr>
                    <td>PRIORIDAD</td>
                    <td>
                        <select name="prioridad">
                            <option value="1">Alta</option>
                            <option value="2">Media</option>
                            <option value="3">Baja</option>

                        </select>

                    </td>



                </tr>
                <tr>
                    <td>COMPLETADO</td>
                    <td><input type="radio" name="completado" value="1">Concluido<br>
                        <input type="radio" name="completado" value="2">Pendiente
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>

            </table>
        </form>  
    </body>
</html>
