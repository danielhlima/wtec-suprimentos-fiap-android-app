package br.com.wtecsuprimentos.device.network.retrofit;

import java.util.List;

import br.com.wtecsuprimentos.device.network.retrofit.services.ClassifyCustomerService;
import br.com.wtecsuprimentos.domain.entities.Customer;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit build;

    public RetrofitConfig() {
        build = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.172:7071/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ClassifyCustomerService getClassifyCustomerService(){
        return this.build.create(ClassifyCustomerService.class);
    }

}
