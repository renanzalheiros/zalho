package andrepereira.com.br.wafermessengerchallenge.ui.countries;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.model.Country;
import andrepereira.com.br.wafermessengerchallenge.ui.countries.adapter.CountriesListAdapter;
import andrepereira.com.br.wafermessengerchallenge.ui.swipe.AfterSwipedCallback;
import andrepereira.com.br.wafermessengerchallenge.ui.swipe.SwipeController;

public class CountriesBindings {

    @BindingAdapter("countriesList")
    public static void bindCountriesList(final RecyclerView recyclerView, final List<Country> countries) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final CountriesListAdapter countriesAdapter = new CountriesListAdapter(countries);
        recyclerView.setAdapter(countriesAdapter);

        AfterSwipedCallback afterSwipedCallback = new AfterSwipedCallback() {
            @Override
            public void afterSwiped(int positionRemoved) {
                Intent intent = new Intent();
                intent.setAction("deleteCountry");
                intent.putExtra("countryPosition", positionRemoved);
                recyclerView.getContext().sendBroadcast(intent);
            }
        };

        final SwipeController swipeController = new SwipeController(recyclerView, afterSwipedCallback);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    @BindingAdapter("deleteClick")
    public static void deleteCountryClick(final ImageView imageView, final Country country) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("deleteCountry");
                intent.putExtra("country", country);
                imageView.getContext().sendBroadcast(intent);
            }
        });
    }
}
