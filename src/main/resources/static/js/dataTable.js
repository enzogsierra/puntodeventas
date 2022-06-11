// Dar funciones a la tabla de productos
$(document).ready(function() 
{
    $('#tablaProductos').DataTable(
    {
        order: [[3, "asc"]],
        columns:
        [
            null,
            {orderable: false, bSearchable: false},
            null,
            null,
            null,
            {bSearchable: false},
            {orderable: false}
        ],
        lengthMenu: [5, 10, 25, 50],
        language:
        {
            "search": "Buscar",
            "lengthMenu": "Mostrar _MENU_ productos",
            "info": "Mostrando _END_ productos (_TOTAL_ total)",
            "infoFiltered": "(Filtrado de _MAX_ productos)",
            "emptyTable": "Aún no hay productos creados",
            "paginate":
            {
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });
});