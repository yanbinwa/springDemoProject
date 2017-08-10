package yanbinwa.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Address {
    private int id;
    private String province;
    private String city;
    private String country;
}