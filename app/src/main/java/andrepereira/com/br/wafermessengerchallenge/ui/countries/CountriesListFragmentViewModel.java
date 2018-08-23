package andrepereira.com.br.wafermessengerchallenge.ui.countries;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.delegate.FetchCountriesDelegate;
import andrepereira.com.br.wafermessengerchallenge.model.Country;
import andrepereira.com.br.wafermessengerchallenge.service.CountryService;

public class CountriesListFragmentViewModel extends ViewModel {

    private CountryService service = new CountryService();

    //Observables can't be privates, so databinding can work
    public ObservableBoolean jsonError = new ObservableBoolean(false);
    public ObservableField<List<Country>> countriesList = new ObservableField<List<Country>>(new ArrayList<Country>());

    public void start() {
        service.fetchCountries(new FetchCountriesDelegate() {
            @Override
            public void show(List<Country> countries) {
                //Clear countries list before populating with service response
                countriesList.set(new ArrayList<Country>());
                if(countries == null) {
                    jsonError.set(true);
                } else {
                    countriesList.set(countries);
                }
            }
        });
    }
}
