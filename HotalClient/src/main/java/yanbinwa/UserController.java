package yanbinwa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("UserController相关api")
public class UserController
{
    @Autowired
    private UserService userService;
    
    @ApiOperation("获取酒店信息,测试GETWithQueryParameter")
    @RequestMapping(value="/getHotelWithQueryParameter",method=RequestMethod.GET)
    public Hotel getHotel(@RequestParam("hotelname") String hotelname) {
        return userService.getHotelFromMyboot2WithQueryParameter(hotelname);
    }
    
    @ApiOperation("获取酒店信息,测试POST")
    @RequestMapping(value="/getHotelList",method=RequestMethod.GET)
    public List<Hotel> getHotelList() {
        return userService.getHotelFromMyboot2List();
    }
    
    @ApiOperation("获取酒店信息,测试POST")
    @RequestMapping(value="/getHotelListWithBody",method=RequestMethod.GET)
    public List<Hotel> getHotelListWithBody() {
        return userService.getHotelFromMyboot2ListWithBody(new Hotel(888, "武林酒店"));
    }
}
