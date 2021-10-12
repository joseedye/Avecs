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
    <body onload="loadVisitas()" >
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
                                                        <div class="col-6 col-sm-2"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>


                                        <!--Observaciones  -->
                                        <div class="col-md-12">
                                            <div class="card my-4">
                                                <h5 id="observaciones" class="card-header">Observaciones</h5>
                                                <div class="card-body">
                                                    <div class="form-group">
                                                        <textarea onblur="loadOb(this.value)" name="observaciones" class="form-control " rows="3" ><%=solicitud.get("observacion")%></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!--Cronograma Actividades  -->
                                        <div class="col-md-12">
                                            <div class="card my-4">
                                                <h4 class="card-header">Cronograma de actividades</h4>
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
                                                                        <th scope="col">Observación </th> 
                                                                        <th scope="col">Lista</th>  
                                                                        <th></th>
                                                                    </tr>
                                                                </thead>


                                                                <tbody>
                                                                    <%
                                                                        int i = 0;
                                                                        Map<String, Object> cronograma = (Map<String, Object>) request.getSession().getAttribute("cronograma");
                                                                        for (Map.Entry<String, Object> entry : cronograma.entrySet()) {
                                                                            Map<String, String> map = (Map<String, String>) entry.getValue();
                                                                    %>
                                                                    <tr >
                                                                        <td id="activ<%=i%>"><%=map.get("actividad")%> </td>
                                                                        <td><%=map.get("descripcion")%></td>
                                                                        <td><%=map.get("fecInicio")%></td>
                                                                        <td><%=map.get("fecFin")%></td>  
                                                                        

                                                                        <td><input onblur="loadObservacion(<%=i%>,<%=map.get("idCronograma")%>, this.value)" value='<%=map.get("observacion")%>' class="form-control" type="text" id="observacion<%=i%>" name="observacion<%=i%>"></td>          
                                                                        <td style="text-align: center">
                                                                            <div class="custom-control custom-switch">
                                                                                <input type="checkbox" class="custom-control-input" id="done<%=i%>" <%if (map.get("estaHecha").equals("true")) {%> checked <%}%> >
                                                                                <label onclick="loadCheck(<%=i%>,<%=map.get("idCronograma")%>,<%=map.get("estaHecha")%>);" class="custom-control-label" for="done<%=i%>"></label>
                                                                            </div>
                                                                        </td>
                                                                            <%i++;
                                                                            }%>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- ModFin Actividades  -->                       

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
        <script src="../js/side-bar/load_docente_1.0.js"></script> 
        <!-- jQuery Side-bar -->     

        <script>
            function loadCheck(i, a, b) {
                var idSolicitud = <%=solicitud.get("idSolicitud")%>;
                location.href = '../UpdateStatusCronograma.do?idCronograma=' + a + '&estatus=' + b + '&idSolicitud=' + idSolicitud + "&idCheck=" + i;
            }

            function loadObservacion(i, a, b) {
                var idSolicitud = <%=solicitud.get("idSolicitud")%>;
                location.href = '../UpdateStatusCronograma.do?idCronograma=' + a + '&idSolicitud=' + idSolicitud + "&idObservacion=" + i + "&observacion=" + b;
            }

            function loadOb(value) {
                var idSolicitud = <%=solicitud.get("idSolicitud")%>;
                location.href = '../UpdateStatusCronograma.do?idSolicitud=' + idSolicitud + "&observacionValue=" + value;
            }
        </script>
    </body>
</html>
