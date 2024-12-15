function ReservaModal() {
    var modalForm = new bootstrap.Modal(document.getElementById('modal-reserva'));
    modalForm.toggle();
}

function PagoModal() {
    var modalForm = new bootstrap.Modal(document.getElementById('modal-PagoReserva'));
    modalForm.toggle();
}

function IncidenciaModal() {
    var modalForm = new bootstrap.Modal(document.getElementById('Modal-Incidencia'));
    modalForm.toggle();
}





function showModal() {
    document.getElementById('modal-PagoReserva').style.display = 'flex';
}

function hideModal() {
    document.getElementById('modal-PagoReserva').display = 'none';
}


function ModalMasaje() {
    var modalForm = new bootstrap.Modal(document.getElementById('Modal-Incidencia'));
    modalForm.toggle();

}


