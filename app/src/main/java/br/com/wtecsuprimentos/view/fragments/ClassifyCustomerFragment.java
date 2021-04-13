package br.com.wtecsuprimentos.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.validator.TextValidator;
import br.com.wtecsuprimentos.view.viewmodel.ClassifyCustomerViewModel;


public class ClassifyCustomerFragment extends Fragment implements DataOut.Callback<List<Integer>> {

    private ClassifyCustomerViewModel viewModel;

    private LinearLayout progressBar;

    private EditText etNome, etMaiorAtraso, etTitulosEmAberto, etFaturamentoBruto, etMargemBruta,
    etPeriodoDemonstrativoEmMeses, etCustos, etAnoFundacao, etCapitalSocial, etScorePontualidade,
    etPrazoMedioRecebimentoVendas, etLimiteEmpresaAnaliseCredito;

    private boolean etNomeValidated, etMaiorAtrasoValidated, etTitulosEmAbertoValidated,
            etFaturamentoBrutoValidated, etMargemBrutaValidated, etPeriodoDemonstrativoEmMesesValidated,
            etCustosValidated, etAnoFundacaoValidated, etCapitalSocialValidated, etScorePontualidadeValidated,
            etPrazoMedioRecebimentoVendasValidated, etLimiteEmpresaAnaliseCreditoValidated;

    private int risco = 1, microempresa = 1, restricao = 0;

    private MaskedEditText etMargemBrutaAcumulada, etDiferencaPercentualRisco, etPercentualRisco;

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

        progressBar = (LinearLayout)view.findViewById(R.id.ll_progressBar_classify);

        etNome = (EditText)view.findViewById(R.id.editTextTextPersonName);
        etNome.addTextChangedListener(new TextValidator(etNome) {
            @Override
            public void validate(EditText editText, String text) {
                if(text.length() < 3){
                    etNome.setTextColor(Color.parseColor("#FF0000"));
                }else{
                    if(!etNome.getText().toString().equalsIgnoreCase(getString(R.string.erro_nome)))
                        etNome.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                }
            }
        });

        etNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etNome.getText().toString().length() < 3){
                        etNome.setTextColor(Color.parseColor("#FF0000"));
                        etNome.setText(getString(R.string.erro_nome));
                        Toast.makeText(getContext(), R.string.erro_nome, Toast.LENGTH_LONG).show();
                        etNomeValidated = false;
                    }else{
                        etNomeValidated = true;
                    }
                }else{
                    if(etNome.getText().toString().length() > 0 &&
                            etNome.getText().toString().equalsIgnoreCase(getString(R.string.erro_nome))){
                        etNome.setText("");
                    }
                }
            }
        });


        etMaiorAtraso = (EditText)view.findViewById(R.id.editTextMaiorAtraso);
        etMaiorAtraso.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etMaiorAtraso.getText().toString().length() < 1){
                        etMaiorAtraso.setTextColor(Color.parseColor("#FF0000"));
                        etMaiorAtraso.setText(getString(R.string.erro_maior_atraso));
                        Toast.makeText(getContext(), R.string.erro_maior_atraso, Toast.LENGTH_LONG).show();
                        etMaiorAtrasoValidated = false;
                    }else {
                        etMaiorAtrasoValidated = true;
                    }
                }else{
                    if(etMaiorAtraso.getText().toString().length() > 0 &&
                            etMaiorAtraso.getText().toString().equalsIgnoreCase(getString(R.string.erro_maior_atraso))){
                        etMaiorAtraso.setText("");
                        etMaiorAtraso.setTextColor(getResources().getColor(R.color.primaryDarkColor));

                    }
                }
            }
        });

        etTitulosEmAberto = (EditText)view.findViewById(R.id.editTextTitulosEmAberto);
        etTitulosEmAberto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etTitulosEmAberto.getText().toString().length() < 1){
                        etTitulosEmAberto.setTextColor(Color.parseColor("#FF0000"));
                        etTitulosEmAberto.setText(getString(R.string.erro_titulos_em_aberto));
                        Toast.makeText(getContext(), R.string.erro_titulos_em_aberto, Toast.LENGTH_LONG).show();
                        etTitulosEmAbertoValidated = false;
                    }else{
                        etTitulosEmAbertoValidated = true;
                    }
                }else{
                    if(etTitulosEmAberto.getText().toString().length() > 0 &&
                            etTitulosEmAberto.getText().toString().equalsIgnoreCase(getString(R.string.erro_titulos_em_aberto))){
                        etTitulosEmAberto.setText("");
                        etTitulosEmAberto.setTextColor(getResources().getColor(R.color.primaryDarkColor));

                    }
                }
            }
        });


        etFaturamentoBruto = (EditText)view.findViewById(R.id.editTextFaturamentoBruto);
        etFaturamentoBruto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etFaturamentoBruto.getText().toString().length() < 1){
                        etFaturamentoBruto.setTextColor(Color.parseColor("#FF0000"));
                        etFaturamentoBruto.setText(getString(R.string.erro_faturamento_bruto));
                        Toast.makeText(getContext(), R.string.erro_faturamento_bruto, Toast.LENGTH_LONG).show();
                        etFaturamentoBrutoValidated = false;
                    }else{
                        etFaturamentoBrutoValidated = true;
                    }
                }else{
                    if(etFaturamentoBruto.getText().toString().length() > 0 &&
                            etFaturamentoBruto.getText().toString().equalsIgnoreCase(getString(R.string.erro_faturamento_bruto))){
                        etFaturamentoBruto.setText("");
                        etFaturamentoBruto.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etMargemBruta = (EditText)view.findViewById(R.id.editTextMargemBruta);
        etMargemBruta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etMargemBruta.getText().toString().length() < 1){
                        etMargemBruta.setTextColor(Color.parseColor("#FF0000"));
                        etMargemBruta.setText(getString(R.string.erro_margem_bruta));
                        Toast.makeText(getContext(), R.string.erro_margem_bruta, Toast.LENGTH_LONG).show();
                        etMargemBrutaValidated = false;
                    }else{
                        etMargemBrutaValidated = true;
                    }
                }else{
                    if(etMargemBruta.getText().toString().length() > 0 &&
                            etMargemBruta.getText().toString().equalsIgnoreCase(getString(R.string.erro_margem_bruta))){
                        etMargemBruta.setText("");
                        etMargemBruta.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etPeriodoDemonstrativoEmMeses = (EditText)view.findViewById(R.id.editTextPeriodoDemonstrativoEmMeses);
        etPeriodoDemonstrativoEmMeses.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etPeriodoDemonstrativoEmMeses.getText().toString().length() < 1){
                        etPeriodoDemonstrativoEmMeses.setTextColor(Color.parseColor("#FF0000"));
                        etPeriodoDemonstrativoEmMeses.setText(getString(R.string.erro_periodo_demonstrativo_em_meses));
                        Toast.makeText(getContext(), R.string.erro_periodo_demonstrativo_em_meses, Toast.LENGTH_LONG).show();
                        etPeriodoDemonstrativoEmMesesValidated = false;
                    }else{
                        etPeriodoDemonstrativoEmMesesValidated = true;
                    }
                }else{
                    if(etPeriodoDemonstrativoEmMeses.getText().toString().length() > 0 &&
                            etPeriodoDemonstrativoEmMeses.getText().toString().equalsIgnoreCase(getString(R.string.erro_periodo_demonstrativo_em_meses))){
                        etPeriodoDemonstrativoEmMeses.setText("");
                        etPeriodoDemonstrativoEmMeses.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etCustos = (EditText)view.findViewById(R.id.editTextCustos);
        etCustos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etCustos.getText().toString().length() < 1){
                        etCustos.setTextColor(Color.parseColor("#FF0000"));
                        etCustos.setText(getString(R.string.erro_custos));
                        Toast.makeText(getContext(), R.string.erro_custos, Toast.LENGTH_LONG).show();
                        etCustosValidated = false;
                    }else{
                        etCustosValidated = true;
                    }
                }else{
                    if(etCustos.getText().toString().length() > 0 &&
                            etCustos.getText().toString().equalsIgnoreCase(getString(R.string.erro_custos))){
                        etCustos.setText("");
                        etCustos.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etAnoFundacao = (EditText)view.findViewById(R.id.editTextAnoFundacao);
        etAnoFundacao.addTextChangedListener(new TextValidator(etAnoFundacao) {
            @Override
            public void validate(EditText editText, String text) {
                if(text.length() != 4){
                    etAnoFundacao.setTextColor(Color.parseColor("#FF0000"));
                }else{
                    if(!etAnoFundacao.getText().toString().equalsIgnoreCase(getString(R.string.erro_ano_fundacao)))
                        etAnoFundacao.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                }
            }
        });

        etAnoFundacao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etAnoFundacao.getText().toString().length() != 4){
                        etAnoFundacao.setTextColor(Color.parseColor("#FF0000"));
                        etAnoFundacao.setText(getString(R.string.erro_ano_fundacao));
                        Toast.makeText(getContext(), R.string.erro_ano_fundacao, Toast.LENGTH_LONG).show();
                        etAnoFundacaoValidated = false;
                    }else{
                        etAnoFundacaoValidated = true;
                    }
                }else{
                    if(etAnoFundacao.getText().toString().length() > 0 &&
                            etAnoFundacao.getText().toString().equalsIgnoreCase(getString(R.string.erro_ano_fundacao))){
                        etAnoFundacao.setText("");
                    }
                }
            }
        });


        etCapitalSocial = (EditText)view.findViewById(R.id.editTextCapitalSocial);
        etCapitalSocial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etCapitalSocial.getText().toString().length() < 1){
                        etCapitalSocial.setTextColor(Color.parseColor("#FF0000"));
                        etCapitalSocial.setText(getString(R.string.erro_capital_social));
                        Toast.makeText(getContext(), R.string.erro_capital_social, Toast.LENGTH_LONG).show();
                        etCapitalSocialValidated = false;
                    }else{
                        etCapitalSocialValidated = true;
                    }
                }else{
                    if(etCapitalSocial.getText().toString().length() > 0 &&
                            etCapitalSocial.getText().toString().equalsIgnoreCase(getString(R.string.erro_capital_social))){
                        etCapitalSocial.setText("");
                        etCapitalSocial.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etScorePontualidade = (EditText)view.findViewById(R.id.editTextNumberScorePontualidade);
        etScorePontualidade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etScorePontualidade.getText().toString().length() < 1){
                        etScorePontualidade.setTextColor(Color.parseColor("#FF0000"));
                        etScorePontualidade.setText(getString(R.string.erro_score_pontualidade));
                        Toast.makeText(getContext(), R.string.erro_score_pontualidade, Toast.LENGTH_LONG).show();
                        etScorePontualidadeValidated = false;
                    }else{
                        etScorePontualidadeValidated = true;
                    }
                }else{
                    if(etScorePontualidade.getText().toString().length() > 0 &&
                            etScorePontualidade.getText().toString().equalsIgnoreCase(getString(R.string.erro_score_pontualidade))){
                        etScorePontualidade.setText("");
                        etScorePontualidade.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etLimiteEmpresaAnaliseCredito = (EditText)view.findViewById(R.id.editTextLimiteEmpresaAnaliseCredito);
        etLimiteEmpresaAnaliseCredito.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etLimiteEmpresaAnaliseCredito.getText().toString().length() < 1){
                        etLimiteEmpresaAnaliseCredito.setTextColor(Color.parseColor("#FF0000"));
                        etLimiteEmpresaAnaliseCredito.setText(getString(R.string.erro_limite_empresa_analise_credito));
                        Toast.makeText(getContext(), R.string.erro_limite_empresa_analise_credito, Toast.LENGTH_LONG).show();
                        etLimiteEmpresaAnaliseCreditoValidated = false;
                    }else{
                        etLimiteEmpresaAnaliseCreditoValidated = true;
                    }
                }else{
                    if(etLimiteEmpresaAnaliseCredito.getText().toString().length() > 0 &&
                            etLimiteEmpresaAnaliseCredito.getText().toString().equalsIgnoreCase(getString(R.string.erro_limite_empresa_analise_credito))){
                        etLimiteEmpresaAnaliseCredito.setText("");
                        etLimiteEmpresaAnaliseCredito.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etPrazoMedioRecebimentoVendas = (EditText)view.findViewById(R.id.editTextPrazoMedioRecebimentoVendas);
        etPrazoMedioRecebimentoVendas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (etPrazoMedioRecebimentoVendas.getText().toString().length() < 1){
                        etPrazoMedioRecebimentoVendas.setTextColor(Color.parseColor("#FF0000"));
                        etPrazoMedioRecebimentoVendas.setText(getString(R.string.erro_prazo_medio_recebimento_vendas));
                        Toast.makeText(getContext(), R.string.erro_prazo_medio_recebimento_vendas, Toast.LENGTH_LONG).show();
                        etPrazoMedioRecebimentoVendasValidated = false;
                    }else{
                        etPrazoMedioRecebimentoVendasValidated = true;
                    }
                }else{
                    if(etPrazoMedioRecebimentoVendas.getText().toString().length() > 0 &&
                            etPrazoMedioRecebimentoVendas.getText().toString().equalsIgnoreCase(getString(R.string.erro_prazo_medio_recebimento_vendas))){
                        etPrazoMedioRecebimentoVendas.setText("");
                        etPrazoMedioRecebimentoVendas.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                    }
                }
            }
        });


        etMargemBrutaAcumulada = (MaskedEditText)view.findViewById(R.id.editTextMargemBrutaAcumulada);
        etDiferencaPercentualRisco = (MaskedEditText)view.findViewById(R.id.editTextDiferencaPercentualRisco);
        etPercentualRisco = (MaskedEditText)view.findViewById(R.id.editTextPercentualRisco);

        ((RadioGroup)view.findViewById(R.id.radioGroupRisco)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonAltoRisco:
                        risco = 4;
                        break;

                    case R.id.radioButtonMedioRisco:
                        risco = 3;
                        break;

                    case R.id.radioButtonBaixoRisco:
                        risco = 2;
                        break;

                    case R.id.radioButtonMuitoBaixoRisco:
                        risco = 1;
                        break;
                }
            }
        });


        ((RadioGroup)view.findViewById(R.id.radioGroupEmpresaME)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonEmpresaMESim:
                        microempresa = 1;
                        break;

                    case R.id.radioButtonEmpresaMENao:
                        microempresa = 0;
                        break;
                }
            }
        });


        ((RadioGroup)view.findViewById(R.id.radioGroupRestricao)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonRestricaoSim:
                        restricao = 1;
                        break;

                    case R.id.radioButtonRestricaoNao:
                        restricao = 0;
                        break;
                }
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_frag_class, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.menu_item_novo_cliente:

                break;

            case R.id.menu_item_listar_classificados:

                break;

            case R.id.menu_item_listar_classificar:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void classifyCustomer() {
        if(areFieldsValidated()) {
            progressBar.setVisibility(View.VISIBLE);
            Customer c = createCustomer();
            if (c != null) {
                List<Customer> customers = new ArrayList<Customer>();
                customers.add(c);
                viewModel.classifyCustomer(customers, this);
            }
        }else{
            Toast.makeText(getContext(), R.string.erro_validacao, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private boolean areFieldsValidated() {
        return (etNomeValidated && etMaiorAtrasoValidated && etTitulosEmAbertoValidated &&
                etFaturamentoBrutoValidated && etMargemBrutaValidated && etPeriodoDemonstrativoEmMesesValidated &&
                etCustosValidated && etAnoFundacaoValidated && etCapitalSocialValidated && etScorePontualidadeValidated &&
                etPrazoMedioRecebimentoVendasValidated &&
                etLimiteEmpresaAnaliseCreditoValidated);
    }

    private Customer createCustomer() {
        String tDiffPercRisco =  etDiferencaPercentualRisco.getText().toString();
        if(tDiffPercRisco.endsWith("%") ||
            tDiffPercRisco.endsWith(".")){
            tDiffPercRisco = tDiffPercRisco.substring(0, tDiffPercRisco.length()-1);
        }
        float diferencaPercentualRisco = Float.parseFloat(tDiffPercRisco);

        String tPercentualRisco =  etPercentualRisco.getText().toString();
        if(tPercentualRisco.endsWith("%") ||
                tPercentualRisco.endsWith(".")){
            tPercentualRisco = tPercentualRisco.substring(0, tPercentualRisco.length()-1);
        }
        float percentualRisco = Float.parseFloat(tPercentualRisco);

        String tMargemBrutaAcumulada = etMargemBrutaAcumulada.getText().toString();
        if(tMargemBrutaAcumulada.endsWith("%") ||
                tMargemBrutaAcumulada.endsWith(".")){
            tMargemBrutaAcumulada = tMargemBrutaAcumulada.substring(0, tMargemBrutaAcumulada.length()-1);
        }
        double margemBrutaAcumulada = Double.parseDouble(tMargemBrutaAcumulada);

        Customer c = new Customer(etNome.getText().toString(),
                Integer.parseInt(etMaiorAtraso.getText().toString()),
                Integer.parseInt(etTitulosEmAberto.getText().toString()),
                Integer.parseInt(etFaturamentoBruto.getText().toString()),
                Integer.parseInt(etMargemBruta.getText().toString()),
                Integer.parseInt(etPeriodoDemonstrativoEmMeses.getText().toString()),
                Integer.parseInt(etCustos.getText().toString()),
                Integer.parseInt(etAnoFundacao.getText().toString()),
                Integer.parseInt(etCapitalSocial.getText().toString()),
                Integer.parseInt(etScorePontualidade.getText().toString()),
                risco, Float.parseFloat(etPrazoMedioRecebimentoVendas.getText().toString()),
                diferencaPercentualRisco,
                percentualRisco,
                margemBrutaAcumulada,
                Float.parseFloat(etLimiteEmpresaAnaliseCredito.getText().toString()), microempresa, restricao, -1);
        return c;
    }

    private Customer createTestCustomer(){
        String razaoSocial = "Abbie Shaw";
        int maiorAtraso = 12; //OK
        int titulosEmAberto = 1491736; //OK
        int faturamentoBruto = 0; //OK'
        int margemBruta = 0; //OK
        int periodoDemonstrativoEmMeses = 1; //OK
        int custos = 0; //OK
        int anoFundacao = 2011; //OK
        int capitalSocial = 979957432; //OK
        int scorePontualidade = 1; //OK
        int risco = 1; //OK
        double margemBrutaAcumulada = 0.5543; //OK Corrigida
        float prazoMedioRecebimentoVendas = 15; //OK Corrigido
        float diferencaPercentualRisco = 0.75f; //OK Corrigido
        float percentualRisco = 0.25f; // OK Corrigido

        float limiteEmpresaAnaliseCredito = 43200f;
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
    public void onSuccess(List<Integer> parameter) {
        Log.d("DABUEK", "Classificação no Fragment: "+parameter.get(0));
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro no fragment: "+throwable.getMessage());
        progressBar.setVisibility(View.INVISIBLE);
    }
}