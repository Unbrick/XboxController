package t_r_y.c_a_t_c_h.me.XboxUnityApi;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 10.12.2016.
 */

public class XboxUnityProvider {
    private static XboxUnityServices xboxUnityServices = null;
    private static Retrofit retrofit = null;
    private static String baseURL = "http://xboxunity.net/Resources/Lib/";

    public static XboxUnityServices getService(){
        if (retrofit== null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if (xboxUnityServices== null){
            xboxUnityServices = retrofit.create(XboxUnityServices.class);
        }
        return xboxUnityServices;
    }
}
