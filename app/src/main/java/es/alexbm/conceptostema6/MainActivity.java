package es.alexbm.conceptostema6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSave, btnApply;
    private TextView textView;
    private EditText editText;
    private Switch switch1;
    private String text, textSaved;
    private boolean switchSaved;

    private static  final String SHARED_PREFS = "sharedPrefs";
    private static  final String TEXT = "sharedText";
    private static  final String SWITCH1 = "sharedSwitch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);
        btnSave = findViewById(R.id.btnSave);
        btnApply = findViewById(R.id.btnApply);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        btnApply.setOnClickListener(view -> {
            text = editText.getText().toString();
            textView.setText(text);
        });

        btnSave.setOnClickListener(view -> {
            saveData();
        });

        loadData();
        updateViews();
    }

    public void saveData(){
        SharedPreferences shared = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

        editor.putString(TEXT,textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();
        Toast.makeText(this, "DATA SAVED", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences shared = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        textSaved = shared.getString(TEXT,"");
        switchSaved = shared.getBoolean(SWITCH1,false);
    }

    private void updateViews() {
        textView.setText(textSaved);
        switch1.setChecked(switchSaved);
        //editText.setText("");
    }
}