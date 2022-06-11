document.addEventListener("DOMContentLoaded", () =>
{
    deleteProductHanlder();
});


// Boton de eliminar (archivar) producto
function deleteProductHanlder()
{
    const btn = document.querySelector("a#deleteProductBtn");
    if(!btn) return;

    btn.addEventListener("click", e =>
    {
        if(confirm("Estás por archivar este producto, ¿quieres continuar?")) return 1;
        else e.preventDefault();
    });
}
