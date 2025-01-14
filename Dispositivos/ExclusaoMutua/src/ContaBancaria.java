public class ContaBancaria {
    private double saldo;
    public synchronized void depositar(double valor) {
        var s = getSaldo();
        s += valor;
        setSaldo(s);
    }
    public synchronized void sacar(double valor) {
        var s = getSaldo();
        s -= valor;
        setSaldo(s);
    }
    public double getSaldo() { return saldo; };
    public void setSaldo(double saldo) { this.saldo = saldo; }
}