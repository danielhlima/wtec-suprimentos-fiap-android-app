package br.com.wtecsuprimentos.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.bus.DataIn;


public class RegisterCustomerFragment extends Fragment implements DataIn.Callback {

    public RegisterCustomerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_register_customer, container, false);
        return view;
    }

    private void registerCustomer(){

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}