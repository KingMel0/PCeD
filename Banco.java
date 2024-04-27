import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class Banco {
    private String nome;
    private Lock lock;

    public Banco(String nome) {
        this.nome = nome;
        this.lock = new ReentrantLock();
    }

    public void transferir(Conta contaOrigem, Conta contaDestino, double valor) {
        lock.lock();
        try {
            if (contaOrigem.getSaldo() >= valor) {
                contaOrigem.debitar(valor);
                contaDestino.creditar(valor);
                System.out.println("Transferência de R$" + valor + " realizada de " +
                        contaOrigem.getNumero() + " para " + contaDestino.getNumero());
                System.out.println("Saldo final da conta " + contaOrigem.getNumero() +
                        ": R$" + contaOrigem.getSaldo());
                System.out.println("Saldo final da conta " + contaDestino.getNumero() +
                        ": R$" + contaDestino.getSaldo());
            } else {
                System.out.println("Saldo insuficiente na conta " + contaOrigem.getNumero());
            }
        } finally {
            lock.unlock();
        }
    }

    public void exibirSaldosFinais(Conta[] contas) {
        System.out.println("\nSaldo final de todas as contas:");
        for (Conta conta : contas) {
            System.out.println("Conta " + conta.getNumero() + ": R$" + conta.getSaldo());
        }
    }
}
