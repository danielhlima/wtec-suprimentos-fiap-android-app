package br.com.wtecsuprimentos.device.network.retrofit.services;

import com.google.gson.JsonObject;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClassifyCustomerService {

    @POST("api/HttpClassifier")
    Call<List<Integer>> classify(@Body RequestBody customersToClassify);
}
