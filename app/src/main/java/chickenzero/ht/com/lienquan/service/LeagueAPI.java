package chickenzero.ht.com.lienquan.service;

import java.util.List;

import chickenzero.ht.com.lienquan.models.LeagueItem;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import chickenzero.ht.com.lienquan.models.Wallpaper;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by QuyDV on 5/8/17.
 */

public interface LeagueAPI {
    @GET("master/LeagueAPI.json")
    Call<List<LeagueItem>> loadLeagueList();

    @GET("master/pro_player.json")
    Call<List<ProPlayer>> loadProPlayerList();

    @GET("master/AoVWallpapers.json")
    Call<List<Wallpaper>> loadWallpaperList();
}
