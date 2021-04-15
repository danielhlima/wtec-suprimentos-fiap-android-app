package br.com.wtecsuprimentos.view.fragments;

import android.graphics.Color;
import android.icu.number.FormattedNumber;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.santalu.maskara.widget.MaskEditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.validator.TextValidator;
import br.com.wtecsuprimentos.view.viewmodel.AskForCreditViewModel;
import faranjit.currency.edittext.CurrencyEditText;


public class AskForCreditFragment extends Fragment implements DataOut.Callback<Double> {

    private AskForCreditViewModel viewModel;
    private LinearLayout progressBar;
    private ScrollView scrollView;


    private EditText etNome, etMaiorAtraso, etPeriodoDemonstrativoEmMeses, etAnoFundacao, etScorePontualidade,
            etPrazoMedioRecebimentoVendas;
    private CurrencyEditText etFaturamentoBruto, etMargemBruta, etTitulosEmAberto, etCapitalSocial, etCustos, etLimiteEmpresaAnaliseCredito, editTextValor;
    private MaskEditText etMargemBrutaAcumulada, etDiferencaPercentualRisco, etPercentualRisco;

    private boolean etNomeValidated, etMaiorAtrasoValidated, etTitulosEmAbertoValidated,
            etFaturamentoBrutoValidated, etMargemBrutaValidated, etPeriodoDemonstrativoEmMesesValidated,
            etCustosValidated, etAnoFundacaoValidated, etCapitalSocialValidated, etScorePontualidadeValidated,
            etPrazoMedioRecebimentoVendasValidated, etLimiteEmpresaAnaliseCreditoValidated, editTextValorValidated;
    private int risco = 1, microempresa = 1, restricao = 0;

    private Customer customer;

