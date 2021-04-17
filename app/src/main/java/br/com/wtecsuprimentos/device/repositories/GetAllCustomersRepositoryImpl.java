package br.com.wtecsuprimentos.device.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.wtecsuprimentos.device.data.AppDatabase;
import br.com.wtecsuprimentos.device.data.models.CustomerModel;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.GetAllCustomersRepository;

public class GetAllCustomersRepositoryImpl implements GetAllCustomersRepository {

    private AppDatabase mDb;

    public GetAllCustomersRepositoryImpl(Context context){
        mDb = AppDatabase.getInstance(context);
    }

    @Override
    public void getAllCustomers(DataOut.Callback callback) {
        try{
            List<CustomerModel> customerModels = mDb.customerDao().getAllCustomers();
            callback.onSuccess(customersFromCustomerModel(customerModels));
        }catch (Exception e){
            callback.onError(e);
        }
    }

//    Customer(String razaoSocial, int maiorAtraso, long titulosEmAberto, long faturamentoBruto,
//             long margemBruta, int periodoDemonstrativoEmMeses, long custos, int anoFundacao,
//             long capitalSocial, int scorePontualidade, int risco, int prazoMedioRecebimentoVendas,
//             double diferencaPercentualRisco, double percentualRisco, double margemBrutaAcumulada,
//             long limiteEmpresaAnaliseCredito, int emrpesaME, int restricao, int cluster)

    private List<Customer> customersFromCustomerModel(List<CustomerModel> customerModels){
        List<Customer> customers = new ArrayList<Customer>();
        for(CustomerModel cm : customerModels){
            customers.add(new Customer(cm.getRazaoSocial(), cm.getMaiorAtraso(), cm.getTitulosEmAberto(),
            cm.getFaturamentoBruto(), cm.getMargemBruta(), cm.getPeriodoDemonstrativoEmMeses(), cm.getCustos(),
            cm.getAnoFundacao(), cm.getCapitalSocial(), cm.getScorePontualidade(), cm.getRisco(),
            cm.getPrazoMedioRecebimentoVendas(), cm.getDiferencaPercentualRisco(), cm.getPercentualRisco(),
            cm.getMargemBrutaAcumulada(), cm.getLimiteEmpresaAnaliseCredito(), cm.getEmrpesaME(),
            cm.getRestricao(), cm.getCluster()));
        }
        return customers;
    }
}
