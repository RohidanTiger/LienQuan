package chickenzero.ht.com.lienquan.service;

import chickenzero.ht.com.lienquan.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by QuyDV on 4/19/17.
 */

public class ServiceGenerator {
    private static Retrofit youtubeRetrofit = new Retrofit.Builder()
                    .baseUrl(YoutubeAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

    public static YoutubeAPI youtubeService = youtubeRetrofit.create(YoutubeAPI.class);

    private static Retrofit newsRetrofit = new Retrofit.Builder()
            .baseUrl(RssNewService.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create()).build();

    public static RssNewService newsService = newsRetrofit.create(RssNewService.class);

    private static Retrofit leagueRetrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();

    public static LeagueAPI leagueService = leagueRetrofit.create(LeagueAPI.class);

}
