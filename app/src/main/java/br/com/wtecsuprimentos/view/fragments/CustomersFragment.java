package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.viewmodel.GetAllCustomersViewModel;

public class CustomersFragment extends Fragment implements DataOut.Callback<List<Customer>> {

    private GetAllCustomersViewModel getAllCustomersViewModel;

    public CustomersFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getAllCustomersViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity()
                .getApplication())
                .create(GetAllCustomersViewModel.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customers, container, false);
        ((Button)view.findViewById(R.id.bt_get_all_customers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveAllCustomers();
            }
        });
        return view;
    }

    private void retrieveAllCustomers(){
        getAllCustomersViewModel.getAllCustomers(this);
    }

    @Override
    public void onSuccess(List<Customer> customers) {
        for(Customer c : customers){
            Log.d("DABUEK", "Customer: "+c.toString());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro ao recuperar customers: "+throwable.getMessage());
    }
}