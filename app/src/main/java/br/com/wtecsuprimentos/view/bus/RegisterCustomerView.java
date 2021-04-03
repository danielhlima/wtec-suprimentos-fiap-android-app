package br.com.wtecsuprimentos.view.bus;

import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface RegisterCustomerView extends DataIn.Callback {
    void RegisterCustomer(Customer customer, DataIn.Callback callback);
}
