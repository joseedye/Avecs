
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
        <title>Usuarios</title>
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
                Map<String, Object> listUsuarios = (Map<String, Object>) request.getSession().getAttribute("usuarios");
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
                            <h5 class="card-header">Administración de usuarios</h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <table class="table table-responsive-sm">

                                            <thead>
                                                <tr>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Correo electrónico (Usuario) </th>
                                                    <th scope="col">Activo</th>
                                                    <th scope="col"></th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>


                                            <tbody>

                                                <%
                                                    int i = 1;
                                                    for (Map.Entry<String, Object> entry : listUsuarios.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr>
                                                    <td><%=map.get("nombres")%></td>
                                                    <td><%=map.get("user")%></td>

                                                    <td>
                                                        <div class="custom-control custom-checkbox">
                                                            <input type="checkbox" name="activo<%=i%>" class="custom-control-input" <%if (map.get("activo").equals("true")) {%> checked <%}%>>
                                                            <label onclick="loadCheck(<%=map.get("idUsuario")%>,<%=map.get("activo")%>)" class="custom-control-label" name="activo<%=i%>" for="activo<%=i%>"></label>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a href="../UpdateUser.do?idUserQuery=<%=map.get("idUsuario")%>" title="Modificar"><i class="fas fa-pencil-alt"></i></a>
                                                    </td>

                                                    <td>
                                                        <a href="#ventana" class="modal-btn" data-some-id="<%=map.get("idUsuario")%>" title="Eliminar" data-toggle="modal"><i class="fas fa-trash-alt"></i></a>
                                                    </td>   
                                                </tr>
                                                <% i++;
                                        }%>
                                            </tbody>
                                        </table>

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
                                                        <p>Estas seguro de eliminar el usuario del sistema?</p>
                                                    </div>
                                                    <div class="modal-footer">            
                                                        <a  class="btn btn-primary">Si</a>
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
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

            $(function () {
                $(".modal-btn").click(function () {
                    var id = $(this).data('some-id');
                    $(".modal-footer a").attr("href", "../DeleteUser.do?idUsuario=" + id);
                })
            });

            function loadCheck(a, b) {
                location.href = '../UpdateActivUser.do?idUsuario=' + a + '&activo=' + b;
            }
        </script>            
    </body>
</html>
