package firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class ConfigFireBase {
    private static DatabaseReference fbDataReference;
    private static FirebaseAuth fbAuth;


    public static DatabaseReference getDataReference(){
        if(fbDataReference == null){
            fbDataReference = FirebaseDatabase.getInstance().getReference();
        }
        return fbDataReference;
    }

    public static FirebaseAuth getAuth(){
        if(fbAuth == null){
            fbAuth = FirebaseAuth.getInstance();
        }
        return fbAuth;
    }

}
