<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../css/style-login.css">
        
        <title>Cambio de Contraseña</title>
    </head>
    <body>     
        <%  String token = request.getParameterValues("token")[0];
            request.getSession(true).setAttribute("token", token);
        %>
        <form name="form1" class="formulario" method="post" action="../PassChange.do">
            <h3>Cambio de Contraseña</h3>
            
            <div class="contenedor">
                <div class="input-contenedor">
                    <input type="password" placeholder="Contraseña" id="pass" name="pass" required>
                    <br> <br>   
                    <input type="password" placeholder="Repetir Contraseña" id="pass2" name="pass2" required>
                </div>            
                <input type="button" value="Aceptar" class="button" onclick="comprobarClave()" >
                <br><br>
                <a href="../index"> <input type="button" value="Cancelar" class="btn-return"> </a>               
            </div>
        </form>
        
        <script>
            function comprobarClave(){  
                var cl1 = document.getElementById("pass").value;
                var cl2 = document.getElementById("pass2").value;
                
                if(cl1==="" || cl2===""){
                    alert("Campos vacíos, verificar.");
                }
                else if(cl1 !== cl2){
                    alert("Contraseñas diferentes");
                }               
                else{
                    document.form1.submit();
                }
            }
            
        </script>
    </body>
</html>
