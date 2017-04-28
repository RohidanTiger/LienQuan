package chickenzero.ht.com.lienquan.service;

import chickenzero.ht.com.lienquan.models.NewsList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by QuyDV on 4/24/17.
 */

public interface RssNewService {
    String BASE_URL = "https://lienquan.garena.vn/";
    @GET("feed")
    Call<NewsList> loadListNew();
}
