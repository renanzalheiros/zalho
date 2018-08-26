package andrepereira.com.br.wafermessengerchallenge.ui.swipe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import andrepereira.com.br.wafermessengerchallenge.model.Country;
import andrepereira.com.br.wafermessengerchallenge.ui.countries.CountriesListFragmentViewModel;

public class DeleteBroadcast extends BroadcastReceiver {

    private final CountriesListFragmentViewModel countriesListFragmentViewModel;

    public DeleteBroadcast(CountriesListFragmentViewModel viewModel){
        this.countriesListFragmentViewModel = viewModel;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Country country = (Country) intent.getSerializableExtra("country");
        if(country != null) {
            countriesListFragmentViewModel.removeFromList(country);
        } else {
            int countryPosition = intent.getIntExtra("countryPosition", -1);
            countriesListFragmentViewModel.removeFromList(countryPosition);
        }
    }
}
