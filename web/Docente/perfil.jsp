
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Sidebar style  -->
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../css/side-bar/style-side-bar.css">
        <link rel="stylesheet" href="../css/style-inputs-validation.css">
        <script defer src="../font/solid.js"></script>
        <script defer src="../font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Perfil</title>
    </head>
    <body onload="load()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">                
            </nav>
            <!-- Sidebar  -->
            <%
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
                        <div> <!-- class="img-profile" >  --> 
                            <img id="fotor" style="border-radius: 50%;" src="<%=userImg%>">                        
                        </div>
                    </div>
                </nav>

                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">
                            <form name="form1" action="../UpdateProfile.do" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Perfil Docente</h4>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">

                                        <label for="">Nombre</label>
                                        <input type="text" name="nombres" value="<%=user.get("nombres")%>" class="form-control" id="nombres" placeholder="Nombre">

                                        <label for="numDocumento">Documento</label>
                                        <input type="number" name="numDocumento" min="0" class="form-control" id="numDocumento" placeholder="Documento" value="<%=user.get("numDocumento")%>" onKeyUp="numberValid(this)">
                                        <label for="">Fecha de Nacimiento</label>
                                        <input class="form-control" type="date" name="FechaNac" id="FechaNac" value="<%=user.get("fecNacimiento") + ""%>" required>

                                    </div>
                                    <div class="form-group col-md-4">


                                        <label for="">Primer Apellido</label>
                                        <input type="text" name="ap1" class="form-control" value="<%=user.get("apellido1") + ""%>" id="ap1" placeholder="Primer Apellido">

                                        <label for="">Género</label>
                                        <select class="form-control" name="genero" id="genero">
                                            <option disabled >Seleccionar</option>
                                            <% if (user.get("genero").equals("Masculino")) {%>
                                            <option selected value="Masculino" name="masculino">Masculino</option>
                                            <option value="Femenino" name="femenino">Femenino</option>
                                            <%} else { %>
                                            <option value="Masculino" name="masculino">Masculino</option>
                                            <option selected value="Femenino" name="femenino">Femenino</option>
                                            <%}%>
                                        </select>
                                        <label for="">Teléfono</label>
                                        <input type="number" name="tl1" class="form-control" value="<%=user.get("telefono1")%>" id="tl1" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }"placeholder="Teléfono">
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="ap2" class="form-control"  value="<%=user.get("apellido2")%>" id="ap2" placeholder="Segundo Apellido">

                                        <label for="">Email</label>
                                        <input type="email" name="email" class="form-control" value="<%=user.get("email")%>" id="email" placeholder="example@ufps.edu.co" disabled>

                                        <label for="">Teléfono 2</label>
                                        <input type="number" name="tl2" class="form-control" value="<%=user.get("telefono2")%>" id="tl2" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" id="" placeholder="Teléfono">
                                    </div>

                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="">Dirección</label>
                                        <input type="text" name="direccion" class="form-control" value="<%=user.get("direccion")%>" id="dir" placeholder="Dirrección">

                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="">Codigo</label>
                                        <input type="number" name="codigo" class="form-control" value="<%=request.getSession().getAttribute("codigo")%>" id="codigo"  disabled>

                                    </div>
                                </div>
                                <br>
                                <a href='#' type="button" class="btn btn-primary" onclick="validar()" >Actualizar</a>
                                <a style="display: none;" id="submit" href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="perfil" class="btn btn-primary">Cancelar</a>

                                <!-- Modal validation -->                                  
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
                                                <p>Esta seguro de modificar sus datos personales?</p>
                                            </div>
                                            <div class="modal-footer">            
                                                <button type="submit" class="btn btn-primary">Si</button>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Modal validation -->

                            </form>

                            <br>
                            <form  name="form2" action="../UpdatePassword.do" method="post">
                                <div class="form-row changPassword">
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña anterior</label>
                                        <input type="password" name="ContraAnt" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña nueva</label>
                                        <input type="password" name="ContraNueva" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label>&nbsp;</label><br>
                                        <button type="submit" name="Cambiar" class="btn btn-primary">Cambiar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
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
    <script src="../js/validations-inputs/docente/docente-validation.js"></script> 
</body>
</html>
