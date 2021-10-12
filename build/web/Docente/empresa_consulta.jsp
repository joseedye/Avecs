<%-- 
    Document   : ConsultarEmpres
    Created on : 18/05/2020, 11:48:49 PM
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
        <script defer src="../font/solid.js"></script>
        <script defer src="../font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Consultar Empresa</title>
    </head>
    <body onload="loadModificar()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">

            </nav>
            <!-- Sidebar  -->


            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> listEmpresas = (Map<String, Object>) request.getSession().getAttribute("empresas");

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
                    <section class="container ">
                        <div class="card my-4">
                            <h5 class="card-header">Consulta de empresa</h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Razón social</th>
                                                    <th scope="col">Email</th>
                                                    <th scope="col">Contacto</th>
                                                    <th scope="col">Teléfono de contacto</th>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int i=1;
                                                    for (Map.Entry<String, Object> entry : listEmpresas.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr id="<%=i%>">
                                                    <td><%=map.get("nombreEmpresa")%></td>
                                                    <td><%=map.get("emailEmpresa")%></td>
                                                    <td><%=map.get("personaContacto")%></td>
                                                    <td><%=map.get("telPersonaContacto")%></td>
                                                    <td>
                                                        <a onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow(<%=i%>)" href="../UpdateCompany.do?idEmpresaQuery=<%=map.get("idEmpresa")%>" title="Modificar"><i class="fas fa-pencil-alt"></i></a>
                                                    </td>
                                                    <td>
                                                        <a onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow2(<%=i%>)" href="#ventana" class="modal-btn" data-some-id="<%=map.get("idEmpresa")%>" title="Eliminar" data-toggle="modal"><i class="fas fa-trash-alt"></i></a>
                                                    </td>          
                                                </tr>                                                                    

                                                <% i++;}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

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
                                                <p>Estas seguro de eliminar la empresa del sistema? </p>
                                                <p>Sólo se eliminará si la empresa no está referenciada en alguna visita empresarial!
                                                </p>
                                            </div>
                                            <div class="modal-footer"> 
                                                <a class="btn btn-primary">Si</a>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Modal  -->

                            </div>
                        </div>
                    </section>
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
        <script>
        $(function () {
            $(".modal-btn").click(function () {
                var id = $(this).data('some-id');
                $(".modal-footer h2").text(id);
                $(".modal-footer a").attr("href", "../DeleteCompany.do?idEmpresa=" + id);
            })
        });
        </script>
        <!-- jQuery Side-bar -->        
    </body>
</html>
