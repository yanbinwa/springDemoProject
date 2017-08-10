package yanbinwa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yanbinwa.model.Address;

@RestController
@RequestMapping("/address")
public class AddressController {
    
    @RequestMapping("/getAddress")
    public Address getAddress(){
        Address address = Address.builder().province("内蒙古自治区")
                                           .city("呼和浩特市")
                                           .country("回民区")
                                           .build();
        System.out.println(address.getCity());
        return address;
    }
    
}
