
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
        <title> Registro Usuario</title>
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
                        <form name="form1" action="../RegisterUser.do" method="post">
                            <h4>Registro de Usuario</h4>

                            <div class="form-row">
                                <div class="form-group col-md-4">

                                    <label for="">Nombre</label>
                                    <input type="text" name="Nom" class="form-control" id="" placeholder="Nombre" required>

                                    <label for="">Documento</label>
                                    <input type="number" name="Doc" min="0" class="form-control" id="" placeholder="Documento" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);} else if (this.value < 0) {
                                                    this.value = '0'; }" required>

                                    <label for="fecha">Fecha de Nacimiento</label>
                                    <input class="form-control" name="Fecha" type="date" id="fecha" name="fecha" value="" required>

                                </div>
                                <div class="form-group col-md-4">
                                    <label for="">Primer Apellido</label>
                                    <input type="text" name="Ape1" class="form-control" id="" placeholder="Primer Apellido" required>

                                    <label for="">Género</label>
                                    <select class="form-control" name="Genero" id="genero" required>
                                        <option disabled selected norequired>Seleccione</option>
                                        <option value="Masculino" name="masculino">Masculino</option>
                                        <option value="Femenino" name="femenino">Femenino</option>
                                    </select>
                                    <label for="">Teléfono</label>
                                    <input type="number" name="Tel1" min="0" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);} else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono" required>
                                </div>

                                <div class="form-group col-md-4">
                                    <label for="">Segundo Apellido</label>
                                    <input type="text" class="form-control" name="Ape2" id="" placeholder="Segundo Apellido" required>

                                    <label for="">Email</label>
                                    <input type="email" pattern="+.@ufps.edu.co" name="Email" class="form-control" id="Email"
                                           placeholder="@ufps.edu.co" required>

                                    <label for="">Teléfono 2</label>
                                    <input type="number" min="0" name="Tel2" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);} else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono">
                                </div>

                            </div>

                            <label for="direccion">Dirección</label>
                            <input type="text" class="form-control" name="Dire" id="direccion" placeholder="Dirrección" required>

                            <br>       
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="selectTipo">Indicar tipo de usuario:</label>
                                    <select onchange="selectDocente(this.value)" class="form-control" name="TipoUsu" id="selectTipo" required>
                                        <option value="1">Administrador</option>
                                        <option value="2">Docente</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">   
                                    <label style="display:none;" id="lblcodigo" for="direccion">Código Docente</label>
                                    <input style="display:none;" type="number" class="form-control" name="codigo" id="codigo" placeholder="Código">
                                </div>
                                <div class="form-group col-md-2">   
                                </div>
                                <div class="form-gruop col-md-6 ">
                                    <div class="form-group ">
                                        <a onclick="validar()" href="#" class="btn btn-primary" >Registrar</a>
                                        <a id="submitbtn" style="display:none;" href="#ventana" data-toggle="modal"></a>
                                        <a href="usuario_registrar" class="btn btn-primary">Cancelar</a>
                                    </div>

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
                                            <p>Estas seguro de registrar el nuevo usuario?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit"  class="btn btn-primary">Si</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal  -->
                        </form>
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
        <script src="../js/side-bar/load-admin-1.0.js"></script> 
        <!-- jQuery Side-bar -->    

        <script>
            $(document).ready(function () {
                $("#ventana2").modal('show');
            });

            function validar() {
                var cor = document.getElementById('Email').value;
                var gen = document.getElementById('genero').value;
                
                var isGenero = gen !== "Seleccione";
                var isEmail = /^\w+([\.-]?\w+)*@ufps.edu.co/.test(cor);
                
                if(!isGenero){
                    alert("Seleccionar un género!");
                }
                else if(!isEmail){
                    alert("La dirección de email " + cor + " no es correcta.");
                }
                else{
                    document.getElementById('submitbtn').click();
                }
            }
            
            function selectDocente(idOption){
                if(idOption == 2){
                    $("#codigo").css("display","initial");
                    $("#lblcodigo").css("display","initial");
                    $("#codigo").prop('required',true);
                }
                else{
                    $("#codigo").css("display","none");
                    $("#lblcodigo").css("display","none");
                    $("#codigo").prop('required',false);
                    $("#codigo").val("");
                }                
            }

        </script>
    </body>
</html>
