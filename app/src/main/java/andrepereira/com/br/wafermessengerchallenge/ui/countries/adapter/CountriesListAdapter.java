package andrepereira.com.br.wafermessengerchallenge.ui.countries.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.R;
import andrepereira.com.br.wafermessengerchallenge.databinding.AdapterCountryRowBinding;
import andrepereira.com.br.wafermessengerchallenge.model.Country;

public final class CountriesListAdapter extends RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder> {

    private final List<Country> countries;

    public CountriesListAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterCountryRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_country_row, viewGroup, false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        countryViewHolder.bindData(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private final AdapterCountryRowBinding countryView;

        CountryViewHolder(@NonNull AdapterCountryRowBinding binding) {
            super(binding.getRoot());
            this.countryView = binding;
        }

        void bindData(Country country) {
            countryView.setViewModel(new CountryHolderViewModel(country));
        }
    }
}
