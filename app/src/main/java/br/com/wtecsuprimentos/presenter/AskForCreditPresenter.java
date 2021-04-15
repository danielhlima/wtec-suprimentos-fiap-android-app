package br.com.wtecsuprimentos.presenter;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import br.com.wtecsuprimentos.device.executor.AppExecutors;
import br.com.wtecsuprimentos.device.repositories.AskForCreditRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.AskForCreditRepository;
import br.com.wtecsuprimentos.domain.usecases.AskForCreditUseCase;

public class AskForCreditPresenter implements DataOut.Callback<Double> {

    private AskForCreditUseCase useCase;
    private AskForCreditRepository repository;
    private Context context;
    private DataOut.Callback<Double> callback;

    public AskForCreditPresenter(Context context, DataOut.Callback<Double> callback) {
        this.context = context;
        this.callback = callback;
        repository = new AskForCreditRepositoryImpl();
        useCase = new AskForCreditUseCase(repository,this);
    }

    public void askForCredit(Customer customer, double value){
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                useCase.askForCredit(customer, value);
            }
        });
    }

    @Override
    public void onSuccess(Double parameter) {
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
