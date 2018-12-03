/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crunchify.jsp.servlet;

import DAO.AdministradorDAO;
import DAO.CajaDAO;
import DAO.FacturaDAO;
import VO.Administrador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fabian.giraldo
 */
public class ServletEscuela extends  HttpServlet{
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         DAOEscuela daoEscuela = new DAOEscuela();
         List<Escuela> escuelas= daoEscuela.findAll();
         request.setAttribute("escuelas", escuelas);
         //Redireccionando la informacion
         RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
         redireccion.forward(request, response);      
      
     }
     
     
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         DAOEstudiante daoEstudiante = new DAOEstudiante();
         String escuela = request.getParameter("escuela");
         List<Estudiante> estudiantes= daoEstudiante.findAllByEscuela(Integer.parseInt(escuela));
         request.setAttribute("estudiantes", estudiantes);
         //Redireccionando la informacion
         RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
         redireccion.forward(request, response);      
         
      
     }
}
