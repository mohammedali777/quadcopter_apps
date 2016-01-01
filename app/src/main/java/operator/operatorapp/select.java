package operator.operatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mohammed on 11/13/2015.
 */
public class select extends AppCompatActivity {
    private Button back;
    private Button send_data;
    private ArrayAdapter<String> adapterp;
    private ArrayList<Item> item_list;
    private communication_server serv;
    private ListView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communication_server.initinstance(getApplicationContext());
        serv = communication_server.getInstance();
        setContentView(R.layout.select_page);
        back=(Button)findViewById(R.id.back_btn);
        send_data=(Button)findViewById(R.id.send_btn);
        view=(ListView)findViewById(R.id.listviewx);
        item_list = DataBase_Actions.getAllitemsObject();
        String [] names = new String[item_list.size()];
        for(int i =0 ;i <item_list.size();i++)
        {
            names[i] = item_list.get(i).getItem_Name();
        }
        adapterp=new ArrayAdapter<String>(this,R.layout.simple_list_item,names);
        view.setAdapter(adapterp);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view;
                Toast.makeText(getApplicationContext(), "selected Item Name is " + x.getText(), Toast.LENGTH_LONG).show();
                Log.d("fuck",x.getText().toString());
                searchAndSend(position);
            }
        });

    }
    private void searchAndSend(int id)
    {
        Item item = item_list.get(id);
        //String msg =item.getRepresentation();
        serv.send_messege(item.getJSon());

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
