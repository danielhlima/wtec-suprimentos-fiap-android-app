package br.com.wtecsuprimentos.presenter;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.device.repositories.GetAllCustomersRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.GetAllCustomersRepository;
import br.com.wtecsuprimentos.domain.usecases.GetAllCustomersUseCase;

public class GetAllCustomersPresenter implements DataOut.Callback<List<Customer>> {

    private GetAllCustomersUseCase getAllCustomersUseCase;
    private GetAllCustomersRepository getAllCustomersRepository;
    private Context context;
    private DataOut.Callback<List<Customer>> callback;
    private DataOut.Callback<List<Customer>> getAllCustomersCallback = this;

    public GetAllCustomersPresenter(Context context, DataOut.Callback<List<Customer>> callback) {
        this.context = context;
        this.callback = callback;
        getAllCustomersRepository = new GetAllCustomersRepositoryImpl(context);
    }
    
    public void getAllCustomers(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                getAllCustomersRepository.getAllCustomers(getAllCustomersCallback);
            }
        });
    }

    @Override
    public void onSuccess(List<Customer> parameter) {
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
