package andrepereira.com.br.wafermessengerchallenge.ui.countries.adapter;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import andrepereira.com.br.wafermessengerchallenge.model.Country;

public class CountryHolderViewModel extends ViewModel {

    public ObservableField<Country> country = new ObservableField<>();

    CountryHolderViewModel(Country country) {
        this.country.set(country);
    }

    public Country getCountry() {
        return country.get();
    }

    public void setCountry(Country country) {
        this.country.set(country);
    }
}
