package br.com.wtecsuprimentos.device.repositories;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.device.network.retrofit.RetrofitConfig;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.ClassifyCustomerRepository;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class ClassifyCustomerRepositoryImpl implements ClassifyCustomerRepository {

    @Override
    public void classify(List<Customer> customers, DataOut.Callback<List<Integer>> callback) {

        RequestBody customersToClassify = listToRequestBody(customers);

        Call<List<Integer>> call = new RetrofitConfig(true).getClassifyCustomerService().classify(customersToClassify);
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

    private RequestBody listToRequestBody(List<Customer> customers){
        JsonObject customersToClassify = new JsonObject();

        try {
            JsonArray outsideArray = new JsonArray();
            JsonArray insideArray = new JsonArray();

            Customer c = customers.get(0);
            insideArray.add(c.getMaiorAtraso()); //OK
            insideArray.add(c.getMargemBrutaAcumulada()); //OK
            insideArray.add(c.getPrazoMedioRecebimentoVendas()); //OK
            insideArray.add(c.getTitulosEmAberto()); // OK
            insideArray.add(c.getDiferencaPercentualRisco()); //OK
            insideArray.add(c.getPercentualRisco()); //OK
            insideArray.add(c.getFaturamentoBruto()); //OK
            insideArray.add(c.getMargemBruta()); //OK
            insideArray.add(c.getPeriodoDemonstrativoEmMeses()); //OK
            insideArray.add(c.getCustos()); //OK
            insideArray.add(c.getAnoFundacao()); //OK
            insideArray.add(c.getCapitalSocial()); //OK
            insideArray.add(c.getScorePontualidade()); //OK

            switch (c.getRisco()){
                case 4:
                    insideArray.add(0);
                    insideArray.add(0);
                    insideArray.add(0);
                    break;

                case 3:
                    insideArray.add(1);
                    insideArray.add(0);
                    insideArray.add(0);
                    break;

                case 2:
                    insideArray.add(0);
                    insideArray.add(1);
                    insideArray.add(0);
                    break;

                case 1:
                    insideArray.add(0);
                    insideArray.add(0);
                    insideArray.add(1);
                    break;
            }

            outsideArray.add(insideArray);
            customersToClassify.add("data", outsideArray);

            String jsonString = customersToClassify.toString();
            Log.d("DABUEK", "Resultado do json Classificação: "+jsonString);

        }catch(Exception e){

        }

        RequestBody rBody = RequestBody.create(MediaType.parse("application/json"), customersToClassify.toString());
        return rBody;
    }
}
