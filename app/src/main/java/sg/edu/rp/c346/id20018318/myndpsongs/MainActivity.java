package sg.edu.rp.c346.id20018318.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int selectedId = rgStars.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                int stars = Integer.parseInt(selectedButton.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, stars);
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();

                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                rgStars.clearCheck();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SongList.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String title = etTitle.getText().toString();
        String singers = etSingers.getText().toString();
        String year = etYear.getText().toString();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("title", title);
        prefEdit.putString("singers", singers);
        prefEdit.putString("year", year);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String title = prefs.getString("title", "");
        String singers = prefs.getString("singers", "");
        String year = prefs.getString("year", "");

        etTitle.setText(title);
        etSingers.setText(singers);
        etYear.setText(year);
    }
}