/**
 * Arquivo JavaScript para o sistema ERP.
 * Demonstra o uso de JavaScript puro e jQuery.
 */

// JavaScript puro
document.addEventListener('DOMContentLoaded', function() {
    console.log('O DOM foi completamente carregado (JavaScript puro).');
});

// jQuery
$(document).ready(function() {
    console.log('O DOM foi completamente carregado (jQuery).');

    // Exemplo de evento com jQuery
    $('nav a').on('click', function(e) {
        // e.preventDefault(); // Descomente para evitar a navegação
        console.log('Link de navegação clicado: ' + $(this).text());
    });
});
