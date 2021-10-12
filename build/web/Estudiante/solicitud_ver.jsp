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
        <script defer src="../font/solid.js"></script>
        <script defer src="../font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Vista</title>

    </head>
    <body onload= 'loadPostulacion(<%=request.getSession().getAttribute("idventana")%>)'>
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                String idVentana = (String) request.getSession().getAttribute("idventana");
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String estatusPostulante = (String) request.getSession().getAttribute("estatusPostulante");
                int idEstudiante = (Integer) request.getSession().getAttribute("idEstudiante");
                Integer idPostulante = (Integer) request.getSession().getAttribute("idPostulante");
                String observacionPostulante = (String) request.getSession().getAttribute("observacionPostulante");                
                
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, String> solicitud = (Map<String, String>) request.getSession().getAttribute("solicitud");
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
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="container">
                        <div class="row">
                            <div class="container">                              

                                <div class="row">
                                </div>
                                <%
                                    if (estatusPostulante != "") {%>
                                <h5>Estatus: </h5> <p><%=estatusPostulante%></p>
                                <% }%>
                                
                                <% if(!observacionPostulante.equals("")){%>
                                <p style="color: red">Observación: <%=observacionPostulante%></p>
                                <%}%>

                                <div class="row">

                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <section class="container">
                                            <div class="card my-4">
                                                <h5 class="card-header">Información de la Visita Empresarial</h5>
                                                <div class="card-body">
                                                    <div class="form-group">
                                                        <textarea style="vertical-align: middle;" class="form-control" rows="10" disabled>
    Empresa: &nbsp;  <%=solicitud.get("empresa")%>
    Fecha de inicio: &nbsp; <%=solicitud.get("fecInicio")%> 
    Fecha de finalización: &nbsp; <%=solicitud.get("fecFin")%>
    Número máx de participantes: &nbsp; <%=solicitud.get("cantidadParticipantes")%> 
    Temática:&nbsp;  <%=solicitud.get("tematica")%> 
    Asignatura: &nbsp; <%=solicitud.get("nombreasignatura")%>
    Docente: &nbsp; <%=solicitud.get("docente")%> 
    Cupos Disponibles: &nbsp; <%=solicitud.get("cupos")%> 
                                                        </textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>

                                        <section>
                                            <form id="form1" action="../Postulate.do?idSolicitud=<%=solicitud.get("idSolicitud")%>" method="post">                                            
                                                <div class="container">
                                                    <div class="row align-items-start">

                                                        <div class="col-5">

                                                            <% if (estatusPostulante != null && estatusPostulante.equals("Pendiente")) { %>
                                                            <div>
                                                                <div class="row ">
                                                                    <a href="#ventana2" class="btn btn-danger" data-toggle="modal"> Cancelar postulación </a>
                                                                </div>
                                                            </div>
                                                            <% } else if (estatusPostulante == null) {  %>
                                                            <div>
                                                                <div class="row ">
                                                                    <a href="#ventana" class="btn btn-primary" data-toggle="modal">Postular</a>
                                                                </div>
                                                            </div>

                                                            <%}%>
                                                            
                                                            
                                                            <!-- Modal Postulate -->
                                                            <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title">Alerta</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <p>Se solicitará aprobación para asistir a esta visita empresarial, seguro?</p>
                                                                        </div>
                                                                        <div class="modal-footer">            
                                                                            <button type="button" onclick="postulate(<%=idEstudiante%>)" class="btn btn-primary">Si</button>
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- Modal Postulate  -->

                                                            <!-- Modal Cancelate -->
                                                            <div class="modal fade" id="ventana2" tabindex="-1" role="dialog">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title">Alerta</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <p>Se cancelará la solicitud de aprobación, seguro?</p>
                                                                        </div>
                                                                        <div class="modal-footer">            
                                                                            <button type="button" onclick="cancelate(<%=idPostulante%>)" class="btn btn-primary">Si</button>
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- Modal Cancelate -->
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </section> 
                                    </div>
                                </div>
                            </div>
                        </div>
                                                           

                        <!-- Actividades  -->
                        <div class="card my-4">
                            <h5 class="card-header">Cronograma De Actividades</h5>
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
                                                        <%if (idVentana.equals("1")) {%>
                                                    <th scope="col">Realizada </th>                                                                        
                                                        <%}%>

                                                    <%if (idVentana.equals("1")) {%>
                                                    <th scope="col">Observación </th>                                                                        
                                                        <%}%>
                                                    <th></th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <%
                                                    Map<String, Object> cronograma = (Map<String, Object>) request.getSession().getAttribute("cronograma");
                                                    for (Map.Entry<String, Object> entry : cronograma.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr >
                                                    <td><%=map.get("actividad")%> </td>
                                                    <td><%=map.get("descripcion")%></td>
                                                    <td><%=map.get("fecInicio")%></td>
                                                    <td><%=map.get("fecFin")%></td>      
                                                    <%if (idVentana.equals("1")) {%>
                                                    <td style="text-align: center"> <% if (map.get("estaHecha").equals("true")) { %><i class="fas fa-check-circle"></i>
                                                        <%}%></td>                         
                                                        <%}%>                                                                         
                                                        <%if (idVentana.equals("1")) {%>
                                                    <td><%=map.get("observacion")%> </td>                                                               
                                                    <%}%>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ModFin Actividades  -->

                </div>      <!-- Page Content  -->
            </div>  <!-- Content  -->
        </div>  <!-- wraper  -->     



        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg != null) {
        %>

        <!-- Modal success -->                        
        <div class="modal fade" id="ventana3" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Mensaje</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p> <%=msg%> </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal success -->

        <%   }
            request.getSession().removeAttribute("msg");
        %>




        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>   
        <script src="../js/side-bar/load-estudiante-1.0.js"></script>
        <!-- jQuery Side-bar -->      
        <script>
            $(document).ready(function () {
                $("#ventana3").modal('show');
            });

            if (window.performance.navigation.type === 1) {
                var idSolicitud = '<%=solicitud.get("idSolicitud")%>';
                location.href = '../QuerySolicitudes.do?idSolicitud=' + idSolicitud + "&idventana=0";
            }

            function cancelate(idPostulante) {
                var action = $("#form1").attr("action");
                $("#form1").attr("action", action + "&idPostulante=" + idPostulante + "&isCancelate=true");
                $("#form1").submit();
            }

            function postulate(idEstudiante) {
                var action = $("#form1").attr("action");
                $("#form1").attr("action", action + "&idEstudiante=" + idEstudiante + "&isCancelate=false");
                $("#form1").submit();
            }
        </script>
    </body>
</html>
