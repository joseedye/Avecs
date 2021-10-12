
<%@page import="java.util.ArrayList"%>
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
    <body onload="loadVisitas()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> solicitudes = (Map<String, Object>) request.getSession().getAttribute("solicitudes");
                Map<String, Object> empresas = (Map<String, Object>) request.getSession().getAttribute("empresas");
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

                        <div class="card my-4">
                            <h4 class="card-header">Visitas Empresariales Comunicación Social</h4>
                            <form id="form1" action="../QueryVisitas.do" method="post">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
                                    &nbsp;<label for="">Año</label>
                                    <select class="form-control" id="anio" name="anio">
                                        <option disabled selected>Seleccione</option>
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                        <option value="2025">2025</option>
                                        <option value="2026">2026</option>
                                        <option value="2027">2027</option>
                                        <option value="2028">2028</option>
                                        <option value="2029">2029</option>
                                        <option value="2030">2030</option>
                                        <option value="2031">2031</option>
                                        <option value="2032">2032</option>
                                        <option value="2033">2033</option>
                                        <option value="2034">2034</option>
                                        <option value="2035">2035</option>
                                    </select>
                                </div>

                                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
                                    &nbsp;<label for="Elegir empresa">Elegir empresa</label>
                                    <select class="form-control" name="empresa" id="empresa">
                                        &nbsp;<option disabled selected>Seleccione</option>
                                        <%
                                            for (Map.Entry<String, Object> entry : empresas.entrySet()) {
                                                Map<String, String> map = (Map<String, String>) entry.getValue();
                                        %>       
                                            <option value="<%=map.get("idEmpresa")%>"><%=map.get("nombreEmpresa")%></option>                                            
                                        <%}%>  
                                    </select>
                                </div>

                                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
                                    <a ><input  type="submit" value="Buscar" class="btn btn-sm btn-primary"></a>
                                </div>
                            </div>
                        </form>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                  
                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Empresa</th>
                                                    <th scope="col">Fecha Inicio</th>
                                                    <th scope="col">Fecha Fin</th>
                                                    <th scope="col">Participantes</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int i = 1;
                                                    for (Map.Entry<String, Object> entry : solicitudes.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr id="<%=i%>">
                                                    <td><%=map.get("empresa")%></td>
                                                    <td><%=map.get("fecInicio")%></td>
                                                    <td><%=map.get("fecFin")%></td>
                                                    <td><%=map.get("docente")%></td>
                                                    <td>
                                                        <a onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow(<%=i%>)" href="../QueryRequest.do?isVisita=true&idSolicitud=<%=map.get("idSolicitud")%>&idventana=1"><input type="button" name="Ver" value="Ver" class="btn btn-sm btn-primary"></a>
                                                    </td>
                                                </tr>
                                                <% i++;}%>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <!-- Content  -->                

                            </div>
                            <!-- Page Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>   
        <script src="../js/side-bar/load_docente_1.0.js"></script> 
        <script src="../js/validations-inputs/docente/docente-validation.js"></script> 
        <!-- jQuery Side-bar -->        
    </body>
</html>
