package br.com.wtecsuprimentos.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.presenter.ClassifyCustomerPresenter;
import br.com.wtecsuprimentos.view.bus.ClassifyCustomerView;

public class ClassifyCustomerViewModel extends AndroidViewModel implements ClassifyCustomerView {

    private ClassifyCustomerPresenter classifyCustomerPresenter;
    private DataOut.Callback<List<Integer>> callback;

    public ClassifyCustomerViewModel(@NonNull Application application) {
        super(application);
        classifyCustomerPresenter = new ClassifyCustomerPresenter(application.getApplicationContext(), this);
    }

    @Override
    public void classifyCustomer(List<Customer> customersToClassify, DataOut.Callback<List<Integer>> callback) {
        this.callback = callback;
        classifyCustomerPresenter.classifyCustomer(customersToClassify);
    }

    @Override
    public void onSuccess(List<Integer> parameter) {
        callback.onSuccess(parameter);
    }

    @Override
    public void onError(Throwable throwable) {
        callback.onError(throwable);
    }
}
