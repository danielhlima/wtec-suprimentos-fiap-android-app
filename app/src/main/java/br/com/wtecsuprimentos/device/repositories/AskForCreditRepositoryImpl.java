package br.com.wtecsuprimentos.device.repositories;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import br.com.wtecsuprimentos.device.network.retrofit.RetrofitConfig;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.AskForCreditRepository;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class AskForCreditRepositoryImpl implements AskForCreditRepository {

    @Override
    public void aksForCredit(Customer customer, double value, DataOut.Callback<Double> callback) {

        RequestBody askForCreditBody = createBody(customer, value);
        Call<Double> call = new RetrofitConfig(false).getAskForCreditService().askForCredit(askForCreditBody);
        call.enqueue(new retrofit2.Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if(response.code() != 200){
                    Log.d("DABUEK", "Erro retrofit no onSucess code != 200");
                    callback.onError(new Throwable("Erro na Regressão: "+response.code()));
                }else{
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("DABUEK", "Erro retrofit na regressão: "+t.getMessage());
                callback.onError(t);
            }
        });
    }

    private RequestBody createBody(Customer c, double value) {
        JsonObject customerRegression = new JsonObject();

        try {
            JsonArray outsideArray = new JsonArray();
            JsonArray insideArray = new JsonArray();

            //cluster
            insideArray.add(0);
            insideArray.add(0);
            insideArray.add(1);
            insideArray.add(0);
            ////////////////
            insideArray.add(c.getMaiorAtraso()); //OK
            insideArray.add(c.getMargemBrutaAcumulada()); //OK
            insideArray.add(c.getPrazoMedioRecebimentoVendas()); //OK
            insideArray.add(c.getTitulosEmAberto()); // OK
            //Valor Solicigtado
            insideArray.add(value);
            ////////////
            insideArray.add(c.getDiferencaPercentualRisco()); //OK
            insideArray.add(c.getPercentualRisco()); //OK
            insideArray.add(c.getFaturamentoBruto()); //OK
            insideArray.add(c.getMargemBruta()); //OK
            insideArray.add(c.getPeriodoDemonstrativoEmMeses()); //OK
            insideArray.add(c.getCustos()); //OK
            insideArray.add(c.getAnoFundacao()); //OK
            insideArray.add(c.getCapitalSocial()); //OK
            insideArray.add(c.getScorePontualidade()); //OK
            insideArray.add(c.getLimiteEmpresaAnaliseCredito());
            //Risco
            insideArray.add(1);
            insideArray.add(0);
            insideArray.add(0);
            //////////////////
            insideArray.add(c.getEmrpesaME());
            insideArray.add(c.getRestricao());
            outsideArray.add(insideArray);
            customerRegression.add("data", outsideArray);

            String jsonString = customerRegression.toString();
            Log.d("DABUEK", "Resultado do json Regressão: "+jsonString);

        }catch(Exception e){

        }
        RequestBody rBody = RequestBody.create(MediaType.parse("application/json"), customerRegression.toString());
        return rBody;
    }
}
