package andrepereira.com.br.wafermessengerchallenge.service;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.delegate.CountriesTaskDelegate;
import andrepereira.com.br.wafermessengerchallenge.delegate.FetchCountriesDelegate;
import andrepereira.com.br.wafermessengerchallenge.model.Country;

public class CountryService {

    public void fetchCountries(final FetchCountriesDelegate fetchCountriesDelegate) {
        new FetchCountriesTask(new CountriesTaskDelegate() {
            @Override
            public void submit(String json) {
                try {
                    fetchCountriesDelegate.bind(parseCountriesListJson(json));
                } catch (JSONException e) {
                    e.printStackTrace();
                    fetchCountriesDelegate.bind(null);
                }
            }
        }).execute();
    }

    private List<Country> parseCountriesListJson(@NonNull String json) throws JSONException {
        List<Country> countries = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject countryJson = jsonArray.getJSONObject(i);
            String countryName = countryJson.getString("name");
            JSONArray currenciesJsonArray = countryJson.getJSONArray("currencies");
            JSONArray languagesJsonArray = countryJson.getJSONArray("languages");

            List<String> currencies = getCurrenciesFromJson(currenciesJsonArray);
            List<String> languages = getLanguagesFromJson(languagesJsonArray);

            countries.add(new Country(countryName, currencies, languages));
        }
        return countries;
    }

    private List<String> getCurrenciesFromJson(@NonNull JSONArray currenciesJsonArray) throws JSONException {
        List<String> currencies = new ArrayList<>();
        for(int i = 0; i < currenciesJsonArray.length(); i++) {
            JSONObject currencyJson = currenciesJsonArray.getJSONObject(i);
            String currency = currencyJson.getString("name");
            currencies.add(currency);
        }
        return currencies;
    }

    private List<String> getLanguagesFromJson(@NonNull JSONArray languagesJsonArray) throws JSONException {
        List<String> languages = new ArrayList<>();
        for (int i = 0; i < languagesJsonArray.length(); i++) {
            JSONObject languageJson = languagesJsonArray.getJSONObject(i);
            String language = languageJson.getString("name");
            languages.add(language);
        }
        return languages;
    }

    private static class FetchCountriesTask extends AsyncTask<Void, Void, Void> {

        private final CountriesTaskDelegate countriesDelegate;

        FetchCountriesTask(CountriesTaskDelegate countriesDelegate) {
            this.countriesDelegate = countriesDelegate;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            StringBuilder json = new StringBuilder("");
            try {
                URL url = new URL("https://restcountries.eu/rest/v2/all");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                countriesDelegate.submit(json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
