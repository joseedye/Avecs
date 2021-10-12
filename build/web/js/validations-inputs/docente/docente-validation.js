function guardar() {
    if (validarEmpresa() && validarAsignatura() && existDates() && validarMax() && validarR()) {
        var action = $("#form1").attr("action");
        $("#form1").attr("action", action + "&estatus=1");
        $("#form1").submit();
    }
}

function enviar() {
    if (validarEmpresa() && validarAsignatura() && existDates() && validarMax() && existCronograma()) {
        var action = $("#form1").attr("action");
        $("#form1").attr("action", action + "&estatus=2");
        $("#form1").submit();
    }
}

function validarR() {
    var rowCount = $('#tabla1 tr').length;
    if (rowCount > 1) {
        return validarInputsCronograma();
    } else {
        return true;
    }

}
function existCronograma() {
    var rowCount = $('#tabla1 tr').length;
    if (rowCount <= 1) {
        $("#modal-msg").text('Se debe crear almenos 1 actividad para esta solicitud.');
        $("#ventana").modal('show');
        return false;
    } else {
        return validarInputsCronograma();
    }
}

function validarInputsCronograma() {
    var primerafecha = null;
    var flag;

    //valida inputs vacios dentro de la tabla.
    $('td input').each(function () {

        if ($(this).val() === "") {

            doInvalid($(this).attr('id'));
            flag = false;
            return flag;

        } else {

            if ($(this).attr('type') === 'date') {
                flag = validarDateCronograma($(this));

                if (!flag) {
                    return flag;
                } else {

                    if (primerafecha !== null) {
                        flag = validarDates(primerafecha, $(this));
                        primerafecha = null;
                        if (!flag) {
                            return flag;
                        }
                    } else {
                        primerafecha = $(this);
                    }

                }
            } else {

                doValid($(this).attr('id'));
                flag = true;

            }
        }

    });
    return flag;
}


function validarDateCronograma(fecha) {
    var from = fecha.val().split("-");
    var year = from[0];
    var month = from[1];
    var day = from[2];

    var f1 = $("#Fecha");
    var from1 = f1.val().split("-");
    var year1 = from1[0];
    var month1 = from1[1];
    var day1 = from1[2];

    var f2 = $("#FechaF");
    var from2 = f2.val().split("-");
    var year2 = from2[0];
    var month2 = from2[1];
    var day2 = from2[2];

    var mydate = new Date();
    mydate.setFullYear(year, month - 1, day);

    var mydate1 = new Date();
    mydate1.setFullYear(year1, month1 - 1, day1);

    var mydate2 = new Date();
    mydate2.setFullYear(year2, month2 - 1, day2);

    if (!(mydate >= mydate1 && mydate <= mydate2)) {
        $("#modal-msg").text('La Fecha debe estar dentro del rango de fechas de la solicitud!');
        $("#ventana").modal('show');
        $("#ventana").on('hidden.bs.modal', function () {
            doInvalid(fecha.attr('id'));
        });
        return false;
    } else {
        doValid(fecha.attr('id'));
        return true;
    }
}

function existDates() {
    var f1 = $("#Fecha");
    var f2 = $("#FechaF");
    return existFechaInicio() && existFechaFin() && validarDates(f1, f2);
}

function existFechaInicio() {
    var fecha = document.getElementById('Fecha').value;
    var isFecha = fecha !== "";
    if (!isFecha) {
        doInvalid("Fecha");
        return false;
    } else {
        doValid("Fecha");
        return true;
    }
}

function existFechaFin() {
    var fechaF = document.getElementById('FechaF').value;
    var isFechaF = fechaF !== "";
    if (!isFechaF) {
        doInvalid("FechaF");
        return false;
    } else {
        doValid("FechaF");
        return true;
    }
}

