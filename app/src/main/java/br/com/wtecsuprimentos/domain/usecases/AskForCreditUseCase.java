package br.com.wtecsuprimentos.domain.usecases;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.AskForCreditRepository;

public class AskForCreditUseCase implements DataOut.Callback<Double> {

    private AskForCreditRepository repository;
    private DataOut.Callback<Double> callback;

    public AskForCreditUseCase(AskForCreditRepository repository, DataOut.Callback<Double> callback){
        this.repository = repository;
        this.callback = callback;
    }

    public void askForCredit(Customer customer, double value){
        repository.aksForCredit(customer, value, this);
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
