package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.viewmodel.AskForCreditViewModel;


public class AskForCreditFragment extends Fragment implements DataOut.Callback<Double> {

    private AskForCreditViewModel viewModel;

    public AskForCreditFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
                .create(AskForCreditViewModel.class);

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ask_for_credit, container, false);
        ((Button)view.findViewById(R.id.bt_ask_credit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForCredit();
            }
        });

        return view;
    }

    private void askForCredit() {
        Customer customer = createTestCustomer();
        double value = 50000;
        viewModel.askForCredit(customer, this, value);
    }

    @Override
    public void onSuccess(Double parameter) {
        Log.d("DABUEK", "Regressão no Fragment: "+parameter);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro de regressão no fragment: "+throwable.getMessage());
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
}