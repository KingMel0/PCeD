import java.util.Random;

@SuppressWarnings("unused")
public class Cliente extends Thread {
    private String nome;
    private String cpf;
    private Conta conta;
    private Loja[] lojas;

    public Cliente(String nome, String cpf, Conta conta, Loja[] lojas) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
        this.lojas = lojas;
    }

    public Conta getConta() {
        return conta;
    }

    public Loja[] getLojas() {
        return lojas;
    }

    @Override
    public void run() {
        while (conta.getSaldo() > 0) {
            realizarCompra();
        }
        System.out.println(nome + " terminou suas compras.");
    }

    private void realizarCompra() {
        Random random = new Random();
        double valorCompra = random.nextDouble() < 0.5 ? 100.0 : 200.0;
        Loja loja = lojas[random.nextInt(lojas.length)];

        synchronized (conta) {
            if (conta.getSaldo() >= valorCompra) {
                conta.debitar(valorCompra);
                System.out.println(nome + " realizou uma compra de R$" + valorCompra + " na loja " + loja.getNome() +
                        ". Saldo restante: R$" + conta.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para realizar a compra na loja " + loja.getNome());
            }
        }

        try {
            Thread.sleep(random.nextInt(3000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
