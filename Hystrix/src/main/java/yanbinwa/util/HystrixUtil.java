package yanbinwa.util;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.squareup.okhttp.Response;

import yanbinwa.command.MyHystrixCommand;
import yanbinwa.properties.HystrixProperites;

@Component
public class HystrixUtil {

    @Autowired
    private HystrixProperites hp;
    
    public Response execute(String hotelServiceName, 
                            String hotelServiceMethodGetHotelInfo, 
                            String url) throws InterruptedException, ExecutionException
    {
        Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(hotelServiceName));        //被调用服务
        setter.andCommandKey(HystrixCommandKey.Factory.asKey(hotelServiceMethodGetHotelInfo));              //被调用服务的一个被调用方法
        setter.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(hp.getTimeoutInMillions()));
        return new MyHystrixCommand(setter, url).execute();
    }

}