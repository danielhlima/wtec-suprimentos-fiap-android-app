package br.com.wtecsuprimentos.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.wtecsuprimentos.R;

public class ClassificationHomeFragment extends Fragment {

    public ClassificationHomeFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classification_home, container, false);

        ((Button)view.findViewById(R.id.bt_go_test_class)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_classificationHomeFragment_to_dest_classification);
            }
        });

        return view;
    }
}