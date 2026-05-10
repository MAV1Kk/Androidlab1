package com.example.lab1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner writerList;
    private RadioGroup publicationYears;
    private Button resultButton;
    private TextView selectedInfo;

    private final String[] writerNames = {
            "Оберіть письменника",
            "Михайло Коцюбинський",
            "Ольга Кобилянська",
            "Панас Мирний",
            "Григорій Сковорода",
            "Ліна Костенко"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writerList = findViewById(R.id.writerList);
        publicationYears = findViewById(R.id.publicationYears);
        resultButton = findViewById(R.id.resultButton);
        selectedInfo = findViewById(R.id.selectedInfo);

        fillWriterSpinner();

        resultButton.setOnClickListener(view -> showUserChoice());
    }

    private void fillWriterSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                writerNames
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        writerList.setAdapter(adapter);
    }

    private void showUserChoice() {
        int writerPosition = writerList.getSelectedItemPosition();
        int checkedYearId = publicationYears.getCheckedRadioButtonId();

        if (writerPosition == 0 && checkedYearId == -1) {
            selectedInfo.setText("Результат вибору\n\nДані ще не заповнено.");
            Toast.makeText(this, "Оберіть автора та рік видання", Toast.LENGTH_SHORT).show();
            return;
        }

        if (writerPosition == 0) {
            selectedInfo.setText("Результат вибору\n\nАвтор книги не обраний.");
            Toast.makeText(this, "Оберіть автора книги", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkedYearId == -1) {
            selectedInfo.setText("Результат вибору\n\nРік видання не обраний.");
            Toast.makeText(this, "Оберіть рік видання", Toast.LENGTH_SHORT).show();
            return;
        }

        String writer = writerNames[writerPosition];

        RadioButton checkedYearButton = findViewById(checkedYearId);
        String year = checkedYearButton.getText().toString();

        String resultText =
                "Результат вибору\n\n" +
                        "Автор: " + writer + "\n" +
                        "Рік видання: " + year + "\n\n" +
                        "Інформацію успішно сформовано.";

        selectedInfo.setText(resultText);
    }
}