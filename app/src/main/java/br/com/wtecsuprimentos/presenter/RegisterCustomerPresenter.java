package br.com.wtecsuprimentos.presenter;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.device.repositories.RegisterCustomerRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.RegisterCustomerRepository;
import br.com.wtecsuprimentos.domain.usecases.RegisterCustomerUseCase;

public class RegisterCustomerPresenter implements DataIn.Callback {

    private RegisterCustomerUseCase registerCustomerUseCase;
    private RegisterCustomerRepository registerCustomerRepository;
    private Context context;
    private DataIn.Callback callback;
    private DataIn.Callback registerCustomerCallBack = this;

    public RegisterCustomerPresenter(Context context, DataIn.Callback callback){
        this.context = context;
        registerCustomerRepository = new RegisterCustomerRepositoryImpl(context);
        this.callback = callback;
    }

    public void registerCustomer(Customer customer){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                registerCustomerRepository.registerCustomer(customer, registerCustomerCallBack);
            }
        });

    }

    @Override
    public void onSuccess() {
        Handler handler = new Handler(context.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
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
