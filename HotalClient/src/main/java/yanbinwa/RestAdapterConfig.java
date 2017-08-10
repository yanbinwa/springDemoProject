package yanbinwa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit.RestAdapter;

@Configuration
public class RestAdapterConfig
{
    @Bean
    public RestAdapter getRestAdapter(){
        /**
         * setEndpoint("http://localhost:8081"):指定基本的URL，
         * API接口中的URL是相对于该URL的路径的，
         * 不能少了协议名，例如写成：localhost:8081就不行
         */
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:8082")
                .build();
        return adapter;
    }
}
