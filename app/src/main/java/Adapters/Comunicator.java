package Adapters;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danqu on 12/11/2017.
 */

public final class Comunicator {
    private static HashMap<String, Object> stream;

    private Comunicator(){}

    public static HashMap<String, Object> getInstance(){
        if(stream == null){
            stream = new HashMap<String, Object>();
        }
        return stream;
    }

    public static void addObject(String index, Object obj){
        stream.put(index, obj);
    }

    public static void clear(){
        stream.clear();
    }

    public static void printAll(){
        for(int i=0; i<stream.size(); ++i){
            Log.e("QQ", ""+stream.get(i));
        }
    }
}
