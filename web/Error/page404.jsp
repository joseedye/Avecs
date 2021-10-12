<%@page import="DTO.Usuario"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Sidebar style  -->
        <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="/css/side-bar/style-side-bar.css">
        <script defer src="/font/solid.js"></script>
        <script defer src="/font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Error de Página</title>
        <style>
            body {
                background-image: url(/img/walpeper2.jpg);
                background-repeat: no-repeat;
                background-size: cover;
            }
            .padre {
                /*background-color: #fafafa;*/
                margin: 1rem;
                padding: 1rem;
                /*border: 2px solid #ccc;*/
                /* IMPORTANTE */
                text-align: center;
            }
            
            .color-white {
                color:white;
                text-shadow: -2px 0px 15px black;
                font-size:  75px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="padre">
            <a onclick="window.history.back();" href="#" class="btn btn-secondary btn-lg " role="button" aria-pressed="true">← Regresar</a>
            <a href="/" class="btn btn-danger btn-lg " role="button" aria-pressed="true">Iniciar Sesión</a>
        </div>
        
        <div class="padre">            
            <p class="color-white">PÁGINA NO ENCONTRADA</p>
            <p class="color-white">ERROR 404</p>
        </div>

        <!-- jQuery Side-bar -->
        <script src="/js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="/js/side-bar/popper.min.js"></script>
        <script src="/js/side-bar/bootstrap.min.js"></script>   
        <script src="/js/side-bar/load_docente_1.0.js"></script> 
        `<!-- jQuery Side-bar -->           
    </body>
</html>
