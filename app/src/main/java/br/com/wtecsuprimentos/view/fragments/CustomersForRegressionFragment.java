package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataOut;
import br.com.wtecsuprimentos.domain.entities.Customer;
import br.com.wtecsuprimentos.view.adapters.ClickRecyclerViewHelper;
import br.com.wtecsuprimentos.view.adapters.CustomerAdapter;
import br.com.wtecsuprimentos.view.viewmodel.GetAllCustomersViewModel;

public class CustomersForRegressionFragment extends Fragment implements DataOut.Callback<List<Customer>>, ClickRecyclerViewHelper {

    private GetAllCustomersViewModel getAllCustomersViewModel;
    private RecyclerView recyclerView;
    private CustomerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Customer> customers;
    private Customer customer;

    public CustomersForRegressionFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCustomersViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity()
                .getApplication())
                .create(GetAllCustomersViewModel.class);
        retrieveAllCustomers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customers_for_regression, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rcv_customers_to_regression);

        setupRecyclerViewLayoutManager();
        return view;
    }

    public void setupRecyclerViewLayoutManager(){
        int scrollPosition = 0;

        if(recyclerView.getLayoutManager() != null){
            scrollPosition = ((LinearLayoutManager)recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

    private void retrieveAllCustomers(){
        getAllCustomersViewModel.getAllCustomers(this);
    }

    @Override
    public void onSuccess(List<Customer> customers) {
        this.customers = customers;
        mAdapter = new CustomerAdapter(customers, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro ao recuperar customers: "+throwable.getMessage());
    }

    @Override
    public void onCustomClick(Object object) {
        customer = (Customer)object;
        Log.d("DABUEK", "Cliente clicado: "+customer.getRazaoSocial());

        CustomersForRegressionFragmentDirections.ActionCustomersForRegressionFragmentToAskForCreditFragment actionDirection = CustomersForRegressionFragmentDirections.actionCustomersForRegressionFragmentToAskForCreditFragment();
        actionDirection.setCustomer(customer);
        Navigation.findNavController(getActivity(), R.id.nav_host).navigate(actionDirection);
    }
}