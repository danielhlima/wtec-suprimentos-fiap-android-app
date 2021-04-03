package br.com.wtecsuprimentos.domain.repository;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface GetAllCustomersRepository {

    void getAllCustomers(DataOut.Callback callback);
}