    public AskForCreditFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Bundle bundle = getArguments();
            customer = (Customer) bundle.getSerializable("customer");
            Log.d("DABUEK", "Customer null? "+(customer == null));
        }
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

        progressBar = (LinearLayout)view.findViewById(R.id.ll_progressBar_regression);
        scrollView = (ScrollView)view.findViewById(R.id.sv_form_regression);

        etNome = (EditText)view.findViewById(R.id.editTextTextPersonName_regression);
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


        etMaiorAtraso = (EditText)view.findViewById(R.id.editTextMaiorAtraso_regression);
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

        editTextValor = (CurrencyEditText)view.findViewById(R.id.editTextValor);
        editTextValor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (editTextValor.getText().toString().length() < 1){
                        editTextValor.setTextColor(Color.parseColor("#FF0000"));
                        editTextValor.setText(getString(R.string.erro_valor));
                        Toast.makeText(getContext(), R.string.erro_valor, Toast.LENGTH_LONG).show();
                        editTextValorValidated = false;
                    }else{
                        editTextValorValidated = true;
                    }
                }else{
                    if(editTextValor.getText().toString().length() > 0 &&
                            editTextValor.getText().toString().equalsIgnoreCase(getString(R.string.erro_valor))){
                        editTextValor.setText("");
                        editTextValor.setTextColor(getResources().getColor(R.color.primaryDarkColor));
                        editTextValorValidated = false;
                    }else{
                        editTextValorValidated = true;
                    }
                }
            }
        });

        etTitulosEmAberto = (CurrencyEditText)view.findViewById(R.id.editTextTitulosEmAberto_regression);
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


        etFaturamentoBruto = (CurrencyEditText) view.findViewById(R.id.editTextFaturamentoBruto_regression);
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


        etMargemBruta = (CurrencyEditText) view.findViewById(R.id.editTextMargemBruta_regression);
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


        etPeriodoDemonstrativoEmMeses = (EditText)view.findViewById(R.id.editTextPeriodoDemonstrativoEmMeses_regression);
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


        etCustos = (CurrencyEditText)view.findViewById(R.id.editTextCustos_regression);
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


        etAnoFundacao = (EditText)view.findViewById(R.id.editTextAnoFundacao_regression);
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


        etCapitalSocial = (CurrencyEditText)view.findViewById(R.id.editTextCapitalSocial_regression);
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


        etScorePontualidade = (EditText)view.findViewById(R.id.editTextNumberScorePontualidade_regression);
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


        etLimiteEmpresaAnaliseCredito = (CurrencyEditText) view.findViewById(R.id.editTextLimiteEmpresaAnaliseCredito_regression);
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


        etPrazoMedioRecebimentoVendas = (EditText)view.findViewById(R.id.editTextPrazoMedioRecebimentoVendas_regression);
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


        etMargemBrutaAcumulada = (MaskEditText)view.findViewById(R.id.editTextMargemBrutaAcumulada_regression);
        etDiferencaPercentualRisco = (MaskEditText)view.findViewById(R.id.editTextDiferencaPercentualRisco_regression);
        etPercentualRisco = (MaskEditText)view.findViewById(R.id.editTextPercentualRisco_regression);

        ((RadioGroup)view.findViewById(R.id.radioGroupRisco_regression)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonAltoRisco_regression:
                        risco = 4;
                        break;

                    case R.id.radioButtonMedioRisco_regression:
                        risco = 3;
                        break;

                    case R.id.radioButtonBaixoRisco_regression:
                        risco = 2;
                        break;

                    case R.id.radioButtonMuitoBaixoRisco:
                        risco = 1;
                        break;
                }
            }
        });


        ((RadioGroup)view.findViewById(R.id.radioGroupEmpresaME_regression)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonEmpresaMESim_regression:
                        microempresa = 1;
                        break;

                    case R.id.radioButtonEmpresaMENao_regression:
                        microempresa = 0;
                        break;
                }
            }
        });


        ((RadioGroup)view.findViewById(R.id.radioGroupRestricao_regression)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonRestricaoSim_regression:
                        restricao = 1;
                        break;

                    case R.id.radioButtonRestricaoNao_regression:
                        restricao = 0;
                        break;
                }
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);
        verifyAutoFillIfIsNeeded();
    }

    private void verifyAutoFillIfIsNeeded() {

        if(customer != null){
            etNome.setText(customer.getRazaoSocial());
            etMaiorAtraso.setText(customer.getMaiorAtraso()+"");
            etTitulosEmAberto.setText(customer.getTitulosEmAberto()*100+"");
            etFaturamentoBruto.setText(customer.getFaturamentoBruto()*100+"");
            etPeriodoDemonstrativoEmMeses.setText(customer.getPeriodoDemonstrativoEmMeses()+"");
            etMargemBruta.setText(customer.getMargemBruta()*100+"");
            etCustos.setText(customer.getCustos()*100+"");
            etAnoFundacao.setText(customer.getAnoFundacao()+"");
            etCapitalSocial.setText(customer.getCapitalSocial()*100+"");
            etScorePontualidade.setText(customer.getScorePontualidade()+"");
            etPrazoMedioRecebimentoVendas.setText(customer.getPrazoMedioRecebimentoVendas()+"");
            etLimiteEmpresaAnaliseCredito.setText(customer.getLimiteEmpresaAnaliseCredito()*100+"");
            risco = customer.getRisco();

            switch (risco){
                case 1:
                    ((RadioButton)getView().findViewById(R.id.radioButtonBaixoRisco_regression)).setChecked(true);
                    break;

                case 2:
                    ((RadioButton)getView().findViewById(R.id.radioButtonMedioRisco_regression)).setChecked(true);
                    break;

                case 3:
                    ((RadioButton)getView().findViewById(R.id.radioButtonMuitoBaixoRisco_regression)).setChecked(true);
                    break;

                case 4:
                    ((RadioButton)getView().findViewById(R.id.radioButtonAltoRisco_regression)).setChecked(true);
                    break;
            }

            microempresa = customer.getEmrpesaME();
            if(microempresa==0){
                ((RadioButton)getView().findViewById(R.id.radioButtonEmpresaMESim_regression)).setChecked(true);
            }else{
                ((RadioButton)getView().findViewById(R.id.radioButtonEmpresaMENao_regression)).setChecked(true);
            }

            restricao = customer.getRestricao();
            if(restricao==0){
                ((RadioButton)getView().findViewById(R.id.radioButtonRestricaoSim_regression)).setChecked(true);
            }else{
                ((RadioButton)getView().findViewById(R.id.radioButtonRestricaoNao_regression)).setChecked(true);
            }

            etMargemBrutaAcumulada.setText(customer.getMargemBrutaAcumulada()+"%");
            etDiferencaPercentualRisco.setText(customer.getDiferencaPercentualRisco()+"%");
            etPercentualRisco.setText(customer.getPercentualRisco()+"%");
            customer = null;
            etNomeValidated = true;
            etMaiorAtrasoValidated = true;
            etTitulosEmAbertoValidated = true;
            etFaturamentoBrutoValidated = true;
            etMargemBrutaValidated = true;
            etPeriodoDemonstrativoEmMesesValidated = true;
            etCustosValidated = true;
            etAnoFundacaoValidated = true;
            etCapitalSocialValidated = true;
            etScorePontualidadeValidated = true;
            etPrazoMedioRecebimentoVendasValidated = true;
            etLimiteEmpresaAnaliseCreditoValidated = true;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_frag_regress, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){

            case R.id.menu_item_listar_regression:
                Navigation.findNavController(getView()).navigate(R.id.action_askForCreditFragment_to_customersForRegressionFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void askForCredit() {
        if(areFieldsValidated()){
            try{
                progressBar.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.INVISIBLE);
                customer = createCustomer();
                if (customer != null) {
                    List<Customer> customers = new ArrayList<Customer>();
                    customers.add(customer);
                    double value = editTextValor.getCurrencyDouble();
                    viewModel.askForCredit(customer, this, value);
                }
            }catch (Exception e){
                Log.d("DABUEK", "Exceção criando Customer: "+e.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(), R.string.erro_validacao, Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private boolean areFieldsValidated() {
        return (etNomeValidated && etMaiorAtrasoValidated && etTitulosEmAbertoValidated &&
                etFaturamentoBrutoValidated && etMargemBrutaValidated && etPeriodoDemonstrativoEmMesesValidated &&
                etCustosValidated && etAnoFundacaoValidated && etCapitalSocialValidated && etScorePontualidadeValidated &&
                etPrazoMedioRecebimentoVendasValidated &&
                etLimiteEmpresaAnaliseCreditoValidated && editTextValorValidated);
    }

    private Customer createCustomer() throws ParseException {

        float diferencaPercentualRisco = Float.parseFloat(etDiferencaPercentualRisco.getUnMasked()+"");
        float percentualRisco = Float.parseFloat(etPercentualRisco.getUnMasked()+"");
        double margemBrutaAcumulada = Double.parseDouble(etMargemBrutaAcumulada.getUnMasked()+"");
        long faturamentoBruto = (long) etFaturamentoBruto.getCurrencyDouble();
        long titulosEmAberto = (long) etTitulosEmAberto.getCurrencyDouble();
        long margemBruta = (long) etMargemBruta.getCurrencyDouble();
        long custos = (long) etCustos.getCurrencyDouble();
        long capitalSocial = (long) etCapitalSocial.getCurrencyDouble();
        long limiteEmppresa = (long) etLimiteEmpresaAnaliseCredito.getCurrencyDouble();

        Customer c = new Customer(etNome.getText().toString(),
                Integer.parseInt(etMaiorAtraso.getText().toString()),
                titulosEmAberto,
                faturamentoBruto,
                margemBruta,
                Integer.parseInt(etPeriodoDemonstrativoEmMeses.getText().toString()),
                custos,
                Integer.parseInt(etAnoFundacao.getText().toString()),
                capitalSocial,
                Integer.parseInt(etScorePontualidade.getText().toString()),
                risco, Integer.parseInt(etPrazoMedioRecebimentoVendas.getText().toString()),
                diferencaPercentualRisco,
                percentualRisco,
                margemBrutaAcumulada,
                limiteEmppresa, microempresa, restricao, -1);
        return c;
    }


    @Override
    public void onSuccess(Double parameter) {
        Log.d("DABUEK", "Regressão no Fragment: "+parameter);
        progressBar.setVisibility(View.INVISIBLE);
        scrollView.setVisibility(View.VISIBLE);

        Locale brazil = new Locale("pt", "BR");
        final DecimalFormatSymbols REAL = new DecimalFormatSymbols(brazil);
        final DecimalFormat moeda = new DecimalFormat("¤ ###,###,##0.00",REAL);

        String valor = moeda.format(parameter);

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.alert_regression, null);
        ((TextView)view.findViewById(R.id.tv_alert_customer_name_regression)).setText(customer.getRazaoSocial());
        ((TextView)view.findViewById(R.id.tv_alert_credit_text)).setText("Valor do crédito concedido: "+valor);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        AlertDialog alert = builder.create();
        alert.show();

        view.findViewById(R.id.bt_alert_dismiss_regression).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
    }

    @Override
    public void onError(Throwable throwable) {
        progressBar.setVisibility(View.INVISIBLE);
        scrollView.setVisibility(View.VISIBLE);
        Log.d("DABUEK", "Erro de regressão no fragment: "+throwable.getMessage());
    }
}