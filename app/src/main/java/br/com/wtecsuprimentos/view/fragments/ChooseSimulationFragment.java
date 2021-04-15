package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.wtecsuprimentos.R;


public class ChooseSimulationFragment extends Fragment {

    public ChooseSimulationFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_simulation, container, false);

        ((Button)view.findViewById(R.id.bt_go_class)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_chooseSimulationFragment_to_classificationHomeFragment);
            }
        });

        ((Button)view.findViewById(R.id.bt_go_regress)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_chooseSimulationFragment_to_regressionHomeFragment);
            }
        });

        return view;
    }
}