package operator.operatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase_Actions.init(getApplicationContext());
        btn_add=(Button)findViewById(R.id.add);
        btn_start=(Button)findViewById(R.id.start);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),add.class);
                startActivity(intent);
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo add some functionality to the start button
                Intent intent;
                intent = new Intent(getApplicationContext(),select.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
