package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.viewmodel.RegisterCustomerViewModel;


public class RegisterCustomerFragment extends Fragment implements DataIn.Callback {

    private RegisterCustomerViewModel registerCustomerViewModel;

    public RegisterCustomerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        registerCustomerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity()
                .getApplication())
                .create(RegisterCustomerViewModel.class);


        View view =  inflater.inflate(R.layout.fragment_register_customer, container, false);

        ((Button)view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCustomer();
            }
        });

        return view;
    }

    private void registerCustomer(){
        registerCustomerViewModel.registerCustomer(createTestCustomer(), this);
    }

    private Customer createTestCustomer(){
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
        int prazoMedioRecebimentoVendas = 0; //OK Corrigido
        float diferencaPercentualRisco = 0.71f; //OK Corrigido0.28
        float percentualRisco = 0.28f; // OK Corrigido

        long limiteEmpresaAnaliseCredito = 43200L;
        int empresaME = 1;
        int restricao = 0;

        Customer customer = new Customer(razaoSocial, maiorAtraso, titulosEmAberto, faturamentoBruto,
                margemBruta, periodoDemonstrativoEmMeses, custos, anoFundacao,
                capitalSocial, scorePontualidade, risco, prazoMedioRecebimentoVendas,
                diferencaPercentualRisco, percentualRisco, margemBrutaAcumulada,
                limiteEmpresaAnaliseCredito, empresaME, restricao, -1);

        return customer;
    }

    @Override
    public void onSuccess() {
        Log.d("DABUEK", "Customer Criado com sucesso.");
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro ao criar Customer: "+throwable.getMessage());
    }
}