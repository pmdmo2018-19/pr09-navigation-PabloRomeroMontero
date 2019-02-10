package com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.R;
import com.iessaladillo.pablo.pr09_navigation_pabloromeromontero.databinding.LoremFragmentBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Lorem_Fragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
//    private LoremFragmentBinding b;
    private SharedPreferences settings;
    private NavController navController;
    private TextView txtLorem;
    private FloatingActionButton fabButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private void setUpNavigator() {
        navController = NavHostFragment.findNavController(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savecInstanceState) {
        return inflater.inflate(R.layout.lorem_fragment, container, false);
    }

    private void setupViews() {
        fabButton = ActivityCompat.requireViewById(requireActivity(),R.id.initFragment_fab);
        txtLorem = ActivityCompat.requireViewById(requireActivity(), R.id.txtLorem);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpNavigator();
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        setupToolbar();
        setupViews();
        setupFab();
        changeText();
    }

    private void setupFab() {
        fabButton.setOnClickListener(v -> navController.navigate(R.id.action_lorem_Fragment2_to_detailFragment));
    }

    private void setupToolbar() {
        Toolbar toolbar = ActivityCompat.requireViewById(requireActivity(),R.id.toolbar);
        toolbar.setTitle(R.string.title_toolbar);
        toolbar.inflateMenu(R.menu.menu_fragment);
        toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.sharedPreferencesMenu) {
                navigateToSettings();
                return true;
            }
            return false;
        });

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);

    }

    private void navigateToSettings() {
        navController.navigate(R.id.action_lorem_Fragment2_to_preferenceFragment);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sharedPreferencesMenu) {
            navigateToSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, getString(R.string.lbltext_prefkey))) {
            changeText();
        }
    }

    private void changeText() {
        txtLorem.setText(settings.getString(getString(R.string.lbltext_prefkey), getString(R.string.main_latin_ipsum)));
    }

    @Override
    public void onResume() {
        super.onResume();
        settings.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