function validarDates(f1, f2) {
    var from = f1.val().split("-");
    var year = from[0];
    var month = from[1];
    var day = from[2];

    var from1 = f2.val().split("-");
    var year1 = from1[0];
    var month1 = from1[1];
    var day1 = from1[2];

    var mydate = new Date();
    mydate.setFullYear(year, month - 1, day);
    var mydate1 = new Date();
    mydate1.setFullYear(year1, month1 - 1, day1);
    if ((mydate - mydate1) > 0) {
        $("#modal-msg").text('La Fecha de inicio no puede ser mayor a la fecha de fin.');
        $("#ventana").modal('show');
        $("#ventana").on('hidden.bs.modal', function () {
            doInvalid(f1.attr('id'));
            doInvalid(f2.attr('id'));
        });
        return false;
    } else {
        doValid(f1.attr('id'));
        doValid(f2.attr('id'));

        return true;
    }
}

function doInvalid(idElement) {
    $("#" + idElement).addClass("no-valid");
    $("#" + idElement).focus();
}

function doValid(idElement) {
    $("#" + idElement).removeClass("no-valid");
}

function validarEmpresa() {
    var emp = document.getElementById('empresa').value;
    var isEmp = emp !== "Seleccione";
    if (!isEmp) {
        doInvalid("empresa");
        return false;
    } else {
        doValid("empresa");
        return true;
    }
}

function validarAsignatura() {
    var asig = document.getElementById('Asignatura').value;
    var isAsig = asig !== "Seleccione";
    if (!isAsig) {
        doInvalid("Asignatura");
        return false;
    } else {
        doValid("Asignatura");
        return true;
    }
}

function validarMax() {
    var max = document.getElementById('NumMax').value;
    var isMax = max !== "";
    if (!isMax) {
        doInvalid("NumMax");
        return false;
    } else {
        doValid("NumMax");
        return true;
    }
}



//elementos tabla cronograma
function createRow() {
    var nextRow = parseInt($("#count").val());
    var htmlTags = '<tr id="' + nextRow + '">' +
            '<td> <input type="text" id="actividad' + nextRow + '" name="actividad' + nextRow + '" class="form-control" placeholder="Actividad" required> </td>' +
            '<td> <textarea id="descripcion' + nextRow + '" name="descripcion' + nextRow + '" class="form-control" placeholder="Descripcion"></textarea></td>' +
            '<td> <input class="form-control" type="date" id="FechaInicio' + nextRow + '" name="FechaInicio' + nextRow + '" required> </td>' +
            '<td> <input class="form-control" type="date" id="FechaFin' + nextRow + '" name="FechaFin' + nextRow + '" required> </td>' +
            '<td> <a onmouseleave="blurRow(' + nextRow + ')" onmouseover="focusRow2(' + nextRow + ')" href="#tabla1" onclick="deleteRow(' + nextRow + ')" title="Eliminar actividad" ><i class="fas fa-trash-alt"></i></a> </td>' +
            '</tr>';
    $('#tabla1 tbody').append(htmlTags);
    $("#count").val(nextRow + 1);
    var idRowLast = $('table tr:last').attr('id');
    $("#lastRow").val(idRowLast);
}

//perfil
$(document).ready(function () {
    $("#ventana2").modal('show');
});

function validar() {
    var arry = new Array();
    arry.push($("#nombres"));
    arry.push($("#ap1"));
    arry.push($("#ap2"));
    arry.push($("#numDocumento"));
    arry.push($("#FechaNac"));
    arry.push($("#tl1"));
    arry.push($("#dir"));

    var flag = false;
    for (var i = 0; i < arry.length; i++) {
        if (arry[i].val() === "") {
            doInvalid(arry[i].attr('id'));
            flag = true;
            i = arry.length;
        } else {
            doValid(arry[i].attr('id'));
        }
    }
    if (!flag) {
        $("#submit").click();
    }

}

function numberValid(input) {
    if (input.value.length > 10) {
        input.value = this.value.substr(0, 10);
    } else if (input.value < 0) {
        input.value = '0';
    }
}

function deleteRow(row){
    $('#'+row).remove();
    
}


