package com.tens.mahjongessential.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.tens.mahjongessential.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button button1;
    TextView textView1;
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button1 =getView().findViewById(R.id.dice_button);
        textView1 =getView().findViewById(R.id.home_number);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=0;
                i = (int)(Math.random()*16+3);
                textView1.setText(i+" ");
            }
        });
        getView().findViewById(R.id.tsumoButton1).
                setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_calculationFragment));
        getView().findViewById(R.id.tsumoButton2).
                setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_calculationFragment));
        getView().findViewById(R.id.tsumoButton3).
                setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_calculationFragment));
        getView().findViewById(R.id.tsumoButton4).
                setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_calculationFragment));
    }
}