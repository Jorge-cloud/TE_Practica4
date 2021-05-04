package com.emergentes.controlador;

import com.emergentes.conexion.ConexionDB;
import cpm.emergentes.modelo.Tareas;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        ArrayList<Tareas> lista = new ArrayList<Tareas>();
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;

        if (op.equals("list")) {

            try {
                //listar datos con una consulta
                String sql = "select*from tareas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                //y lo enivamos al index;
                while (rs.next()) {
                    Tareas tar = new Tareas();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));

                    tar.setCompletado(rs.getInt("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Error en sql " + ex.getMessage());
            } finally {
                canal.desconectar();
            }

        }
        if (op.equals("nuevo")) {
            //Instancia un objeto de la clase libro
            Tareas t = new Tareas();
            //el objeto se pone como atributo dee request
            request.setAttribute("tarea", t);
                        //redireccionar a editar
            request.getRequestDispatcher("nuevo.jsp").forward(request, response);
        }
        if (op.equals("editar")) {

            try {
                
                int id = Integer.parseInt(request.getParameter("id"));
                //para listar los datos
                String sql = "select * from tareas where id=?";
                // para consultar de seleccion y almacenar el una coleccion
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Tareas tar = new Tareas();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));
                    tar.setCompletado(rs.getInt("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                //enviar a index.jsp para mostrar la informacion
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            
                
            } catch (SQLException ex) {
                System.out.println("Error en SQL:" + ex.getMessage());
            }
        }

        if (op.equals("eliminar")) {
            //obtener el id
            int id = Integer.parseInt(request.getParameter("id"));

            //realiza la eliminacion
            try {
                String sql = "delete from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error en sql " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            //redirecciona a main controller
            response.sendRedirect("MainController");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");
        int prioridad = Integer.parseInt(request.getParameter("prioridad"));
        int completado = Integer.parseInt(request.getParameter("completado"));

        Tareas t = new Tareas();

        t.setId(id);
        t.setTarea(tarea);
        t.setPrioridad(prioridad);
        t.setCompletado(completado);

        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        try {
        if (id == 0) {
            
                String sql = "insert into tareas(tarea,prioridad,completado)values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());
                ps.executeUpdate();
            
        }else{
                
                // edicion de registro
                String sql = "UPDATE tareas SET tarea = ? , prioridad = ? , completado = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());
                ps.setInt(4, t.getId());
                ps.executeUpdate();
            }
        response.sendRedirect("MainController");
        } catch (SQLException ex) {
                System.out.println("Error en sql " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
    
        
    }

}
