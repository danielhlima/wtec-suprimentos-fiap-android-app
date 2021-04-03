package br.com.wtecsuprimentos.device.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.wtecsuprimentos.device.data.models.CustomerModel;

@Dao
public interface CustomerDao {

    @Insert
    void insertCustomer(CustomerModel customerModel);

    @Query("SELECT * FROM customer")
    List<CustomerModel> getAllCustomers();
}
