package andrepereira.com.br.wafermessengerchallenge.model;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {

    private final String name;
    private final List<String> currencies;
    private final List<String> languages;

    public Country(String name, List<String> currencies, List<String> languages){
        this.name = name;
        this.currencies = currencies;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public List<String> getLanguages() {
        return languages;
    }

    @Override
    public String toString() {
        return name + "\t\t\t" + currencies.get(0) + "\t\t\t" + languages.get(0);
    }
}
