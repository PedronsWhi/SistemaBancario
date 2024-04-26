package bancoPOO;

public class SistemaBancario {
    public static void main(String[] args) throws InterruptedException {
        //INICIALIZA AS AGENCIAS
        Agencia[] agencias = new Agencia[2]; 
        for (int a = 0; a < agencias.length; a++) {
            agencias[a] = new Agencia(0); 
        }

        // "" "" FUNCIONÁRIOS
        Funcionario[] funcionarios = new Funcionario[4]; 
        for (int f = 0; f < funcionarios.length; f++) {
            Conta salarioAgencia = agencias[f / 2].getConta(); 
            Conta contaInvestimento = new Conta(0); 
            funcionarios[f] = new Funcionario(salarioAgencia, contaInvestimento);
            funcionarios[f].start();
        }

        // "" "" CONTAS
        Conta[] contasClientes = new Conta[5]; 
        for (int c = 0; c < contasClientes.length; c++) {
            //AS CONTAS COMEÇAM COM 1000
            contasClientes[c] = new Conta(1000); 
        }

        // INICIA OS CLIENTES
        Cliente[] clientes = new Cliente[5]; 
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(contasClientes[i], agencias);
            clientes[i].start();
        }

        // Espera todos os clientes terminarem
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // PAGAMENTO DAS AGÊNCIAS
        for (Agencia agencia : agencias) {
            agencia.pagarSalarios(); 
        }

        System.out.println("\n\n");
        System.out.println("SAÍDA:");
        for (int index = 0; index < contasClientes.length; index++) {
            System.out.println("---------------------------------");
            System.out.println("O cliente " + (index + 1) + " restou --> R$" + contasClientes[index].getSaldo());
            System.out.println("---------------------------------");
            Thread.sleep(1000); //DELAY
        }

        for (int index = 0; index < agencias.length; index++) {
            System.out.println("\n\n---------------------------------");
            System.out.println("Agência " + (index + 1) + " faturou --> R$" + agencias[index].getConta().getSaldo());
            System.out.println("---------------------------------");
            Thread.sleep(1000); //DELAY
        }

        for (int h = 0; h < funcionarios.length; h++) {
            System.out.println("\n===========================================================================\nO funcionário " + (h + 1) + ", da agência " + (h / 2 + 1) + ", obteve de Salário --> R$" + (funcionarios[h].getSalario().getSaldo()/2+1400) + ", e investiu--> R$" + (funcionarios[h].getSalario().getSaldo()/2+1400)*0.2);
        }
    }
}
