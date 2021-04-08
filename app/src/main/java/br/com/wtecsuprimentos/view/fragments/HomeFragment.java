package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.wtecsuprimentos.NavGraphDirections;
import br.com.wtecsuprimentos.R;


public class HomeFragment extends Fragment {

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}