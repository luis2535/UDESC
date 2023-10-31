<?php
session_start();

if (isset($_SESSION['nome'])) {
    $nome = $_SESSION['nome'];
} else {
    $nome = 'Visitante';
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Página 2</title>
</head>
<body>
    <h1>Bem-vindo à Página 2</h1>
    <p>Olá, <?php echo $nome; ?>!</p>
    <p><a href="pagina1.php">Voltar para a Página 1</a></p>
</body>
</html>
