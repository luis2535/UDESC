<!DOCTYPE html>
<html>
<head>
    <title>Bem-vindo</title>
</head>
<body>
    <h2>Bem-vindo</h2>
    <?php
    if (isset($_GET['nome'])) {
        $nome = $_GET['nome'];
        echo "<p>Você fez login com sucesso, $nome!</p>";
    } else {
        echo "<p>Você fez login com sucesso!</p>";
    }
    ?>
</body>
</html>
