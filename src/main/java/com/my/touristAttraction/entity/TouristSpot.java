package com.my.touristAttraction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tourist_spot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addr1;
    private String addr2;
    private String areacode;
    private String cat1;
    private String cat2;
    private String cat3;
    private String contentid;
    private String contenttypeid;
    private String createdtime;
    private String firstimage;
    private String firstimage2;
    private String cpyrhtDivCd;
    private Double mapx;
    private Double mapy;
    private String mlevel;
    private String modifiedtime;
    private String sigungucode;
    private String tel;
    private String title;
    private String zipcode;
    private String lDongRegnCd;
    private String lDongSignguCd;
    private String lclsSystm1;
    private String lclsSystm2;
    private String lclsSystm3;
}
