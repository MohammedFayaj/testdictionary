package test.dictionary.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.dictionary.model.Dictionary;

/**
 * Created by imran on 23/01/18.
 */


public interface DictionaryApi {
    @GET("define")
    Single<Dictionary> getAllItems(@Query("term") String term);
}