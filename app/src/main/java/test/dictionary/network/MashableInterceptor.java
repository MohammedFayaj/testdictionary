package test.dictionary.network;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import test.dictionary.Utils.Constants;

public class MashableInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("X-Mashape-Key", Constants.MASHABLE_KEY)
                .header("Accept", "text/plain")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
