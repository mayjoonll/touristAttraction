package com.my.touristAttraction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @Column(name = "content_id")
    private Long contentId; // contentid

    private String title;   // 음식점 이름
    private String addr1;   // 주소
    private String addr2;   // 상세 주소
    private String zipcode; // 우편번호
    private String tel;     // 전화번호

    private String areacode;    // 시/도 코드
    private String sigungucode; // 시/군/구 코드

    private Double mapx;    // 경도
    private Double mapy;    // 위도
    private String mlevel;  // 지도 레벨

    private String firstimage;   // 대표 이미지
    private String firstimage2;  // 추가 이미지

    private String contenttypeid; // 콘텐츠 타입 ID
    private String cat1;          // 대분류 코드
    private String cat2;          // 중분류 코드
    private String cat3;          // 소분류 코드

    private String createdtime;   // 생성일자
    private String modifiedtime;  // 수정일자
    private String cpyrhtDivCd;   // 저작권 구분

    private String lDongRegnCd;
    private String lDongSignguCd;
    private String lclsSystm1;
    private String lclsSystm2;
    private String lclsSystm3;
}
