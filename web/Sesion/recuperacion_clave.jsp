<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../css/style-login.css">

        <title>Recuperación Contraseña</title>
    </head>
    <body>  

        <form class="formulario" method="post" action="../PassRecovery.do">
            <img src="../img/ufps.png" class="card-img-top" alt="">
            <h5>Recuperación de Contraseña</h5>
            <hr/>
            <div class="contenedor">
                <div class="input-contenedor">
                    <label for="exampleInputEmail1" class="form-label">Correo electrónico</label>
                    <input type="email" placeholder="Email" class="form-control" name="user" required>
                    <br>
                </div>   
                <div  class="text-center"> 
                    <input id="idbtn" type="submit" value="Aceptar" class="btn btn-danger">
                    <a href="../index"> <input type="button" value="Cancelar" class="btn btn-secondary"> </a>  
                </div>
            </div>
        </form>

        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg != null) {
        %>
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
                        <p><%=msg%></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal success -->

        <%
            }
            request.getSession().removeAttribute("msg");
        %>

        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script> 
        <script>
            $(document).ready(function () {
                $("#ventana").modal('show');
            });
        </script>
    </body>
</html>
