
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
        <title>Postulantes</title>
    </head>
    <body onload="loadPostulantes()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->
            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> estudiante = (Map<String, Object>) request.getSession().getAttribute("estudiante");
                String obPostulante = (String) request.getSession().getAttribute("obPostulante");
                String idPostulante = (String) request.getSession().getAttribute("idPostulante");
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
                <div> 
                    <button type="button" onclick="window.location.href = '../QueryPostulantes.do'" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span>Regresar</span>
                    </button>
                </div>
                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">                    
                    <div class="form-group col-md-12">
                        <div class="card my-4">
                            <h4 class="card-header">Información del estudiante</h4>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <table class="table table-responsive-sm">

                                            <thead>
                                                <tr>
                                                    <th scope="col">Nombre contacto</th>
                                                    <th scope="col">Código Estudiante </th>
                                                    <th scope="col">numero de contacto </th>
                                                    <th scope="col">arl </th>
                                                    <th scope="col">Observacion </th>

                                                    <th></th>
                                                </tr>
                                            </thead

                                            <tbody>
                                                <tr>
                                                    <td><%=estudiante.get("representante")%></td>
                                                    <td><%=estudiante.get("codigo")%></td>
                                                    <td><%=estudiante.get("reptelefono")%></td>
                                                    <td><%=estudiante.get("arl")%></td>
                                                    <td><%=obPostulante%></td>
                                                </tr>

                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content  -->


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
        if (window.performance.navigation.type === 1) {
            location.href = "../QueryDataStudent.do?idestudiante=<%=estudiante.get("idEstudiante")%>&idPostulante=<%=idPostulante%>";
        }
        </script>

    </body>
</html>
