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
                 '   <li id="lidocumentos">'+
                '        <a href="../QueryDocuments.do">'+
                  '          <i class="fas fa-copy"></i>'+
                   '         Documentos'+
                    '    </a>'+
                    '</li>'+
                    '<li id="lisolicitud">'+
                       ' <a href="../QuerySolicitudes.do">'+
                        '    <i class="fas fa-question"></i>'+
                         '   Solicitudes'+
                       ' </a>'+
                    '</li>'+                    
                     '<li id="livisitas" >'+
                        '<a href="../QueryVisitas.do">'+
                            '<i class="fa fa-building"></i>'+
                            '&nbspVisitas'+
                        '</a>'+
                    '</li>'+
                    '<li id="lipostulantes">'+
                       ' <a href="../QueryPostulantes.do">'+
                        '    <i class="fas fa-user"></i>'+
                         '   Postulantes'+
                       ' </a>'+
                    '</li>'+
                     '  <li>  '+   
                      '  <a href="#CompaniesSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                       '     <i class="fas fa-briefcase"></i>'+
                        '    Empresas'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="CompaniesSubmenu">'+
                         '   <li id="liregistar">'+
                             '   <a href="empresa_registrar">Ingresar Nueva</a>'+
                            '</li>'+
                            '<li id="liconsultar" >'+
                                '<a href="../QueryCompanies.do">Consultar</a>'+
                           ' </li>'+
                        '</ul>'+
                    '</li>'+
                    '<li>'+
                     '   <a href="../LogOut.do">'+
                    '        <i class="fas fa-lock"></i>'+
                   '         Cerrar Sesión'+
                  '      </a>'+
                 '   </li> '+                   
                '</ul>';
                
function load(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
}

function loadDocumentos(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lidocumentos').className = "active";
   
}

function loadSolicitud(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lisolicitud').className = "active";
}

function loadVisitas(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('livisitas').className = "active";
}

function loadPostulantes(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lipostulantes').className = "active";
}

function loadRegistro(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liregistar').className = "active";
    document.getElementById('CompaniesSubmenu').className += " show";
}

function loadModificar(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liconsultar').className = "active";
    document.getElementById('CompaniesSubmenu').className += " show";
}

function focusRow(row){
    $("#" + row).css("background","#efeeee");
}

function focusRow2(row){
    $("#" + row).css("background","#dc3545");
}

function blurRow(row){
    $("#" + row).css("background","white");
}