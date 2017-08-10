package yanbinwa;


import java.util.List;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface HotelAPI {
    
    /**
     * GET请求带查询参数
     */
    @GET("/hotel/getHotelWithQueryParameter")
    public Hotel getHotelWithQueryParameter(@Query("hotelname") String hotelname);
    
    /**
     * POST请求
     */
    @POST("/hotel/getHotelList")
    public List<Hotel> getHotelList();
    
    /**
     * POST请求,带参数JavaBean
     */
    @POST("/hotel/getHotelListWithBody")
    public List<Hotel> getHotelListWithBody(@Body Hotel hotel);
    
}

