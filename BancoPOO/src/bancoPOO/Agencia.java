package bancoPOO;

public class Agencia {
    public Conta conta;

    public Agencia(double saldoInicial) {
        this.conta = new Conta(saldoInicial); //CRIA A CONTA DA AGÊNCIA
    }

    public Conta getConta() {
        return conta;
    }

    public void pagarSalarios() {
    	//EFETUA O PAGAMENTO DOS SALÁRIOS
        System.out.println("\n\nPagamento dos funcionários efetuado");
    }
}
