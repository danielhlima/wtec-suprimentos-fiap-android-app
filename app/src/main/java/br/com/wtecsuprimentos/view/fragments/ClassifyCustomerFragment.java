package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.bus.ClassifyCustomerView;
import br.com.wtecsuprimentos.view.viewmodel.ClassifyCustomerViewModel;


public class ClassifyCustomerFragment extends Fragment implements DataOut.Callback<List<Integer>> {

    private ClassifyCustomerViewModel viewModel;

    public ClassifyCustomerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
                .create(ClassifyCustomerViewModel.class);

        View view =  inflater.inflate(R.layout.fragment_classify_customer, container, false);
        ((Button)view.findViewById(R.id.bt_classify_customers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classifyCustomer();
            }
        });
        return view;
    }

    private void classifyCustomer() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(createTestCustomer());
        viewModel.classifyCustomer(customers, this);
    }

    private Customer createTestCustomer(){
        String razaoSocial = "Abbie Shaw";
        int maiorAtraso = 12; //OK
        int titulosEmAberto = 1491736; //OK
        int faturamentoBruto = 0; //OK
        int margemBruta = 0; //OK
        int periodoDemonstrativoEmMeses = 1; //OK
        int custos = 0; //OK
        int anoFundacao = 2011; //OK
        int capitalSocial = 979957432; //OK
        int scorePontualidade = 1; //OK
        int risco = 1; //OK
        double margemBrutaAcumulada = 5543835022.0; //OK Corrigida
        float prazoMedioRecebimentoVendas = 15; //OK Corrigido
        float diferencaPercentualRisco = 75; //OK Corrigido
        float percentualRisco = 25; // OK Corrigido

        Customer customer = new Customer(razaoSocial, maiorAtraso, titulosEmAberto, faturamentoBruto,
                margemBruta, periodoDemonstrativoEmMeses, custos, anoFundacao,
                capitalSocial, scorePontualidade, risco, margemBrutaAcumulada,
                prazoMedioRecebimentoVendas, diferencaPercentualRisco, percentualRisco);

        return customer;
    }

    @Override
    public void onSuccess(List<Integer> parameter) {
        Log.d("DABUEK", "Classificação no Fragment: "+parameter.get(0));
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro no fragment: "+throwable.getMessage());
    }
}