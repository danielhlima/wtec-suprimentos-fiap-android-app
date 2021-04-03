package br.com.wtecsuprimentos.domain.repository;

import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface RegisterCustomerRepository {
    void registerCustomer(Customer customer, DataIn.Callback callback);
}
