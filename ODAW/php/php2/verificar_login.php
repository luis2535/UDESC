<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST['usuario'];
    $senha = $_POST['senha'];

    $arquivo = "autenticacao.txt";
    $linhas = file($arquivo, FILE_IGNORE_NEW_LINES);

    $credenciais_validas = false;

    foreach ($linhas as $linha) {
        list($usuario_armazenado, $senha_hash, $salt) = explode(":", $linha);

        if ($usuario === $usuario_armazenado) {
            // Verifique a senha usando password_verify
            if (password_verify($senha . $salt, $senha_hash)) {
                $credenciais_validas = true;
                break;
            }
        }
    }

    if ($credenciais_validas) {
        header("Location: welcome.php?nome=$usuario");
        exit();
    } else {
        echo "Usuário ou senha inválidos. Tente novamente.";
    }
}
?>
