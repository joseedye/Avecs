
<%@page import="java.util.HashMap"%>
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
                Map<String, Object> listPostulantes = (Map<String, Object>) request.getSession().getAttribute("postulantes");

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
                    <div class="form-group col-md-12">

                        <div class="card my-4">
                            <h4 class="card-header">Administración de Postulantes</h4>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <table class="table table-responsive-sm">

                                            <thead>
                                                <tr>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Código Estudiante </th>
                                                    <th scope="col">Fecha </th>
                                                    <th scope="col">Empresa </th>
                                                    <th scope="col">Temática </th>
                                                    <th scope="col"><i class="fas fa-user-check"></i> </th>
                                                    <th scope="col"><i class="fas fa-user-times"></i></th>
                                                    <th></th>
                                                </tr>
                                            </thead>


                                            <tbody>

                                                <%
                                                    int i = 1;
                                                    for (Map.Entry<String, Object> entry : listPostulantes.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr id="<%=i%>">
                                                    <td><%=map.get("estudianteNombre")%></td>
                                                    <td><%=map.get("estudianteCodigo")%></td>
                                                    <td><%=map.get("fechaPostulante")%></td>
                                                    <td><%=map.get("empresaSolicitud")%></td>
                                                    <td><%=map.get("tematicaSolicitud")%></td>

                                                    <td>
                                                        <div class="custom-control custom-checkbox">
                                                            <input type="checkbox" name="accept<%=i%>" class="custom-control-input" <%if (map.get("estatusPostulante").equals("2")) {%> checked <%}%>>
                                                            <label onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow(<%=i%>)" onclick="loadCheck(<%=map.get("idPostulante")%>, '2')" class="custom-control-label" name="accept<%=i%>" for="accept<%=i%>"></label>
                                                        </div>
                                                    </td> 
                                                    <td> 
                                                        <div class="custom-control custom-checkbox">
                                                            <input type="checkbox" name="refuse<%=i%>" class="custom-control-input" <%if (map.get("estatusPostulante").equals("3")) {%> checked <%}%>>
                                                            <label onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow2(<%=i%>)" onclick="refuse(<%=map.get("idPostulante")%>, '3',<%=map.get("observacion")%>)" class="custom-control-label" name="refuse<%=i%>" for="refuse<%=i%>"></label>
                                                        </div>
                                                    </td> 
                                                    <td>
                                                        <a onmouseleave="blurRow(<%=i%>)" onmouseover="focusRow(<%=i%>)" href="../QueryDataStudent.do?idestudiante=<%=map.get("idEstudiante")%>&idPostulante=<%=map.get("idPostulante")%>" class="modal-btn"  title="Ver" ><i class="fas fa-search-plus"></i></a>
                                                    </td> 
                                                </tr>
                                                <% i++;
                                    }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content  -->

                        <!-- Modal success -->                        
                        <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Mensaje</h5>                        
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="modal-msg"></p>
                                    </div>
                                    <textarea id="msg-motivo"></textarea>
                                    <div class="modal-footer">            
                                        <a style="color:white" id="btn-accept" onclick="loadCheck2()" class="btn btn-primary">Aceptar</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal success -->

        <a id="postulante-current"></a>
        <a id="status-current"></a>


        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script>   
        <script src="../js/side-bar/load_docente_1.0.js"></script> 
        <!-- jQuery Side-bar -->    

        <script>

                            function refuse(idPostulante, status, observacion) {
                                $("#postulante-current").val(idPostulante);
                                $("#status-current").val(status);

                                $("#modal-msg").text('Ingresar motivo por el cual no fue aceptado el postulante.');
                                $("#msg-motivo").css("display", "initial");
                                $("#msg-motivo").val(observacion);
                                $("#btn-accept").css("display", "initial");
                                $("#ventana").modal('show');
                            }

                            $(document).ready(function () {
                                $("#msg-motivo").css("display", "none");
                                $("#btn-accept").css("display", "none");
                                $("#modal-msg").text('Ingresar motivo de No aceptación.');
                                var msg = '<%=request.getSession().getAttribute("msg")%>';

                                if (msg !== 'null') {
                                    $("#modal-msg").text(msg);
                                    $("#ventana").modal('show');
            <%
                            request.getSession().removeAttribute("msg");
            %>
                                }
                                msg = 'null';
                            });

                            function loadCheck(a, b) {
                                location.href = '../UpdateStatusPostulant.do?idPostulante=' + a + '&estatus=' + b + "&observacion=";
                            }

                            function loadCheck2() {
                                var a = $("#postulante-current").val();
                                var b = $("#status-current").val();
                                var msg = $("#msg-motivo").val();
                                location.href = '../UpdateStatusPostulant.do?idPostulante=' + a + '&estatus=' + b + "&observacion=" + msg;
                            }

                            if (window.performance.navigation.type === 1) {
                                location.href = "../QueryPostulantes.do";
                            }
        </script>            
    </body>
</html>