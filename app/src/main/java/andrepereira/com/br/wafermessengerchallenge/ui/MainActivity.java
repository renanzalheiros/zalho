package andrepereira.com.br.wafermessengerchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import andrepereira.com.br.wafermessengerchallenge.R;
import andrepereira.com.br.wafermessengerchallenge.ui.countries.CountriesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, new CountriesListFragment())
                .commit();
    }
}
