package br.com.wtecsuprimentos.device.repositories;

import android.content.Context;

import br.com.wtecsuprimentos.device.data.AppDatabase;
import br.com.wtecsuprimentos.device.data.models.CustomerModel;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.domain.repository.RegisterCustomerRepository;

public class RegisterCustomerRepositoryImpl implements RegisterCustomerRepository {

    private AppDatabase mDb;

    public RegisterCustomerRepositoryImpl(Context context) {
        mDb = AppDatabase.getInstance(context);
    }

    @Override
    public void registerCustomer(Customer customer, DataIn.Callback callback) {
        try{
            mDb.customerDao().insertCustomer(new CustomerModel((customer)));
            callback.onSuccess();
        }catch (Exception e) {
            callback.onError(e);
        }
    }
}
