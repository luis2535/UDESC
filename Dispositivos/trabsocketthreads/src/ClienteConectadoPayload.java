import java.net.Socket;

public class ClienteConectadoPayload {
    private Socket ip;
    private String nome;

    public void setIp(Socket ip) {
        this.ip = ip;
    }

    public Socket getIp() {
        return ip;
    }

    public ClienteConectadoPayload(Socket ip, String nome) {
        this.ip = ip;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
