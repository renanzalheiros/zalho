package andrepereira.com.br.wafermessengerchallenge.delegate;

import java.util.List;

import andrepereira.com.br.wafermessengerchallenge.model.Country;

public interface FetchCountriesDelegate {
    void bind(List<Country> countries);
}
