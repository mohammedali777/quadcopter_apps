package operator.operatorapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;

import org.json.JSONObject;

/**
 * Created by mohammed on 12/24/2015.
 */
public class communication_server {
    private static Context mContext;
    private static communication_server instance;
    private  Handler mHandler;
    private  Callback publishcall ;
    private  Callback subscribecall ;
    private  Pubnub pubnub = null;
    public  final String CHANNEl = "quadcoptercontrol";

    private communication_server(){
        publishcall = new publishCallback();
        subscribecall = new subscribeCallback();
        mHandler = new Handler(Looper.getMainLooper());
        pubnub = new Pubnub(mContext.getString(R.string.publish_key),mContext.getString(R.string.subscribe_key));

    }



    public static void initinstance(Context context)
    {
        if(instance == null) {

            mContext = context;
            instance = new communication_server();

        }

    }
    public static communication_server getInstance()
    {
        // Return the instance
        return instance;
    }


    public synchronized void send_messege(JSONObject msg)
    {
        if(msg!=null) {
            pubnub.publish(CHANNEl, msg,publishcall);
        }
    }

    public void display_string(String msg)
    {
        final String msg_temp = msg;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //// TODO: 1/1/2016  post somewhere
            }
        });
    }




    class subscribeCallback extends Callback
    {
        @Override
        public void connectCallback(String channel, Object message) {
            String msg= "SUBSCRIBE : CONNECT on channel:" + channel
                    + " : " + message.getClass() + " : "
                    + message.toString();
            display_string(msg);
        }

        @Override
        public void disconnectCallback(String channel, Object message) {
            String msg= "SUBSCRIBE : DISCONNECT on channel:" + channel
                    + " : " + message.getClass() + " : "
                    + message.toString() ;
            display_string(msg);
        }

        public void reconnectCallback(String channel, Object message) {
            String msg= "SUBSCRIBE : RECONNECT on channel:" + channel
                    + " : " + message.getClass() + " : "
                    + message.toString();
            display_string(msg);
        }

        @Override
        public void successCallback(String channel, Object message) {
            String msg= "SUBSCRIBE : " + channel + " : "
                    + message.getClass() + " : " + message.toString();
            display_string(msg);
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            String msg = "SUBSCRIBE : ERROR on channel " + channel
                    + " : " + error.toString();
            display_string(msg);

        }
    }
    class publishCallback extends Callback
    {
        @Override
        public void successCallback(String channel, Object response) {
            display_string(response.toString());
        }
        @Override
        public void errorCallback(String channel, PubnubError error) {
            //System.out.println(error.toString());
            display_string(error.toString());
        }
    }


}
