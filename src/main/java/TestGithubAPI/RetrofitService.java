package TestGithubAPI;

import io.qameta.allure.Step;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.apache.http.util.TextUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitService {

    private static final String API_BASE_URL = "https://api.github.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static RetrofitService retrofitInstance;
    private Retrofit retrofit;

    private RetrofitService(){
        retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
    }

    private static RetrofitService getInstance(){
        if (retrofitInstance == null){
            retrofitInstance = new RetrofitService();
        }
        return retrofitInstance;
    }

    static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    static <S> S createService(
            Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    @Step("Create retrofit service")
    private static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }
        return getInstance()
                .retrofit
                .create(serviceClass);
    }
}