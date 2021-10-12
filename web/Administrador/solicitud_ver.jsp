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
        <title>Solicitud</title>

    </head>
    <body onload = 'loadSolicitudes(<%=request.getSession().getAttribute("idventana")%>)' >
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, String> solicitud = (Map<String, String>) request.getSession().getAttribute("solicitud");
                String idVentana = (String) request.getSession().getAttribute("idventana");
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

                                <div class="row">

                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <section class="container">
                                            <div class="card my-4">
                                                <h5 class="card-header">
                                                    <%if (idVentana.equals("0")) {%>
                                                    Información de la Solicitud
                                                    <%} else {%>
                                                    Información de la visita empresarial
                                                    <%}%>
                                                </h5>
                                                <div class="card-body">
                                                    <div class="form-group">
                                                        <textarea style="vertical-align: middle;" class="form-control" rows="10" readonly>
    Empresa: &nbsp;  <%=solicitud.get("empresa")%>
    Fecha de inicio: &nbsp; <%=solicitud.get("fecInicio")%> 
    Fecha de finalización: &nbsp; <%=solicitud.get("fecFin")%> 
    Fecha de solicitud:&nbsp;  <%=solicitud.get("fecSolicitudAprob")%> 
    Número máx de participantes: &nbsp; <%=solicitud.get("cantidadParticipantes")%> 
    Temática:&nbsp;  <%=solicitud.get("tematica")%> 
    Asignatura: &nbsp; <%=solicitud.get("asignatura")%> 
    Docente: &nbsp; <%=solicitud.get("docente")%> 
    Estatus: &nbsp; <%=solicitud.get("estatus")%> 
    Cupos Disponibles: &nbsp; <%=solicitud.get("cupos")%> 
                                                        </textarea>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-6 col-sm-4"></div>
                                                        <div class="col-6 col-sm-6"></div>
                                                        <div class="col-6 col-sm-2">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>

                                        <section>
                                            <form id="form1" action="../AprobacionSolicitud.do?idSolicitud=<%=solicitud.get("idSolicitud")%>" method="post">                                            
                                                <div class="container">
                                                    <div class="row align-items-start">

                                                        <div class="col-md-12">
                                                            <div class="card my-4">
                                                                <h5 class="card-header">Observaciones</h5>
                                                                <div class="card-body">
                                                                    <div class="form-group">
                                                                        <textarea name="observaciones" class="form-control" <%if (idVentana.equals("1")) {%> disabled <%}%> ><%=solicitud.get("observacion")%></textarea>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <br><br>

                                                            <!-- Modal  -->
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
                                                                            <p id="mensaje"></p>
                                                                        </div>
                                                                        <div class="modal-footer">            
                                                                            <button type="submit" class="btn btn-primary">Si</button>
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- Modal  -->

                                                            
                                                            


                                                            <!-- Actividades  -->
                                                            <div class="card my-4">
                                                                <h5 class="card-header">Cronograma de actividades</h5>
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
                                                                                        <td> <% if (map.get("estaHecha").equals("true")) { %><i class="fas fa-check-circle"></i>
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



                                                            <!-- ModFin Actividades  -->

                                                            <br>

                                                            <%
                                                                Map<String, Object> postulantes = (Map<String, Object>) request.getSession().getAttribute("postulantes");
                                                                if (postulantes.size() != 0) {

                                                            %>
                                                            <!-- Inicio Postulante  -->
                                                            <div class="card my-4">
                                                                <h5 class="card-header">Postulantes</h5>
                                                                <div class="card-body">
                                                                    <div class="form-group">
                                                                        <div class="container">
                                                                            <table id="tabla2" class="table table-responsive-sm">

                                                                                <thead>
                                                                                    <tr>
                                                                                        <th scope="col">Codigo</th>
                                                                                        <th scope="col">Nombre </th>
                                                                                        <th scope="col">Correo </th>
                                                                                        <th scope="col">Telefono</th>
                                                                                            <%if (idVentana.equals("1")) {%>
                                                                                        <th scope="col">Estatus </th>                                                                        
                                                                                            <%}%>
                                                                                        <th></th>
                                                                                    </tr>
                                                                                </thead>


                                                                                <tbody>
                                                                                    <%
                                                                                        for (Map.Entry<String, Object> entry : postulantes.entrySet()) {
                                                                                            Map<String, String> map = (Map<String, String>) entry.getValue();
                                                                                    %>
                                                                                    <tr >
                                                                                        <td><%=map.get("estudianteCodigo")%> </td>
                                                                                        <td><%=map.get("estudianteNombre")%></td>
                                                                                        <td><%=map.get("correo")%></td>
                                                                                        <td><%=map.get("telefono")%></td>      
                                                                                        <td><%=map.get("estatusdescripcion")%> </td>                                                               
                                                                                    </tr>
                                                                                    <%}%>
                                                                                    <% }
                                                                                    %>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--  Fin Postulante  -->


                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                                                <div class="form-row col-md-7">
                                                                <% if (!solicitud.get("estatus").equals("Aprobada")) { %>
                                                                <div class="col-2">
                                                                    <Button type="button" onclick="aprobar()" class="btn btn-primary" >Aprobar</Button>
                                                                </div>
                                                                <div class="col-6">                                   
                                                                    <a onclick="desaprobar()" class="btn btn-danger"> No Aprobar </a>
                                                                </div>
                                                                <%}%>                                                            
                                                            </div>
                                        </section> 
                                                                                
                                                                                
                                                           
                                                                                
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content  -->
            </div>
            <!-- Page Content  -->
        </div>

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>   
        <script src="../js/side-bar/load-admin-1.0.js"></script>
        <script>
            function aprobar (){
               $('#mensaje').text('Estas seguro de aprobar esta solicitud?');
               var action =  $('#form1').attr('action');
               $('#form1').attr('action',action+'&status=2');
                              $('#ventana').modal('show');
           
               
    }
            
            function desaprobar(){
                $('#mensaje').text('Estas seguro que desea desaprobar esta solicitud?');
               var action =  $('#form1').attr('action');
               $('#form1').attr('action',action+'&status=3');
               $('#ventana').modal('show');
            }
            
        </script>
        <!-- jQuery Side-bar -->        
    </body>
</html>
