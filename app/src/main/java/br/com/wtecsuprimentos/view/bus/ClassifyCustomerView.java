package br.com.wtecsuprimentos.view.bus;

import java.util.List;

import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface ClassifyCustomerView extends DataOut.Callback<List<Integer>> {
    void classifyCustomer(List<Customer> customersToClassify, DataOut.Callback<List<Integer>> callback);
}
