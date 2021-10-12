<%-- 
    Document   : Solicitu
    Created on : 18/05/2020, 11:43:55 PM
    Author     : rozo-
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Sidebar style  -->
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../css/side-bar/style-side-bar.css">
        <link rel="stylesheet" href="../css/style-inputs-validation.css">
        <script defer src="../font/solid.js"></script>
        <script defer src="../font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Solicitud</title>
    </head>
    <body onload="loadSolicitud()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">


            </nav>
            <!-- Sidebar  -->
            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> empresas = (Map<String, Object>) request.getSession().getAttribute("empresas");
                Map<String, Object> asignaturas = (Map<String, Object>) request.getSession().getAttribute("asignaturas");
                Map<String, Object> solicitud = (Map<String, Object>) request.getSession().getAttribute("solicitud");
                Map<String, Object> cronograma = (Map<String, Object>) request.getSession().getAttribute("cronograma");
                int i = 1;
            %>
            <!-- Page Content  -->
            <div id="content">     
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>Menú</span>
                        </button>
                        <div>
                            <h5><%=user.get("nombres") + ""%></h5>
                        </div>
                        <div class="img-profile">
                            <img src="<%=userImg%>">                        
                        </div>
                    </div>
                </nav>

                <!-- Content  --> 
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-12">
                        <div class="contenedor">
                            <form name="form1" id="form1" action="../RegisterRequest.do?idsolicitud=<%=(solicitud == null) ? "0" : solicitud.get("idSolicitud") + ""%>" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Solicitud de Visita Empresarial</h4>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="Elegir empresa">Elegir empresa</label>
                                        <select class="form-control" name="empresa" id="empresa">
                                                <%
                                                    for (Map.Entry<String, Object> entry : empresas.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>       
                                            <option value="<%=map.get("idEmpresa")%>"><%=map.get("nombreEmpresa")%></option>
                                            <% }%>  
                                        </select>

                                        <label for="Tematica">Temática</label>
                                        <input type="text" name="Tematica" value="<%=(solicitud == null) ? "" : solicitud.get("tematica") + ""%>" class="form-control" id="Tematica" placeholder="tematica" >
                                        <label for="NumMax">Número máximo de estudiantes</label>
                                        <input type="number" name="NumMax" min="0" value="<%=(solicitud == null) ? "" : solicitud.get("cantidadParticipantes") + ""%>"class="form-control col-md-2" id="NumMax" placeholder="Num" onKeyUp="if (this.value.length > 3) {
                                                    this.value = this.value.substr(0, 3);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }">
                                    </div>


                                    <div class="form-group col-md-6">

                                        <label for="Asignatura">Asignatura</label>
                                        <select class="form-control" name="Asignatura" id="Asignatura">

                                            <%if (solicitud == null) {%>
                                            <option disabled selected>Seleccione</option>
                                            <% } else {%>
                                            <option value="<%=solicitud.get("asignatura")%>"> <%=solicitud.get("nombreasignatura")%> </ option > 
                                                <%  }  %>

                                                <%
                                                    for (Map.Entry<String, Object> entry : asignaturas.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>     
                                            <option value="<%=map.get("id_asignatura")%>"><%=map.get("descripcion_asignatura")%></option>
                                            <% }%> 
                                        </select>

                                        <label for="Fecha">Fecha de Inicio</label>
                                        <input  class="form-control  col-md-6" type="date" name="Fecha" id="Fecha" value="<%=(solicitud == null) ? "" : solicitud.get("fecInicio") + ""%>" required>

                                        <label for="FechaF">Fecha de Fin</label>
                                        <input class="form-control  col-md-6"  type="date" name="FechaF" id="FechaF" value="<%=(solicitud == null) ? "" : solicitud.get("fecFin") + ""%>" required>
                                    </div>

                                </div>


                                <div class="form-group">
                                    <label for="comment">Comentario:</label>
                                    <textarea name="observacion" class="form-control col-md-6" rows="5" id="comment"><%=(solicitud == null) ? "" : solicitud.get("observacion") + ""%></textarea>
                                </div>
                                <br>   

                                <!-- Content Cronograma  -->
                                <div style="justify-content:center;" class="form-row">
                                    <div class="form-group col-md-12">

                                        <div class="card my-4">
                                            <h4 class="card-header">Administración de Cronograma</h4>
                                            <div class="card-body">
                                                <div class="form-group">
                                                    <div class="container">

                                                        <table id="tabla1" class="table table-responsive-sm">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col">Actividad</th>
                                                                    <th scope="col">Descripción </th>
                                                                    <th scope="col">Fecha Inicio </th>
                                                                    <th scope="col">Fecha Fin </th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                                <%
                                                                    for (Map.Entry<String, Object> entry : cronograma.entrySet()) {
                                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                                %>
                                                                <tr id="<%=i%>">
                                                                    <td style="vertical-align:middle;"><input type="text" id="actividad<%=i%>" name="actividad<%=i%>" value="<%=map.get("actividad")%>" class="form-control" placeholder="Actividad"></td>
                                                                    <td><textarea id="descripcion<%=i%>" name="descripcion<%=i%>" value="<%=map.get("descripcion")%>" class="form-control" placeholder="Descripcion"><%=map.get("descripcion")%></textarea></td>
                                                                    <td><input class="form-control" type="date" id="FechaInicio<%=i%>" name="FechaInicio<%=i%>" value="<%=map.get("fecInicio")%>" required></td>
                                                                    <td><input class="form-control" type="date" id="FechaFin<%=i%>" name="FechaFin<%=i%>" value="<%=map.get("fecFin")%>" required></td>

                                                                    <td>
                                                                        <a onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow2(<%=i%>)" href="#tabla1" onclick="deleteRow(<%=i%>)" title="Eliminar actividad"><i class="fas fa-trash-alt"></i></a>
                                                                    </td> 
                                                                </tr>
                                                                <% i++;
                                                                    }%>
                                                            </tbody>

                                                            <input style="display: none;" name="lastRow" id="lastRow" value="<%=i%>">
                                                        </table>
                                                        <a href="#btn" onclick="createRow()" ><input  type="button" value="Nueva Actividad" class="btn btn-sm btn-primary"></a>
                                                    </div>
                                                </div>
                                                <!-- Content Cronograma  -->


                                                <button title="Esta opción la guarda temporalmente." type="button" onclick="guardar()" name="Guardar" class="btn btn-primary">Guardar</button>
                                                <button title="Esta opción envia la solicitud a dirección." type="button" onclick="enviar()" name="Enviar" class="btn btn-danger">Solicitar Aprobación</button>
                                                <a id="btn"></a>

                                                <!-- Modal  -->                        
                                                <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">Mensaje</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <p id="modal-msg"> </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Modal  -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Page Content  -->


        <!-- jQuery Side-bar -->       
        <script src="../js/validations-inputs/docente/docente-validation.js"></script>
        <script src="../js/side-bar/load_docente_1.0.js"></script> 
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>  
        <!-- jQuery Side-bar --> 

        <input style="display: none;" id="count" value="<%=i%>">        
    </body>
</html>
