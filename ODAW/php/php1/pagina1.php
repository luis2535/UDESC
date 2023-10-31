<?php
session_start();

if (isset($_POST['nome'])) {
    $_SESSION['nome'] = $_POST['nome'];
}

?>
<!DOCTYPE html>
<html>
<head>
    <title>Página 1</title>
</head>
<body>
    <h1>Bem-vindo à Página 1</h1>
    <?php if (isset($_SESSION['nome'])) : ?>
        <p>Olá, <?php echo $_SESSION['nome']; ?>!</p>
        <a href="pagina2.php">Ir para a Página 2</a>
    <?php else : ?>
        <form method="post">
            <label for="nome">Digite seu nome:</label>
            <input type="text" name="nome">
            <input type="submit" value="Enviar">
        </form>
    <?php endif; ?>
</body>
</html>
