package operator.operatorapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

/**
 * Created by mohammed on 11/10/2015.
 */
public class add extends Activity {
    private EditText name_txt;
  //  private static DataBase sInstance;
    private EditText weight_txt;
    private EditText x_cord_txt;
    private EditText y_cord_txt;
    private EditText z_cord_txt;
    private Button register_btn;
    private Pubnub pubnub ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);
        //publish then subscribe
        pubnub = new Pubnub(getString(R.string.publish_key),getString(R.string.subscribe_key));
        try {
            pubnub.subscribe("quadcoptercontrol", new Callback() {
                @Override
                public void connectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                            + " : " + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void disconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                            + " : " + message.getClass() + " : "
                            + message.toString());
                }

                public void reconnectCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                            + " : " + message.getClass() + " : "
                            + message.toString());
                }

                @Override
                public void successCallback(String channel, Object message) {
                    System.out.println("SUBSCRIBE : " + channel + " : "
                            + message.getClass() + " : " + message.toString());
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    System.out.println("SUBSCRIBE : ERROR on channel " + channel
                            + " : " + error.toString());
                }
                    });
        } catch (PubnubException e) {
            e.printStackTrace();
        }

        //  sInstance=DataBase.getInstance(getApplicationContext());
                init_views();
        register_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check_input();
                    }
                });



    }
    private void check_input()
    {
        String messege="";
        String name=name_txt.getText().toString();
        Boolean flags[]={true,true,true,true,true};
        String weight=weight_txt.getText().toString();
        String x_cord=x_cord_txt.getText().toString();
        String y_cord=y_cord_txt.getText().toString();
        String z_cord=z_cord_txt.getText().toString();
        if((is_empty(name)==false))
        {
            messege+="The name should not be empty \n";
            //flags[0]=false;
            name_txt.setText("");
        }
        if(isNumeric(weight)==false||is_empty(weight)==false)
        {
            messege+="The weight field should be only integers and not empty \n";
            //flags[1]=false;
            weight_txt.setText("");
        }
        if(isNumeric(x_cord)==false||is_empty(x_cord)==false)
        {
            messege+="The x cord field should be only integers and not empty \n";
            //flags[2]=false;
            x_cord_txt.setText("");
        }
        if(isNumeric(y_cord)==false||is_empty(y_cord)==false)
        {
            messege+="The y cord field should be only integers and not empty \n";
            //flags[3]=false;
            y_cord_txt.setText("");
        }
        if(isNumeric(z_cord)==false||is_empty(z_cord)==false)
        {
            messege+="The z cord field should be only integers and not empty \n";
            //flags[4]=false;
            z_cord_txt.setText("");
        }
        if(is_empty(messege)==true)
        {

            Toast.makeText(getApplicationContext(), messege,Toast.LENGTH_SHORT);
        }
        else{
            boolean s=DataBase_Actions.insertItem(name,Integer.parseInt(x_cord),Integer.parseInt(y_cord),Integer.parseInt(z_cord),Integer.parseInt(weight));
            if(s==false)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "something is wrong", Toast.LENGTH_LONG);
                    }
                });
            }
            else
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG);
                    }
                });

            }
        }



    }
    private boolean is_empty(String s)
    {
        return s.length() != 0;
    }
    private boolean isNumeric(String s)
    {
        try
        {
            Integer d = Integer.parseInt(s);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    private void init_views()
    {
        name_txt=(EditText)findViewById(R.id.name_input);
        weight_txt=(EditText)findViewById(R.id.weight_input);
        x_cord_txt=(EditText)findViewById(R.id.xcord_input);
        y_cord_txt=(EditText)findViewById(R.id.ycord_input);
        z_cord_txt=(EditText)findViewById(R.id.zcord_input);

        register_btn=(Button)findViewById(R.id.register_btn);

    }
    @Override
    protected  void onResume()
    {
        super.onResume();
    }
    @Override
    protected  void onPause()
    {
        super.onPause();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
    }
}
