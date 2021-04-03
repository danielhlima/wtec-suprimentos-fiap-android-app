package br.com.wtecsuprimentos.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.presenter.GetAllCustomersPresenter;
import br.com.wtecsuprimentos.view.bus.GetAllCustomersView;

public class GetAllCustomersViewModel extends AndroidViewModel implements GetAllCustomersView {

    private GetAllCustomersPresenter getAllCustomersPresenter;
    private DataOut.Callback<List<Customer>> callback;

    public GetAllCustomersViewModel(@NonNull Application application) {
        super(application);
        getAllCustomersPresenter = new GetAllCustomersPresenter(application.getApplicationContext(), this);
    }

    @Override
    public void getAllCustomers(DataOut.Callback<List<Customer>> callback) {
        this.callback = callback;
        getAllCustomersPresenter.getAllCustomers();
    }

    @Override
    public void onSuccess(List<Customer> parameter) {
        callback.onSuccess(parameter);
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onError(throwable);
    }
}
