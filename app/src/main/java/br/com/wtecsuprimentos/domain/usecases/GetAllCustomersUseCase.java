package br.com.wtecsuprimentos.domain.usecases;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.GetAllCustomersRepository;

public class GetAllCustomersUseCase implements DataOut.Callback<List<Customer>> {

    private GetAllCustomersRepository getAllCustomersRepository;
    private DataOut.Callback callback;

    public GetAllCustomersUseCase(GetAllCustomersRepository getAllCustomersRepository, DataOut.Callback callback){
        this.getAllCustomersRepository = getAllCustomersRepository;
        this.callback = callback;
    }

    public void getAllCustomers(){
        if(getAllCustomersRepository != null){
            getAllCustomersRepository.getAllCustomers(this);
        }
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
