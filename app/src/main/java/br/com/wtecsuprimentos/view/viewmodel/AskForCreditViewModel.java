package br.com.wtecsuprimentos.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.presenter.AskForCreditPresenter;
import br.com.wtecsuprimentos.view.bus.AskForCreditView;

public class AskForCreditViewModel extends AndroidViewModel implements AskForCreditView {

    private AskForCreditPresenter presenter;
    private DataOut.Callback<Double> callback;

    public AskForCreditViewModel(@NonNull Application application) {
        super(application);
        presenter = new AskForCreditPresenter(application.getApplicationContext(), this);
    }

    @Override
    public void askForCredit(Customer customer, DataOut.Callback<Double> callback, double value) {
        this.callback = callback;
        presenter.askForCredit(customer, value);
    }

    @Override
    public void onSuccess(Double parameter) {
        callback.onSuccess(parameter);
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onError(throwable);
    }
}
