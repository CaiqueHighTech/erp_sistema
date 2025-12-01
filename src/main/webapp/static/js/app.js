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

    // Exemplo de manipulação do DOM com jQuery
    // Adiciona uma mensagem de rodapé
    $('body').append('<div class="jquery-message">Mensagem adicionada via jQuery: O frontend está funcionando!</div>');

    // Mostra a mensagem com um efeito de fade
    $('.jquery-message').fadeIn(2000);

    // Exemplo de evento com jQuery
    $('nav a').on('click', function(e) {
        // e.preventDefault(); // Descomente para evitar a navegação
        console.log('Link de navegação clicado: ' + $(this).text());
    });
});
