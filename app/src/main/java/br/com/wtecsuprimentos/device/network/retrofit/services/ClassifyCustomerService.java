package br.com.wtecsuprimentos.device.network.retrofit.services;

import org.json.JSONObject;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClassifyCustomerService {

    @POST("api/HttpClassifier")
    Call<List<Integer>> classify(@Body JSONObject customersToClassifys);
}
