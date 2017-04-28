package chickenzero.ht.com.lienquan.service;

import java.util.List;

import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by QuyDV on 4/19/17.
 */

public interface YoutubeAPI {
    String BASE_URL = "https://www.googleapis.com/";

    @GET("youtube/v3/playlistItems?part=snippet")
    Call<PlayListYoutube> loadQuestions(@Query("playlistId") String playlistId,
                                        @Query("key") String apiKey,
                                        @Query("maxResults")int maxResults);
}
