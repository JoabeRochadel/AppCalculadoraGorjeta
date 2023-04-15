package devandroid.joabe.calculadoragorjeta;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Locale locale = new Locale("pt", "BR");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private EditText editText_ValorConta;
    private TextView textView_Percentual, textView_Gorjeta, textView_Total;
    private SeekBar seekBar_Percentual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar_Percentual = findViewById(R.id.seekBar_Percentual);
        editText_ValorConta = findViewById(R.id.editText_ValorConta);
        textView_Percentual = findViewById(R.id.textView_Percentual);
        textView_Gorjeta = findViewById(R.id.textView_Gorjeta);
        textView_Total = findViewById(R.id.textView_Total);

        seekBar_Percentual.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double valorConta = 0;

                if (editText_ValorConta.getText().toString().length() != 0) {
                    valorConta = Double.parseDouble(editText_ValorConta.getText().toString());
                }

                Double valorGorjeta = (valorConta * (Double.valueOf(progress) / 100));
                Double valorContaFinal = valorGorjeta + valorConta;
                String valorGorjetaConvertido = currencyFormatter.format(valorGorjeta);
                String valorContaFinalConvertido = currencyFormatter.format(valorContaFinal);

                textView_Gorjeta.setText(valorGorjetaConvertido);
                textView_Total.setText(valorContaFinalConvertido);
                textView_Percentual.setText(String.format("%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}