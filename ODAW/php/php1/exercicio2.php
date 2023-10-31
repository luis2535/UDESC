<?php
function ordenaPalavras($listadepalavras) {
    $palavras = explode(', ', $listadepalavras);


    sort($palavras);

    echo "Palavras em ordem alfabética: " . implode(', ', $palavras);
}

$listadepalavras = "html, css, javascript, php, sql, ruby, function, abacate";

ordenaPalavras($listadepalavras);
?>
