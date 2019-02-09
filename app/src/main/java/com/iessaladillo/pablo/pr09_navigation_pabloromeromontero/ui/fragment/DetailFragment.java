package com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.R;
import com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.databinding.DetalleFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private DetalleFragmentBinding b;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void setupToolbar() {
        b.toolbarDetailFragment.setTitle(R.string.detailFragment_lblTitle);
        b.toolbarDetailFragment.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        b.toolbarDetailFragment.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.detalle_fragment, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
        setupFab();
    }

    private void setupFab() {
        b.detailFragmentFab.setOnClickListener(v -> Toast.makeText(requireContext(), "Click on FabBotton", Toast.LENGTH_SHORT).show());
    }

}
