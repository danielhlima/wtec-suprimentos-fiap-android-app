package br.com.wtecsuprimentos.device.repositories;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.device.network.retrofit.RetrofitConfig;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.ClassifyCustomerRepository;
import retrofit2.Call;
import retrofit2.Response;

public class ClassifyCustomerRepositoryImpl implements ClassifyCustomerRepository {

    @Override
    public void classify(List<Customer> customers, DataOut.Callback<List<Integer>> callback) {

        JSONObject customersToClassify = listToJSON(customers);

        Call<List<Integer>> call = new RetrofitConfig().getClassifyCustomerService().classify(customersToClassify);
        Log.d("DABUEK",call.request().body().toString());
        call.enqueue(new retrofit2.Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.code() != 200){
                    Log.d("DABUEK", "Erro retrofit");
                    callback.onError(new Throwable("Erro no Classify: "+response.code()));
                }else{
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.d("DABUEK", "Erro retrofit classificando: "+t.getMessage());
                callback.onError(t);
            }
        });

    }

    private JSONObject listToJSON(List<Customer> customers){
        JSONObject customersToClassify = new JSONObject();

        try
        {
            try {
                JSONArray outsideArray = new JSONArray();
                JSONArray insideArray = new JSONArray();

                Customer c = createTestCustomer();
                insideArray.put(c.getMaiorAtraso()); //OK
                insideArray.put(c.getMargemBrutaAcumulada()); //OK
                insideArray.put(c.getPrazoMedioRecebimentoVendas()); //OK
                insideArray.put(c.getTitulosEmAberto()); // OK
                insideArray.put(c.getDiferencaPercentualRisco()); //OK
                insideArray.put(c.getPercentualRisco()); //OK
                insideArray.put(c.getFaturamentoBruto()); //OK
                insideArray.put(c.getMargemBruta()); //OK
                insideArray.put(c.getPeriodoDemonstrativoEmMeses()); //OK
                insideArray.put(c.getCustos()); //OK
                insideArray.put(c.getAnoFundacao()); //OK
                insideArray.put(c.getCapitalSocial()); //OK
                insideArray.put(c.getScorePontualidade()); //OK
                insideArray.put(1);
                insideArray.put(0);
                insideArray.put(0);
                outsideArray.put(insideArray);
                customersToClassify.put("data", outsideArray);

                String jsonString = customersToClassify.toString();
                Log.d("DABUEK", "Resultado do json: "+jsonString);

            }catch(Exception e){

            }
        }catch (Exception e){

        }

        return customersToClassify;
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
}
