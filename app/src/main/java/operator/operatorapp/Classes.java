package operator.operatorapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohammed on 11/10/2015.
 */
public class Classes {
}

class Item
{

    private Cords cords;
    private String Item_Name;
    public double weight;
    public Item(Cords cord,String name,double weight)
    {
        setCords(cord);
        setItem_Name(name);
        setWeight(weight);
    }

    public Cords getCords() {
        return cords;
    }

    public void setCords(Cords cords) {
        this.cords = cords;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getRepresentation()
    {
        return Item_Name +","+getWeight()+","+ cords.getXcord()+","+cords.getYcord()+","+cords.getZcord();
    }
    public JSONObject getJSon()
    {
        JSONObject temp = new JSONObject();
        try {
            temp.put("name", Item_Name);
            temp.put("weight",weight);
            temp.put("x_cord",cords.getXcord());
            temp.put("y_cord",cords.getYcord());
            temp.put("z_cord",cords.getZcord());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return temp;
    }

}


class Cords {
    private double x_cord;
    private double y_cord;
    private double z_cord;
    public Cords(double x,double y,double z)
    {
        setXcord(x);
        setYcord(y);
        setzcord(z);
    }
    public double getXcord() {
        return x_cord;
    }
    public void setXcord(double x)
    {
        x_cord=x;
    }

    public double getYcord()
    {
        return y_cord;
    }
    public void setYcord(double y)
    {
        y_cord=y;
    }
    public double getZcord()
    {
        return z_cord;
    }
    public void setzcord(double z)
    {
        z_cord=z;
    }
}