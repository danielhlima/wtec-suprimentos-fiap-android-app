package br.com.wtecsuprimentos.view.bus;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface AskForCreditView extends DataOut.Callback<Double> {
    void askForCredit(Customer customer, DataOut.Callback<Double> callback, double value);
}
