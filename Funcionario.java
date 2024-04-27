public class Funcionario extends Thread {
    private String nome;
    private String cargo;
    private double salario;
    private Conta contaSalario;
    private Conta contaInvestimentos;

    public Funcionario(String nome, String cargo, double salario, Conta contaSalario, Conta contaInvestimentos) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public void setContaSalario(Conta contaSalario) {
        this.contaSalario = contaSalario;
    }

    public Conta getContaInvestimentos() {
        return contaInvestimentos;
    }

    public void setContaInvestimentos(Conta contaInvestimentos) {
        this.contaInvestimentos = contaInvestimentos;
    }

    @Override
    public void run() {
        synchronized (contaSalario) {
            if (contaInvestimentos != null) {
                contaSalario.creditar(salario); 
                double valorInvestimento = salario * 0.20; 
                contaSalario.debitar(valorInvestimento); 
                contaInvestimentos.creditar(valorInvestimento); 
                System.out.println(nome + " recebeu o salário de R$" + salario +
                        " e investiu R$" + valorInvestimento + " na conta de investimentos.");
            } else {
                System.err.println("Error: contaInvestimentos is null.");
            }
        }
    }

}

