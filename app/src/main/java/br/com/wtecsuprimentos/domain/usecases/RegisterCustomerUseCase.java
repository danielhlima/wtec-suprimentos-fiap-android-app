package br.com.wtecsuprimentos.domain.usecases;

import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.RegisterCustomerRepository;

public class RegisterCustomerUseCase implements DataIn.Callback {

    private RegisterCustomerRepository registerCustomerRepository;
    private DataIn.Callback callback;

    public RegisterCustomerUseCase(RegisterCustomerRepository repository, DataIn.Callback callback){
        this.registerCustomerRepository = repository;
        this.callback = callback;
    }

    public void registerCustomer(Customer customer){
        if(registerCustomerRepository != null){
            registerCustomerRepository.registerCustomer(customer, this);
        }
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
