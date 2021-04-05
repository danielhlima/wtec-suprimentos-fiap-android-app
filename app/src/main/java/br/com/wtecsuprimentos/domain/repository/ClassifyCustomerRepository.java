package br.com.wtecsuprimentos.domain.repository;

import org.json.JSONObject;
import java.util.List;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;

public interface ClassifyCustomerRepository {
    void classify(List<Customer> customersToClassify, DataOut.Callback<List<Integer>> callback);
}
