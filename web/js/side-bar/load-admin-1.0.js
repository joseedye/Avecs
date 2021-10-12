var sidebar =   '<div class="sidebar-header">'+
                    '<h3 style="color: #fff !important">AVECS</h3>'+
                    '<strong style="color: #fff !important">CS</strong>'+
                '</div>'+

                '<ul class="list-unstyled components">'+
                    '<li id="liperfil" >'+
                        '<a href="perfil">'+
                            '<i class="fas fa-home"></i>'+
                            '&nbspPerfil'+
                        '</a>'+
                    '</li>'+
                    '<li id="lidocumentos" >'+                        
                        '<a href="#documentsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-copy"></i>'+
                            '&nbspDocumentos'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="documentsSubmenu">'+
                            '<li>'+
                                '<a href="../QueryTypeDocuments.do">Nuevo</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="../QueryDocuments.do">Consultar</a>'+
                            '</li>'+
                        '</ul>'+
                    '</li>'+
                    '<li id="liusuarios" >'+                        
                        '<a href="#usersSubMenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-users"></i>'+
                            '&nbspUsuarios'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="usersSubMenu">'+
                            '<li>'+
                                '<a href="usuario_registrar">Registrar Usuario</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="estudiantes_registrar">Registro Estudiantes</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="../QueryUsers.do">Consultar</a>'+
                            '</li>'+                            
                        '</ul>'+
                    '</li>'+
                    '<li id="lisolicitudes" >'+
                        '<a href="../QuerySolicitudes.do">'+
                            '<i class="fas fa-question"></i>'+
                            '&nbspSolicitudes'+
                        '</a>'+
                    '</li>'+
                     '<li id="livisitas" >'+
                        '<a href="../QueryVisitas.do">'+
                            '<i class="fa fa-building"></i>'+
                            '&nbspVisitas'+
                        '</a>'+
                    '</li>'+
                    '<li id="liempresas" >'+                        
                        '<a href="#CompaniesSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-briefcase"></i>'+
                            '&nbspEmpresas'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="CompaniesSubmenu">'+
                            '<li>'+
                                '<a href="empresa_registrar">Registrar Empresa</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="../QueryCompanies.do">Consultar</a>'+
                            '</li>'+
                        '</ul>'+
                    '</li>'+
                    '<li>'+
                        '<a href="../LogOut.do">'+
                            '<i class="fas fa-lock">'+'</i>'+
                            '&nbspCerrar Sesión'+
                        '</a>'+
                    '</li>'+                    
                '</ul>';


function loadPerfil(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
    document.getElementsByTagName('a')[0].href = "#";
}

function loadDocumentos(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lidocumentos').className = "active";
    document.getElementById('documentsSubmenu').className += " show";
}

function loadUsuarios(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liusuarios').className = "active";
    document.getElementById('usersSubMenu').className += " show";
}

function loadSolicitudes(i){ 
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lisolicitudes').className = "active";
  
    if(i===1){
        loadVisitas();
    }
}

function loadVisitas(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('livisitas').className = "active";
}

function loadEmpresas(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liempresas').className = "active";
    document.getElementById('CompaniesSubmenu').className += " show";
}




