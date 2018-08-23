package andrepereira.com.br.wafermessengerchallenge.ui.countries;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.model.Country;
import andrepereira.com.br.wafermessengerchallenge.ui.countries.adapter.CountriesListAdapter;

public class CountriesBindings {

    @BindingAdapter("countriesList")
    public static void bindCountriesList(RecyclerView recyclerView, List<Country> countries) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CountriesListAdapter(countries));
    }
}
