package br.com.wtecsuprimentos.domain.usecases;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.ClassifyCustomerRepository;

public class ClassifyCustomerUseCase implements DataOut.Callback<List<Integer>> {

    private ClassifyCustomerRepository classifyCustomerRepository;
    private DataOut.Callback<List<Integer>> callback;

    public ClassifyCustomerUseCase(ClassifyCustomerRepository classifyCustomerRepository, DataOut.Callback<List<Integer>> callback) {
        this.classifyCustomerRepository = classifyCustomerRepository;
        this.callback = callback;
    }

    public void classifyCustomers(List<Customer> customersToClassify){
        if(classifyCustomerRepository != null){
            classifyCustomerRepository.classify(customersToClassify, this);
        }
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
