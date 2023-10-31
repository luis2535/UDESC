<?php
$arquivoContador = 'contador.txt';

if (file_exists($arquivoContador)) {
    $contador = (int) file_get_contents($arquivoContador);
    $contador++;
} else {
    $contador = 1;
}

file_put_contents($arquivoContador, $contador);

echo "Esta página foi visitada $contador vezes.";
?>
