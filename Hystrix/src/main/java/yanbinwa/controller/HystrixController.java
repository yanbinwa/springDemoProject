package yanbinwa.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.okhttp.Response;

import yanbinwa.util.HystrixUtil;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/hystrix")
@ConfigurationProperties(prefix = "hotel")
public class HystrixController {

    @Value("${hotel.service_hotel_url:}")
    private String HOTEL_URL;

    @Value("${hotel.service_hotel_name:}")
    private String hotelServiceName;
    
    @Value("${hotel.service_hotel_method_getHotelInfo:}")
    private String hotelServiceMethodGetHotelInfo;
    
    @Autowired
    private HystrixUtil hystrixUtil;

    @RequestMapping(value = "/firstHystrix", method = RequestMethod.GET)
    public String getHotelInfo(@RequestParam("id") int id, @RequestParam("name") String name) {
        
        String url = String.format(HOTEL_URL, id, name);
        Response response = null;
        try {
            response = hystrixUtil.execute(hotelServiceName, hotelServiceMethodGetHotelInfo, url);
            if (response != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (response != null && response.body() != null) {
                try {
                    response.body().close();// 资源关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "获取酒店信息失败";
    }

}
