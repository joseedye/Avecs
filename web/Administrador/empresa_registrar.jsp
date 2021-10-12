
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <title>Registro Empresa</title>
    </head>
    <body onload="loadEmpresas()">
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
                        <form action="../RegisterCompany.do" method="post">
                            <h4>Registro de Empresa </h4>

                            <div class="form-row">
                                <div class="form-group col-md-8">

                                    <label for="razon">Razón social</label>
                                    <input type="text" class="form-control" id="razon" placeholder="Razon social" name="razon" required>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="Departamento">Departamento:</label>
                                            <select class="form-control" name="Departamento" id="Departamento">
                                                <option disabled selected>Seleccione</option>
                                                <option value="Amazonas">Amazonas</option>
                                                <option value="Antioquia">Antioquia</option>
                                                <option value="Arauca">Arauca</option>
                                                <option value="Atlántico">Atlántico</option>
                                                <option value="Bolívar">Bolívar</option>
                                                <option value="Boyacá">Boyacá</option>
                                                <option value="Caldas">Caldas</option>
                                                <option value="Caquetá">Caquetá</option>
                                                <option value="Casanare">Casanare</option>
                                                <option value="Cauca">Cauca</option>
                                                <option value="Cesar">Cesar</option>
                                                <option value="Chocó">Chocó</option>
                                                <option value="Córdoba">Córdoba</option>
                                                <option value="Cundinamarca">Cundinamarca</option>
                                                <option value="Guainía">Guainía</option>
                                                <option value="Guaviare">Guaviare</option>
                                                <option value="Huila">Huila</option>
                                                <option value="La Guajira">La Guajira</option>
                                                <option value="Magdalena">Magdalena</option>
                                                <option value="Meta">Meta</option>
                                                <option value="Nariño">Nariño</option>
                                                <option value="Norte de Santander">Norte de Santander</option>
                                                <option value="Putumayo">Putumayo</option>
                                                <option value="Quindío">Quindío</option>
                                                <option value="Risaralda">Risaralda</option>
                                                <option value="San Andrés y Providencia">San Andrés y Providencia</option>
                                                <option value="Santander">Santander</option>
                                                <option value="Sucre">Sucre</option>
                                                <option value="Tolima">Tolima</option>
                                                <option value="Valle del Cauca">Valle del Cauca</option>
                                                <option value="Vaupés">Vaupés</option>
                                                <option value="Vichada">Vichada</option>
                                            </select>
                                            <label for="telefono">Teléfono 1</label>
                                            <input type="number" name="Tel1" pattern="[0-9]+" class="form-control" id="telefono" onKeyUp="if (this.value.length > 10) {
                                                        this.value = this.value.substr(0, 10);
                                                    } else if (this.value < 0) {
                                                        this.value = '0';
                                                    }" placeholder="3110000000" required>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="ciudad">Ciudad</label>
                                            <input type="text" name="Ciudad" class="form-control"  id="ciudad" placeholder="Ciudad" required>
                                            <label for="telefono2">Teléfono 2</label>
                                            <input type="number" pattern="[0-9]+" name="Tel2" class="form-control" id="telefono2" onKeyUp="if (this.value.length > 10) {
                                                        this.value = this.value.substr(0, 10);
                                                    } else if (this.value < 0) {
                                                        this.value = '0';
                                                    }" placeholder="3110000000">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-md-4">

                                    <label for="nit">NIT</label>
                                    <input type="number" class="form-control" name="Nit" id="nit" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }" placeholder="NIT" required>

                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" name="Email" id="email" maxlength="70" placeholder="Email" required>
                                </div>
                            </div>

                            <label for="direccion">Dirección</label>
                            <input type="text" class="form-control" id="direccion" name="Direccion" placeholder="Dirrección" required>
                            <hr/>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="PerCont">Persona contacto</label>
                                    <input type="text" class="form-control" id="PerCont" name="PerCont" placeholder="Persona contacto" >
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="TelCont">Teléfono contacto</label>
                                    <input type="number" pattern="[0-9]+" class="form-control" name="TelCont" id="TelCont" placeholder="Teléfono contacto">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="EmailCont">Email contacto</label>
                                    <input type="email" class="form-control" id="EmailCont" name="EmailCont" maxlength="70" placeholder="Email contacto">
                                </div>
                            </div>
                            <a onclick="validar()" href="#" class="btn btn-primary" >Registrar</a>
                            <a id="submitbtn" style="display:none;" href="#ventana" data-toggle="modal"></a>  
                            <a class="btn btn-primary" href="empresa_registrar">Cancelar</a>

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
                                            <p>Estas seguro de registrar la empresa?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit" class="btn btn-primary">Si</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal  -->


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

                            <%   } request.getSession().removeAttribute("msg");
                            %>

                        </form>
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
        <script src="../js/side-bar/load-admin-1.0.js"></script> 
        <!-- jQuery Side-bar --> 

        <script>
            $(document).ready(function () {
                $("#ventana2").modal('show');
            });
            
            function validar() {
                var gen = document.getElementById('Departamento').value;
                
                var isDep = gen !== "Seleccione";
                
                if(!isDep){
                    alert("Seleccionar un departamento!");
                }                
                else{
                    document.getElementById('submitbtn').click();
                }
            }

            function tab(e) {
                if (e.which === 13) {
                    var nextSibling = e.target.nextSibling.nextSibling;
                    if (nextSibling) {
                        nextSibling.focus();
                    }
                    /* INICIO EDICION*/
                    else {
                        inputs[0].focus();
                    }
                    /*FIN EDICION*/
                    e.preventDefault();
                }
            }
            var inputs = document.getElementsByTagName('input');
            for (var x = 0; x < inputs.length; x++) {
                inputs[x].addEventListener('keypress', tab);
            }

        </script> 
               
    </body>
</html>
