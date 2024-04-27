import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Meu Banco");

        Loja[] lojas = new Loja[2];
        for (int i = 0; i < lojas.length; i++) {
            Conta contaLoja = new Conta(i + 1, 10000.0, null); 
            lojas[i] = new Loja("Loja " + (i + 1), contaLoja);

            Funcionario funcionario1 = new Funcionario("Funcionario " + (2*i + 1), "Cargo 1", 1400.0, contaLoja, null);
            Funcionario funcionario2 = new Funcionario("Funcionario " + (2*i + 2), "Cargo 2", 1400.0, contaLoja, null);
            lojas[i].adicionarFuncionario(funcionario1);
            lojas[i].adicionarFuncionario(funcionario2);
            funcionario1.start();
            funcionario2.start();
        }

        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i++) {
            Conta contaCliente = new Conta(i + 1, 1000.0, null);
            Cliente cliente = new Cliente("Cliente " + (i + 1), "1234567890" + (i + 1), contaCliente, lojas);
            clientes[i] = cliente;
            cliente.start();
        }

        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        banco.exibirSaldosFinais(criarArrayContas(lojas, clientes));
    }

    private static Conta[] criarArrayContas(Loja[] lojas, Cliente[] clientes) {
        List<Conta> contas = new ArrayList<>();
        for (Loja loja : lojas) {
            contas.add(loja.getConta());
            for (Funcionario funcionario : loja.getFuncionarios()) {
                contas.add(funcionario.getContaSalario());
            }
        }
        for (Cliente cliente : clientes) {
            contas.add(cliente.getConta());
        }
        return contas.toArray(new Conta[0]);
    }
}
