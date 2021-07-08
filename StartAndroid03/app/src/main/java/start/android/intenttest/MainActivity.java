package start.android.intenttest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        text1 = findViewById(R.id.text1);

        button.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        });

    }
}