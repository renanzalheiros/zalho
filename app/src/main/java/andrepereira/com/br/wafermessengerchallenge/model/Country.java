package andrepereira.com.br.wafermessengerchallenge.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
