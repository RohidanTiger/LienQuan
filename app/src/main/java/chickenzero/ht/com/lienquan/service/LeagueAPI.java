package chickenzero.ht.com.lienquan.service;

import java.util.List;

import chickenzero.ht.com.lienquan.models.LeagueItem;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by QuyDV on 5/8/17.
 */

public interface LeagueAPI {
    String BASE_URL = "https://raw.githubusercontent.com/RohidanTiger/";
    @GET("LienQuanLeague/master/LeagueAPI.json")
    Call<List<LeagueItem>> loadLeagueList();

    @GET("LienQuanLeague/master/pro_player.json")
    Call<List<ProPlayer>> loadProPlayerList();
}
