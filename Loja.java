import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nome;
    private Conta conta;
    private List<Funcionario> funcionarios;

    public Loja(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
    
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void pagarSalarios() {
        double totalSalarios = 0;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios += funcionario.getSalario();
        }
        synchronized (conta) {
            if (conta.getSaldo() >= totalSalarios) {
                conta.debitar(totalSalarios);
                for (Funcionario funcionario : funcionarios) {
                    funcionario.getContaSalario().creditar(funcionario.getSalario());
                }
                System.out.println("Salários pagos na loja " + nome + ". Saldo restante: R$" + conta.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para pagar os salários na loja " + nome);
            }
        }
    }
}

