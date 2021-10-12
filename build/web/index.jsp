<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        

        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/style-login.css">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="400000881726-m4mdhc93k07qp2u6c0n50mafq5h2knlh.apps.googleusercontent.com">

        <title>Login </title>
    </head>
    <body>          

        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="card" style="width: 23rem;">
                    <img src="img/ufps.png" class="card-img-top" alt="">

                    <form class="" method="post" action="SignIn.do">
                        <div class="contenedor">
                            <div class="input-contenedor">

                                <label for="exampleInputEmail1" class="form-label">Correo electrónico</label>
                                <input type="email" placeholder="Email" class="form-control" name="user"  required>

                                <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                                <input type="password" placeholder="Contraseña" class="form-control" name="pass" required>
                            </div>

                            <input id="idbtn" type="submit" value="Iniciar sesión" class="button">
                            <div  class="text-center"> 
                                <a href="Sesion/recuperacion_clave">¿Olvidaste tu clave?</a>
                            </div>
                            <hr/>
                            <div id="gSignIn"></div>  
                            <div class="g-signin2 text-center" data-onFailure="fail" data-onsuccess="onSignIn"></div>
                        </div>
                    </form>                 
                </div>
            </div>
        </div>          
        
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
        <!-- Modal  -->

        <%
            }
            request.getSession().removeAttribute("msg");
        %>
        <script src="js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="js/side-bar/bootstrap.min.js"></script> 
        <script src="js/google-login.js"></script>  
    </body>
</html>
