package yanbinwa.command;

import com.netflix.hystrix.HystrixCommand;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MyHystrixCommand extends HystrixCommand<Response>
{

    String url;
    
    public MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter setter, String url)
    {
        super(setter);
        this.url = url;
    }

    @Override
    protected Response run() throws Exception
    {
        System.out.println("服务正被调用，当前线程：'{}'" + Thread.currentThread().getName());
        Request request = new Request.Builder().url(url).build();
        return new OkHttpClient().newCall(request).execute();
    }
    
    @Override
    public Response getFallback() {
        System.out.println("服务调用失败,service:'{}'");
        return null;
    }

}
