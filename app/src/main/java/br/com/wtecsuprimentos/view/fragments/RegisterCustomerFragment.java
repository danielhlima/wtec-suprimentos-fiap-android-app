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
        String razaoSocial = "Abbie Shaw";
        int maiorAtraso = 12;
        int titulosEmAberto = 1491736;
        int faturamentoBruto = 0;
        int margemBruta = 0;
        int periodoDemonstrativoEmMeses = 1;
        int custos = 0;
        int anoFundacao = 2011;
        int capitalSocial = 979957432;
        int scorePontualidade = 1;
        int risco = 1;
        float margemBrutaAcumulada = 0;
        float prazoMedioRecebimentoVendas = 0;
        float diferencaPercentualRisco = 1;
        float percentualRisco = 0;

        Customer customer = new Customer(razaoSocial, maiorAtraso, titulosEmAberto, faturamentoBruto,
        margemBruta, periodoDemonstrativoEmMeses, custos, anoFundacao,
        capitalSocial, scorePontualidade, risco, margemBrutaAcumulada,
        prazoMedioRecebimentoVendas, diferencaPercentualRisco, percentualRisco);

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