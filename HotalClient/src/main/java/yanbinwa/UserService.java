package yanbinwa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private HotelAPI hotelApi;
    
    public Hotel getHotelFromMyboot2WithQueryParameter(String hotelname){
        return hotelApi.getHotelWithQueryParameter(hotelname);
    }
    
    public List<Hotel> getHotelFromMyboot2List(){
        return hotelApi.getHotelList();//测试post请求
    }
    
    public List<Hotel> getHotelFromMyboot2ListWithBody(Hotel hotel){
        return hotelApi.getHotelListWithBody(hotel);//测试post请求
    }
}
