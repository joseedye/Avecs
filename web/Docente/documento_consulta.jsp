<%-- 
    Document   : Document
    Created on : 18/05/2020, 11:37:10 PM
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

        <title>Documento</title>
    </head>
    <body  onload="loadDocumentos()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">

            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> listDocumentos = (Map<String, Object>) request.getSession().getAttribute("documentos");
                Map<String, Object> listTipoDocumentos = (Map<String, Object>) request.getSession().getAttribute("tipoDocumentos");
                String tipoDesc = (String) request.getSession().getAttribute("tipoDesc");
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
                        <h4>Consulta de documentos</h4> <br>                            
                        <div class="form-group col-md-6">
                            <label for="tipoDocument">Seleccionar Tipo de Documento</label>
                            <select onchange="doSearch()" class="custom-select" id="tipoDocument" name="tipoDocument">
                                <option disabled selected >Seleccionar</option>
                                <%
                                    for (Map.Entry<String, Object> entry : listTipoDocumentos.entrySet()) {
                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                %>                                    
                                <option value="<%=map.get("idTipoDoc")%>"><%=map.get("descripcionTipoDoc")%></option>
                                <% }%>
                            </select>                                
                        </div>

                        <div class="card my-4">
                            <h5 class="card-header">Documentos en el sistema: <%=tipoDesc%></h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Descripción</th>
                                                    <th scope="col">Tipo</th>
                                                    <th scope="col">Descarga</th>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int i = 1;
                                                    for (Map.Entry<String, Object> entry : listDocumentos.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr>
                                                    <td><%=map.get("descripcionDoc")%></td>
                                                    <td><%=map.get("tipoDoc")%></td>
                                                    <td>
                                                        <a download href="/<%=map.get("rutaDoc")%>" title="Descargar"><i class="fas fa-cloud-download-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <% i++;
                                        }%>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- Content  -->
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
                                        <p><%=msg%></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal success -->

        <%
            }
            request.getSession().removeAttribute("msg");
        %>                           

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>   
        <script src="../js/side-bar/load_docente_1.0.js"></script> 
        <!-- jQuery Side-bar -->  

        <script>
            $(document).ready(function () {
                $("#ventana2").modal('show');
            });

            function loadCheck(a, b) {
                location.href = '../UpdateActivDoc.do?idDocumento=' + a + '&publico=' + b;
            }
            
            if (window.performance.navigation.type === 1) {
                location.href = "../QueryDocuments.do";
            }
            
            function doSearch() {
                var tipo = $("#tipoDocument").val();
                location.href = "../QueryDocuments.do?tipoDocument=" + tipo;
            }
        </script> 
    </body>
</html>
