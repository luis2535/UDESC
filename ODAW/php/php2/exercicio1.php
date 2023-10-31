<html>
<head>
    <title>Resultado do Formulário</title>
</head>
<body>

<?php
$errors = array();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nome = $_POST['nome'];
    $senha = $_POST['senha'];
    $email = $_POST['email'];


    if (empty($nome)) {
        $errors[] = "O campo Nome é obrigatório.";
    }

    if (empty($senha)){
        $errors[] = "O campo Senha é obrigatório.";
    }elseif (strlen($senha) < 6) {
        $errors[] = "A Senha deve conter pelo menos 6 caracteres.";
    }
    if (empty($email)) {
        $errors[] = "O campo Email é obrigatório.";
        
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $errors[] = "O Email inserido não é válido.";
    }

    if (!isset($_POST['java']) && !isset($_POST['python']) && !isset($_POST['c'])) {
        $errors[] = "Selecione pelo menos uma linguagem de programação.";
    }

    if (empty($_POST['genero'])) {
        $errors[] = "Selecione o gênero.";
    }

    // Verificar se o campo Estado Civil foi selecionado
    if (empty($_POST['estado_civil'])) {
        $errors[] = "Selecione o estado civil.";
    }

    if (!empty($errors)) {
        echo "<h2>Erros no formulário:</h2>";
        foreach ($errors as $error) {
            echo "<p style='color: red;'>$error</p>";
        }
    } else {
        $genero = $_POST['genero'];
        $estado_civil = $_POST['estado_civil'];

        $salt = bin2hex(random_bytes(16));

        $senhaHash = password_hash($senha . $salt, PASSWORD_BCRYPT);

        echo "<h2>Dados do Formulário:</h2>";
        echo "<p><strong>Nome:</strong> $nome</p>";
        echo "<p><strong>Email:</strong> $email</p>";
        echo "<p><strong>Gênero:</strong> $genero</p>";
        echo "<p><strong>Linguagens de Programação:</strong> ";
        if (isset($_POST['java'])) echo "Java ";
        if (isset($_POST['python'])) echo "Python ";
        if (isset($_POST['c'])) echo "C ";
        echo "</p>";
        echo "<p><strong>Estado Civil:</strong> $estado_civil</p>";


        $usuarioSenha = $nome . ":" . $senhaHash . ":" . $salt . "\n";
        file_put_contents("autenticacao.txt", $usuarioSenha, FILE_APPEND);
    }
}
?>

</body>
</html>
