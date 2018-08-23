package andrepereira.com.br.wafermessengerchallenge.ui.countries;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrepereira.com.br.wafermessengerchallenge.R;
import andrepereira.com.br.wafermessengerchallenge.databinding.FragmentCountriesListBinding;

public class CountriesListFragment extends Fragment {

    private CountriesListFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCountriesListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries_list, container, false);
        viewModel = new CountriesListFragmentViewModel();
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.start();
    }
}
