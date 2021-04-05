package br.com.wtecsuprimentos.presenter;

import android.content.Context;
import android.os.Handler;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.device.repositories.ClassifyCustomerRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.ClassifyCustomerRepository;
import br.com.wtecsuprimentos.domain.usecases.ClassifyCustomerUseCase;

public class ClassifyCustomerPresenter implements DataOut.Callback<List<Integer>> {

    private ClassifyCustomerUseCase classifyCustomerUseCase;
    private ClassifyCustomerRepository classifyCustomerRepository;
    private Context context;
    private DataOut.Callback<List<Integer>> callback;
    private DataOut.Callback<List<Integer>> classifyCustomersCallBack = this;

    public ClassifyCustomerPresenter(Context context, DataOut.Callback<List<Integer>> callback) {
        this.context = context;
        this.callback = callback;
        classifyCustomerRepository = new ClassifyCustomerRepositoryImpl();
    }

    public void classifyCustomer(List<Customer> customersToClassify){
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                classifyCustomerRepository.classify(customersToClassify, classifyCustomersCallBack);
            }
        });
    }

    @Override
    public void onSuccess(List<Integer> parameter) {
        Handler handler = new Handler(context.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(parameter);
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onError(Throwable throwable) {
        Handler handler = new Handler(context.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onError(throwable);
            }
        };
        handler.post(runnable);
    }
}
