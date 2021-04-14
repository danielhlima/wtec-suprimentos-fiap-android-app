package br.com.wtecsuprimentos.domain.usecases;

import java.util.ArrayList;
import java.util.List;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.RegisterCustomerRepository;

public class FirstDatabaseChargeUseCase {

    private RegisterCustomerRepository registerCustomerRepository;
    private DataIn.Callback callback;

    public FirstDatabaseChargeUseCase(RegisterCustomerRepository registerCustomerRepository, DataIn.Callback callback) {
        this.registerCustomerRepository = registerCustomerRepository;
        this.callback = callback;
    }

    public void makeFisrtCharge(){
        makeCharge(createDummyCustomers());
    }

    private void makeCharge(List<Customer> customers){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(registerCustomerRepository != null){
                    for(Customer c : customers){
                        registerCustomerRepository.registerCustomer(c, callback);
                    }
                }
            }
        });
    }

    private List<Customer> createDummyCustomers() {

        List<Customer> customers = new ArrayList<Customer>();

        //Cluster 2
        String razaoSocial = "James Richardson-Patel";
        int maiorAtraso = 0; //OK
        long titulosEmAberto = 0L; //OK
        long faturamentoBruto = 1766880L; //OK
        long margemBruta = 0L; //OK
        int periodoDemonstrativoEmMeses = 12; //OK
        long custos = 0L; //OK
        int anoFundacao = 2003; //OK
        long capitalSocial = 90000L; //OK
        int scorePontualidade = 1; //OK
        int risco = 1; //OK
        double margemBrutaAcumulada = 0.25; //OK Corrigida
        int prazoMedioRecebimentoVendas = 0; //OK Corrigido
        double diferencaPercentualRisco = 0.71; //OK Corrigido0.28
        double percentualRisco = 0.28; // OK Corrigido

        long limiteEmpresaAnaliseCredito = 43200L;
        int empresaME = 1;
        int restricao = 0;

        //Cluster 2
        Customer c1 = new Customer(razaoSocial, maiorAtraso, titulosEmAberto, faturamentoBruto,
                margemBruta, periodoDemonstrativoEmMeses, custos, anoFundacao,
                capitalSocial, scorePontualidade, risco, prazoMedioRecebimentoVendas,
                diferencaPercentualRisco, percentualRisco, margemBrutaAcumulada,
                limiteEmpresaAnaliseCredito, empresaME, restricao, -1);

        //Cluster 0
        Customer c2 = new Customer("Dr. Geoffrey Walsh", 0, 0L, 0L,
                16209878L, 10, 28L, 2006,
                11214526L, 1, 3, 0,
                0.01, 0.01, 0.01,
                2851016L, 0, 0, -1);

        //Cluster 0
        Customer c3 = new Customer("Jean Berry", 4, 2089800L, 11958227L,
                9357949L, 9, 0L, 2000,
                120000L, 1, 3, 102,
                0.94, 0.05, 0.35,
                768233L, 0, 0, -1);

        //Cluster 2
        Customer c4 = new Customer("Joanna Hudson", 4, 0L, 2814940L,
                0L, 7, 0L, 2014,
                20000L, 1, 1, 0,
                0.71, 0.28, 0.62,
                4320L, 1, 0, -1);

        //Cluster 1
        Customer c5 = new Customer("Gordon Jones-Hopkins", 20, 0L, 1285274L,
                0L, 12, 0L, 2013,
                30000L, 0, 4, 0,
                0.39, 0.60, 0.01,
                5920L, 1, 0, -1);

        //Cluster 1
        Customer c6 = new Customer("Liam Jackson", 0, 0L, 918476L,
                0L, 12, 0L, 2011,
                15000L, 0, 2, 0,
                0.62, 0.37, 0.01,
                38400L, 1, 0, -1);

        //Cluster 3
        Customer c7 = new Customer("Mrs. Amber Hawkins", 0, 54000L, 2171417618L,
                1373143082L, 12, 798274536L, 1972,
                900000000L, 1, 3, 64,
                0.94, 0.56, 0.43,
                128430684L, 0, 0, -1);

        //Cluster 3
        Customer c8 = new Customer("Brian Fisher", 4, 0L, 2218916229L,
                837710879L, 9, 1381205350L, 1982,
                9000L, 0, 1, 0,
                0.7924, 0.2075, 0.01,
                0L, 0, 0, -1);


        customers.add(c2); //Cluster 0
        customers.add(c3); //Cluster 0
        customers.add(c5); //Cluster 1
        customers.add(c6); //Cluster 1
        customers.add(c4); //Cluster 2
        customers.add(c1); //Cluster 2
        customers.add(c7); //Cluster 3
        customers.add(c8); //Cluster 3
        return customers;
    }
}
