package br.com.wtecsuprimentos.device.network.retrofit;

import java.util.List;

import br.com.wtecsuprimentos.device.network.retrofit.services.AskForCreditService;
import br.com.wtecsuprimentos.device.network.retrofit.services.ClassifyCustomerService;
import br.com.wtecsuprimentos.domain.entities.Customer;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private String urlProdClassification = "https://classifierfunction.azurewebsites.net/";
    private String urlProdRegression = "https://httpregression.azurewebsites.net/";
    private String urlDev = "http://192.168.0.104:7071/";

    private Retrofit build;

    public RetrofitConfig(boolean classification) {
        if(classification){
            build = new Retrofit.Builder()
                    .baseUrl(urlProdClassification)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }else{
            build = new Retrofit.Builder()
                    .baseUrl(urlProdRegression)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
    }

    public ClassifyCustomerService getClassifyCustomerService(){
        return this.build.create(ClassifyCustomerService.class);
    }

    public AskForCreditService getAskForCreditService(){
        return this.build.create(AskForCreditService.class);
    }
}
