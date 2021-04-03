package br.com.wtecsuprimentos.view.bus;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface GetAllCustomersView extends DataOut.Callback<List<Customer>> {

    void getAllCustomers(DataOut.Callback<List<Customer>> callback);
}
