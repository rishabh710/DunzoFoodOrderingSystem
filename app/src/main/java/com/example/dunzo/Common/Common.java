package com.example.dunzo.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;

import com.example.dunzo.Model.Request;
import com.example.dunzo.Model.User;
import com.example.dunzo.Remote.APIService;
import com.example.dunzo.Remote.RetrofitClient;

import java.util.Calendar;
import java.util.Locale;

public class Common {

    public static String topicName="News";

    public static User currentUser;

    public static Request currentRequest;

    public static final String INTENT_FOOD_ID="FoodId";

    private static final String BASE_URL="https://fcm.googleapis.com/";

    public static String PHONE_TEXT="userPhone";


    public static APIService getFCMService()
    {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static final String DELETE="Delete";

    public static final String USER_KEY="User";
    public static final String PWD_KEY="Password";

    public static String convertCodetoStatus(String status) {
        if (status.equals("0")){
            return "Placed";
        }else if (status.equals("1")){
            return "On the way";
        }else if (status.equals("2")){
            return "Shipped";
        }else {
            return "Delivered";
        }
    }

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null){
            NetworkInfo[] info= connectivityManager.getAllNetworkInfo();
            if (info!=null){
                for (int i=0; i<info.length;i++){
                    if (info[i].getState()==NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static String getDate(long time)
    {
        Calendar calendar= Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        StringBuilder date= new StringBuilder(
                DateFormat.format("dd-MM-yyyy HH:mm",calendar).toString());
        return date.toString();
    }
}
