$(document).ready(function()
{
    $("#buscar_producto").autocomplete(
    {
        source: function(req, res)
        {
            $.ajax(
            {
                url: `/ventas/buscar/${req.term}`,
                dataType: "json",
                data: {term: req.term},
                success: function(data)
                {
                    res($.map(data, function(item)
                    {
                        return {
                            value: item.id,
                            label: `${item.descripcion} - $${item.precio}`,
                            descripcion: item.descripcion,
                            precio: item.precio
                        };
                    }));
                }
            });
        },
        select: function(e, ui)
        {
            let linea = $("#ventaItems").html();

            let id = ui.item.value;
            let descripcion = ui.item.descripcion;
            let precio = ui.item.precio;

            // Verificar si el producto ya está añadido a la tabla
            if(utils.isRepeated(id))
            {
                utils.updateAmount(id, precio);
                return false;
            }
            
            // Añadir producto a la tabla
            linea = linea.replace(/{ID}/g, id);
            linea = linea.replace(/{DESCRIPCION}/g, descripcion);
            linea = linea.replace(/{PRECIO}/g, precio);

            $("#tabla tbody").append(linea);

            utils.subtotal(id, precio, 1);
        }
    });
});


const utils =
{
    total: function()
    {
        let total = 0.0;
        $("span[id^='subtotal_']").each(function()
        {
            total += parseFloat($(this).html());
        });

        $("#total").html(parseFloat(total).toFixed(2));
    },
    subtotal: function(id, precio, cantidad)
    {
        $(`#subtotal_${id}`).html(parseFloat(precio) * parseInt(cantidad).toFixed(2));

        this.total();
    },
    updateAmount: function(id, precio) 
    {
        let cantidad = parseInt($(`#cantidad_${id}`).val());
        $(`#cantidad_${id}`).val(++cantidad);

        this.subtotal(id, precio, cantidad);
    },
    isRepeated: function(id)
    {
        let result = false;
        
        $("input[name='item_id[]']").each(function()
        {
            if(parseInt(id) === parseInt($(this).val()))
            {
                result = true;
            }
        });

        return result;
    },
    deleteRow: function(id)
    {
        $(`#row_${id}`).remove();

        this.total();
    }
}