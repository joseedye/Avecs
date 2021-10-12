
<%@page import="Util.Utileria"%>
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
        <title>Modificar empresa</title>
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
                Map<String, String> empresa = (Map<String, String>) request.getSession().getAttribute("empresa");
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
                        <form action="../UpdateCompany.do?idEmpresa=<%=request.getParameterValues("idEmpresa")[0]%>" method="post" >
                            <h4>Información de Empresa</h4><br>

                            <div class="form-row">
                                <div class="form-group col-md-8">

                                    <label for="">Razon social</label>
                                    <input type="text" class="form-control" id="" value="<%=empresa.get("nombreEmpresa")%>" name="razon" placeholder="Razon social">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="exampleFormControlSelect1">Departamento:</label>
                                            <select class="form-control" name="departamento" value="<%=empresa.get("dptoEmpresa")%>">
                                                <option disabled >Seleccione</option>
                                                <option selected value="<%=empresa.get("dptoEmpresa")%>" > <%=empresa.get("dptoEmpresa")%></option>
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
                                            <label for="">Teléfono 1</label>
                                            <input type="number" value="<%=empresa.get("telefono1Empresa")%>" name="tl1" pattern="[0-9]+" class="form-control" onKeyUp="if (this.value.length > 10) {
                                                        this.value = this.value.substr(0, 10);
                                                    } else if (this.value < 0) {
                                                        this.value = '0';
                                                    }"  id="" placeholder="3110000000" required>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="">Ciudad</label>
                                            <input type="text" name="ciudad" value="<%=empresa.get("ciudadEmpresa")%>" class="form-control" id="" placeholder="Ciudad" required>
                                            <label for="">Teléfono 2</label>
                                            <input type="number" pattern="[0-9]+" name="tl2" value="<%=empresa.get("telefono2Empresa")%>" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                        this.value = this.value.substr(0, 10);
                                                    } else if (this.value < 0) {
                                                        this.value = '0';
                                                    }"  placeholder="3110000000" >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-md-4">

                                    <label for="">NIT</label>
                                    <input type="number" class="form-control" value="<%=empresa.get("nitEmpresa")%>" name="nit" id="" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);} else if (this.value < 0) {
                                                this.value = '0';
                                            }"  placeholder="NIT" required>

                                    <label for="">Email</label>
                                    <input type="email" class="form-control" value="<%=empresa.get("emailEmpresa")%>" name="email" id="" placeholder="Email" required>
                                </div>

                                <label for="">Dirección</label>
                                <input type="text" class="form-control" value="<%=empresa.get("direccionEmpresa")%>" id="" name="direccion" placeholder="Dirrección" required>
                                <hr />
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="">Persona contacto</label>
                                        <input type="text" class="form-control" id="" value="<%=empresa.get("personaContacto")%>" name="persona" placeholder="Persona contacto">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="">Teléfono contacto</label>
                                        <input type="number" pattern="[0-9]+" class="form-control" value="<%=empresa.get("telPersonaContacto")%>" name="telefono" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);} else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono contacto" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="">Email contacto</label>
                                        <input type="email" class="form-control" id="" value="<%=empresa.get("emailPersonaContacto")%>" name="email2" placeholder="Email contacto" required>
                                    </div>
                                    <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a> &nbsp;
                                    <a href="empresa_consulta"><input type="button" name="cancelar" value="Cancelar" class="btn btn-primary"></a>   
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
                                            <p>Esta seguro de modificar los datos de la empresa?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit" class="btn btn-primary">Si</button>
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
        </script>
    </body>
</html>
