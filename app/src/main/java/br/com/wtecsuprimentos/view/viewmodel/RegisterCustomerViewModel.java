package br.com.wtecsuprimentos.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.presenter.RegisterCustomerPresenter;
import br.com.wtecsuprimentos.view.bus.RegisterCustomerView;

public class RegisterCustomerViewModel extends AndroidViewModel implements RegisterCustomerView {


    private RegisterCustomerPresenter registerCustomerPresenter;
    private DataIn.Callback callback;

    public RegisterCustomerViewModel(@NonNull Application application) {
        super(application);
        registerCustomerPresenter = new RegisterCustomerPresenter(application.getApplicationContext(), this);
    }

    @Override
    public void registerCustomer(Customer customer, DataIn.Callback callback) {
        this.callback = callback;
        registerCustomerPresenter.registerCustomer(customer);
    }

    @Override
    public void onSuccess() {
        callback.onSuccess();
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onError(throwable);
    }
}
