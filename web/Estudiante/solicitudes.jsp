
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
        <title>Visitas</title>
    </head>
    <body onload="loadPostulacion()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> solicitudes = (Map<String, Object>) request.getSession().getAttribute("solicitudes");
                String filterMyPostulation = (String) request.getSession().getAttribute("filterMyPostulation");
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
                    <div class="form-group col-md-10">

                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" name="myPostulation" class="custom-control-input" <%if (filterMyPostulation.equals("true")) {%> checked <%}%>>
                            <label onclick="loadMyPostulations()" class="custom-control-label" name="myPostulation" for="myPostulation">Mis postulaciones pendientes de aprobación</label>
                        </div>

                        <div class="card my-4">
                            <h5 class="card-header">Visitas Empresariales Disponibles</h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">        
                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Empresa</th>
                                                    <th scope="col">Fecha Inicio</th>
                                                    <th scope="col">Fecha Fin</th>
                                                    <th scope="col">Docente</th>
                                                    <th scope="col">Cupos Disponibles</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (Map.Entry<String, Object> entry : solicitudes.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr>
                                                    <td><%=map.get("empresa")%></td>
                                                    <td><%=map.get("fecInicio")%></td>
                                                    <td><%=map.get("fecFin")%></td>
                                                    <td><%=map.get("docente")%></td>
                                                    <td style="text-align: center"><%=map.get("cupos")%></td>
                                                    <td>
                                                        <a href="../QuerySolicitudes.do?idSolicitud=<%=map.get("idSolicitud")%>&idventana=0"><input type="button" name="Ver" value="Ver" class="btn btn-sm btn-primary"></a>
                                                    </td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
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
                                    <p>Esta seguro de eliminar la solicitud del sistema?</p>
                                </div>
                                <div class="modal-footer">            
                                    <button type="button" class="btn btn-primary">Si</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content  -->                

            </div>
            <!-- Page Content  -->
        </div>

        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg != null) {
        %>

        <!-- Modal success -->                        
        <div class="modal fade" id="ventana2" tabindex="-1" role="dialog">
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
                                    $("#ventana2").modal('show');
                                });

                                if (window.performance.navigation.type === 1) {
                                    location.href = "../QuerySolicitudes.do";
                                }

                                function loadMyPostulations() {
                                    var check = <%=filterMyPostulation%>;
                                    if (check) {
                                        location.href = "../QuerySolicitudes.do?filterMyPostulation=false";
                                    } else {
                                        location.href = "../QuerySolicitudes.do?filterMyPostulation=true";
                                    }
                                }
        </script>
    </body>
</html>
