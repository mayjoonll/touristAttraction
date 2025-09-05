package com.my.touristAttraction.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationDto {
    private String title;
    private String addr1;
    private String addr2;
    private String tel;
    private String zipcode;
    private String mapx;
    private String mapy;
    private String cat1;
    private String cat2;
    private String cat3;
    private String firstimage;
    private String firstimage2;
    private String contentid;
    private String contenttypeid;
    private String createdtime;
    private String modifiedtime;
}