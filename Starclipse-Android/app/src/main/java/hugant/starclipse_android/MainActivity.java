package hugant.starclipse_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int i = 0;
    Button btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textView2);
        text.setText(i + "");

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i++;
                text.setText(i + "");
            }
        });
    }
}
