<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.co.sergio.mundo.vo.*"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Estudiantes</title>
    </head>
    <body>
        <div align="center" style="margin-top: 50px;">
            <%
              if(request.getAttribute("escuelas")!=null){
            %>   
            <form action="Escuela" method="post">
                <select name = "escuela">
           <%
              List<Escuela> escuelas  = (List<Escuela>)request.getAttribute("escuelas");  
               for (Escuela escuela : escuelas) {
             %>
               <option value="<%=escuela.getCodigo()%>"><%=escuela.getNombre()%></option>
             
             <%
               }
           %>
                </select>
                <input type="submit" value="submit">
            </form>
            <%
              }
            
            %>
            
          
        </div>


        <div align="center" style="margin-top: 50px;">
            
            <%
              if(request.getAttribute("estudiantes")!=null){
            %>      
              <table style="width:100%">
               <tr>
                    <th>Codigo</th>
                    <th>Nombre</th> 
                    <th>Escuela</th>
              </tr>
                
              <%
              List<Estudiante> estudiantes  = (List<Estudiante>)request.getAttribute("estudiantes");  
               for (Estudiante estudiante : estudiantes) {
             %>
             
               <tr>
                  <td><%=estudiante.getCodigo()%></td>
                  <td><%=estudiante.getNombre()%></td> 
                  <td><%=estudiante.getEscuela()%></td>
              </tr>
             
             <%
             }
             %>
               
                </table>
               
            <%
              }
            %>   
            
        </div>
    
    
</body>
</html>
