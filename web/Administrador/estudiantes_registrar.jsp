
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
        <title>Registro Estudiantes</title>
    </head>
    <body onload="loadUsuarios()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->
            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> listEstudiantes = (Map<String, Object>) request.getSession().getAttribute("listEstudiantes");
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

                        <h5>Carga de estudiantes</h5><br>

                        <p>El proceso de carga de estudiantes al sistema, se hará mediante un archivo excel, 
                            de las asginaturas Planeación de la comunicación y Estrategias de la comunicación 
                            pertenecientes a 6to y 7mo semestre de Comunicación Social.
                        </p>

                        <br><br>
                        <form id="form1" action="../LoadStudents.do" method="POST" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
                                    <label for='file'>Seleccionar archivo excel:</label>
                                    <input class="form-control-file" type="file" id="file" name="file" required/>   

                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
                                    <br>
                                    <a name="btn" onclick="validar()"><input type="button" class="btn btn-group btn-primary" value="Subir Archivo"/> </a>
                                </div>
                            </div>
                        </form>  


                        <%
                            if (listEstudiantes != null) {
                        %>  

                        <div class="card my-4">
                            <h5 class="card-header"><%=listEstudiantes.size()%> Estudiantes registrados exitosamente</h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Código</th>
                                                    <th scope="col">Email </th>
                                                </tr>
                                            </thead>

                                            <tbody>

                                                <%
                                                    for (Map.Entry<String, Object> entry : listEstudiantes.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr>
                                                    <td><%=map.get("nombre")%></td>
                                                    <td><%=map.get("codigo")%></td>
                                                    <td><%=map.get("email")%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                        <% }%> 
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
        <script src="../js/side-bar/load-admin-1.0.js"></script> 
        <!-- jQuery Side-bar -->    

        <script>
            $(document).ready(function () {
                $("#ventana2").modal('show');
            });
            
            function validar(){
                var file = $("#file").val();
                
                if(file === ""){
                    alert("Selecciona un archivo")
                }
                else{
                    $("#form1").submit();
                }
            }

        </script>            
    </body>
</html>
