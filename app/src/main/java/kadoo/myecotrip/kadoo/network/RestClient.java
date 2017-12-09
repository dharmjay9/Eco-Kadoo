package kadoo.myecotrip.kadoo.network;


import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Use to handle Network Call
 */


public class RestClient implements INetwork {

    ApiCall iNetwork;

    //http://myecotrip.com/myecotrip_api/public/index.php/api/v1/signUp
    public RestClient() {
        // String url = "http://myecotrip.com/myecotrip_api/public/index.php/api/v1/";
        // String url = "http://myecotrip.com/myecotrip_api_new/public/index.php/api/v1/";
        String url = "http://35.154.28.131/myecotripapis/public/index.php/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iNetwork = retrofit.create(ApiCall.class);
    }


    /**
     * Add common parameter in Get API
     *
     * @return
     */
    private OkHttpClient.Builder getOkHttpClient() {

        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                okhttp3.Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("userPassword", "Capgeminiapi")
                        .addQueryParameter("accountName", "lakmepilot")
                        .addQueryParameter("appVersion", "v100")
                        .addQueryParameter("userName", "api_capgemini")
                        .build();
                okhttp3.Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                okhttp3.Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient;

    }





}
