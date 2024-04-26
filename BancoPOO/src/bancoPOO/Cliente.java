package bancoPOO;

public class Cliente extends Thread {
    public static int clienteContador = 0;

    public int clienteNumero;
    public Conta conta;
    public Agencia[] agencias;

    public Cliente(Conta conta, Agencia[] agencias) {
        this.agencias = agencias; 
        this.conta = conta; 
        this.clienteNumero = ++clienteContador; 
    }

    @Override
    public void run() { //EXECUTA
        while (conta.getSaldo() > 0) { //RODA ENQUANTO SALDO > 0
            double compra = Math.random() < 0.5 ? 100 : 200; //ALEATÓRIO ENTRE 100 E 200
            int agenciaCompra = (int) (Math.random() * agencias.length); //ALEATÓRIO PARA A AGÊNCIA
            Agencia agencia = agencias[agenciaCompra];
            synchronized (agencia.getConta()) {
                if (conta.receber(compra)) {
                    agencia.getConta().investir(compra); //FAZ A COMPRA
                        try {
                            Thread.sleep(500); //FAZ UMA PAUSA PARA MOSTRAR OS FUNCIONÁRIOS NO CONSOLE
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.printf("\n---------------------------------\nCompra realizada na agência %d\nPelo cliente %d\nNo valor de = R$ %.0f\n", agenciaCompra+1,clienteNumero, compra);
                } else {
                    break; //SAI DO LOOP
                }
            }
        }
    }
}
