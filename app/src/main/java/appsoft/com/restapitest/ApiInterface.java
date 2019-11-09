package appsoft.com.restapitest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user")     // Add resource name here
    Call<String> postData(@Field("name") String name, @Field("pass") String pass, @Field("type") String type);

    @GET("user")    // Add resource name here
    Call<List<Model>> getData();
}
