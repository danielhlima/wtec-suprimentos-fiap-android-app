package br.com.wtecsuprimentos.device.network.retrofit.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AskForCreditService {

    @POST("api/HttpRegression")
    Call<Double> askForCredit(@Body RequestBody ask);
}
