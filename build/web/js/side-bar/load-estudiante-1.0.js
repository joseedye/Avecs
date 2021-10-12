var sidebar = '<div class="sidebar-header">'+
                '<h3 style="color: #fff !important">AVECS</h3>'+
                    '<strong style="color: #fff !important">CS</strong>'+

              '  </div>'+

              '  <ul class="list-unstyled components">'+
                  '  <li id="liperfil">'+
                  '      <a href="perfil">'+
                 '           <i class="fas fa-home"></i>'+
                  '          Perfil'+
                 '       </a>'+
               '     </li>'+
                 '   <li id="lipostulacion">'+
                '        <a href="../QuerySolicitudes.do">'+
                  '          <i class="fas fa-search"></i>'+
                   '         Todas Las Visitas'+
                    '    </a>'+
                    '</li>'+
                    '<li id="livisitas" >'+
                        '<a href="../QueryVisitas.do">'+
                            '<i class="fa fa-building"></i>'+
                            '&nbsp Mis Visitas'+
                        '</a>'+
                    '</li>'+
                    '<li>'+
                     '   <a href="../LogOut.do">'+
                    '        <i class="fas fa-lock"></i>'+
                   '         Cerrar Sesión'+
                  '      </a>'+
                 '   </li> '+                   
                '</ul>';
                
function loadPerfil(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
}

function loadPostulacion(i){  
    console.log("este"+i);
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lipostulacion').className = "active";
     if(i===1){loadVisitas();} 
}

function loadVisitas(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('livisitas').className = "active";
}