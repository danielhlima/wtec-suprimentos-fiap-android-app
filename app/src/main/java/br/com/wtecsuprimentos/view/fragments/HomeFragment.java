package br.com.wtecsuprimentos.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.wtecsuprimentos.NavGraphDirections;
import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.device.repositories.RegisterCustomerRepositoryImpl;
import br.com.wtecsuprimentos.domain.bus.DataIn;
import br.com.wtecsuprimentos.domain.usecases.FirstDatabaseChargeUseCase;


public class HomeFragment extends Fragment implements DataIn.Callback {

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RegisterCustomerRepositoryImpl repository = new RegisterCustomerRepositoryImpl(getContext());
        FirstDatabaseChargeUseCase useCase = new FirstDatabaseChargeUseCase(repository, this);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FIRST_DATA_CHARGE", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("FIRST_CHARGE", "");

        if(!(result != null &&
           result.length() > 0 &&
           result.equalsIgnoreCase("FIRST_CHARGE"))){
            useCase.makeFisrtCharge();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((Button)view.findViewById(R.id.bt_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChooseSimulationFragment();
            }
        });
        return view;
    }

    private void goToChooseSimulationFragment() {
        Navigation.findNavController(getActivity(), R.id.nav_host).navigate(NavGraphDirections.globalActionDestChooseSimulation());
    }

    @Override
    public void onSuccess() {
        Log.d("DABUEK", "Sucesso First Charge");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FIRST_DATA_CHARGE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FIRST_CHARGE", "FIRST_CHARGE");
        editor.apply();
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("DABUEK", "Erro First Charge: "+throwable.getMessage());
    }
}