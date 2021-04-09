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
        int titulosEmAberto = 0; //OK
        int faturamentoBruto = 1766880; //OK
        int margemBruta = 0; //OK
        int periodoDemonstrativoEmMeses = 12; //OK
        int custos = 0; //OK
        int anoFundacao = 2003; //OK
        int capitalSocial = 90000; //OK
        int scorePontualidade = 1; //OK
        int risco = 1; //OK
        double margemBrutaAcumulada = 0.25; //OK Corrigida
        float prazoMedioRecebimentoVendas = 0; //OK Corrigido
        float diferencaPercentualRisco = 0.71f; //OK Corrigido0.28
        float percentualRisco = 0.28f; // OK Corrigido

        float limiteEmpresaAnaliseCredito = 43200f;
        int empresaME = 1;
        int restricao = 0;

        //Cluster 2
        Customer c1 = new Customer(razaoSocial, maiorAtraso, titulosEmAberto, faturamentoBruto,
                margemBruta, periodoDemonstrativoEmMeses, custos, anoFundacao,
                capitalSocial, scorePontualidade, risco, prazoMedioRecebimentoVendas,
                diferencaPercentualRisco, percentualRisco, margemBrutaAcumulada,
                limiteEmpresaAnaliseCredito, empresaME, restricao, -1);

        //Cluster 0
        Customer c2 = new Customer("Dr. Geoffrey Walsh", 0, 0, 0,
                16209878, 10, 0.28f, 2006,
                11214526, 1, 3, 0,
                0.10f, 0, 0,
                2851016, 0, 0, -1);

        //Cluster 0
        Customer c3 = new Customer("Jean Berry", 4, 2089800, 11958227,
                9357949, 9, 0, 2000,
                120000, 1, 3, 102,
                0.9433f, 0.0566f, 0.3501f,
                768233, 0, 0, -1);

        //Cluster 2
        Customer c4 = new Customer("Joanna Hudson", 4, 0, 2814940,
                0, 7, 0, 2014,
                20000, 1, 1, 0,
                0.7169f, 0.2830f, 0.6247f,
                4320, 1, 0, -1);

        //Cluster 1
        Customer c5 = new Customer("Gordon Jones-Hopkins", 20, 0, 1285274,
                0, 12, 0, 2013,
                30000, 0, 4, 0,
                0.3962f, 0.6037f, 0,
                5920, 1, 0, -1);

        //Cluster 1
        Customer c6 = new Customer("Liam Jackson", 0, 0, 918476,
                0, 12, 0, 2011,
                15000, 0, 2, 0,
                0.6226f, 0.3773f, 0,
                38400, 1, 0, -1);

        //Cluster 3
        Customer c7 = new Customer("Mrs. Amber Hawkins", 0, 54000, 2171417618.0f,
                1373143082, 12, 798274536, 1972,
                900000000, 1, 3, 64,
                0.9433f, 0.5660f, 0.4316f,
                128430684, 0, 0, -1);

        //Cluster 3
        Customer c8 = new Customer("Brian Fisher", 4, 0, 2218916229.0f,
                837710879, 9, 1381205350, 1982,
                9000, 0, 1, 0,
                0.7924f, 0.2075f, 0,
                0, 0, 0, -1);


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
