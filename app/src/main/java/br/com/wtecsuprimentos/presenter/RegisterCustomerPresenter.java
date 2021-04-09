package br.com.wtecsuprimentos.presenter;

import android.content.Context;
import android.os.Handler;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.device.repositories.RegisterCustomerRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.RegisterCustomerRepository;
import br.com.wtecsuprimentos.domain.usecases.RegisterCustomerUseCase;

public class RegisterCustomerPresenter implements DataIn.Callback {

    private RegisterCustomerUseCase useCase;
    private RegisterCustomerRepository registerCustomerRepository;
    private Context context;
    private DataIn.Callback callback;

    public RegisterCustomerPresenter(Context context, DataIn.Callback callback){
        this.context = context;
        this.callback = callback;
        registerCustomerRepository = new RegisterCustomerRepositoryImpl(context);
        useCase = new RegisterCustomerUseCase(registerCustomerRepository, this);
    }

    public void registerCustomer(Customer customer){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                useCase.registerCustomer(customer);
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
