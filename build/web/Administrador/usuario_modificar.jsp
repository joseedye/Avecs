
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
        <title>Modificar Usuario</title>
    </head>
    <body onload="loadUsuarios()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> usuario = (Map<String, String>) request.getSession().getAttribute("usuario");
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
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
                <div style="justify-content:center;" class="form-row ">                    
                    <div class="form-group col-md-10">
                        <form name="form1" action="../UpdateUser.do?idUsuario=<%=request.getParameterValues("idUsuario")[0]%>" method="post" >
                            <h4>Información de usuario</h4><br>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="">Nombre</label>
                                    <input type="text" name="nombres" value= "<%=usuario.get("nombres")%>"class="form-control" maxlength="100" id="" placeholder="Nombre" required>

                                    <label for="">Documento</label>
                                    <input type="number" value= "<%=usuario.get("numDocumento")%>" name="numDocumento" pattern="[0-9]+" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }" class="form-control" id="" placeholder="Documento"
                                           required>
                                    <label for="Fecha">Fecha de Nacimiento</label>
                                    <input class="form-control" name="fecNacimiento" value= "<%=usuario.get("fecNacimiento")%>" type="date" name="fecha" required>
                                </div>

                                <div class="form-group col-md-4">
                                    <label for="">Primer Apellido</label>
                                    <input type="text" name="ap1" value= "<%=usuario.get("apellido1")%>"  class="form-control" id="" maxleng="15" placeholder="Primer Apellido" required>

                                    <label for="">Genero</label>
                                    <select class="form-control" name="genero">
                                        <option disabled >Seleccionar</option>
                                        <% if (usuario.get("genero").equals("Masculino")) {%>
                                        <option selected value="Masculino" name="masculino">Masculino</option>
                                        <option value="Femenino" name="femenino">Femenino</option>
                                        <%} else { %>
                                        <option value="Masculino" name="masculino">Masculino</option>
                                        <option selected value="Femenino" name="femenino">Femenino</option>
                                        <%}%>
                                    </select>

                                    <label for="">Teléfono</label>
                                    <input type="number" name="tl1" value= "<%=usuario.get("telefono1")%>" pattern="[0-9]+" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }" placeholder="Teléfono" required>
                                </div>

                                <div class="form-group col-md-4">
                                    <label for="">Segundo Apellido</label>
                                    <input type="text" class="form-control" value= "<%=usuario.get("apellido2")%>" name="ap2" id="" maxlength="15" placeholder="Primer Apellido">

                                    <label for="">Email</label>
                                    <input type="email" value= "<%=usuario.get("email")%>" pattern="+.@ufps.edu.co" name="email" class="form-control" id="email" maxlength="70"
                                           placeholder="@ufps.edu.co" required>

                                    <label for="">Teléfono 2</label>
                                    <input type="number" pattern="[0-9]+" value= "<%=usuario.get("telefono2")%>" name="tl2" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }"  placeholder="Teléfono">
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="Dire">Dirección</label>
                                    <input type="text" class="form-control" value= "<%=usuario.get("direccion")%>" name="direccion" placeholder="Dirrección" maxlength="150" required>
                                </div>
                            </div>

                            <div>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="usuario_consulta"><input type="button" name="cancelar" value="Cancelar" class="btn btn-primary"></a>
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
                                            <p>Esta seguro de modificar los datos del usuario?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit" onclick="validarEmail()" class="btn btn-primary">Si</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal  -->
                        </form>
                    </div>
                </div>
            </div>
            <!-- Content  -->
        </div>
        <!-- Page Content  -->

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
        <!-- Modal success -->

        <%
            } request.getSession().removeAttribute("msg");
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
            
            function validarEmail() {
                var cor = document.getElementById('email').value;
                if (/^\w+([\.-]?\w+)*@ufps.edu.co/.test(cor)) {
                    document.form1.submit();
                } else {
                    alert("La dirección de email " + cor + " no es correcta.");
                }
            }
        </script>
    </body>
</html>
