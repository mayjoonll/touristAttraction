package com.my.touristAttraction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.touristAttraction.entity.TouristSpot;
import com.my.touristAttraction.repository.TouristSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TouristSpotService {

    private final TouristSpotRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void fetchAndSaveTouristSpots() {
        try {
            int numOfRows = 1000; // 한 페이지 데이터 수
            int totalPages = 14; // 총 페이지 수 (13020 / 500 ≈ 27)

            List<TouristSpot> touristSpots = new ArrayList<>();

            for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
                String url = UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551011/KorService2/areaBasedList2")
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("MobileOS", "WEB")
                        .queryParam("MobileApp", "TouristAttraction")
                        .queryParam("_type", "json")
                        .queryParam("contentTypeId", "12")
                        .queryParam("serviceKey", "a611fd5e397e6ff534ff96406fbca3f002b12ec79c63a8b93a4dee70a06d68f5")
                        .toUriString();

                String responseStr = restTemplate.getForObject(url, String.class);
                if (responseStr == null) continue;

                Map<String, Object> response = objectMapper.readValue(responseStr, Map.class);
                Map<String, Object> body = (Map<String, Object>) ((Map<String, Object>) response.get("response")).get("body");
                Map<String, Object> items = (Map<String, Object>) body.get("items");
                if (items == null) continue;

                List<Map<String, Object>> itemList;
                Object itemObj = items.get("item");
                if (itemObj instanceof List) {
                    itemList = (List<Map<String, Object>>) itemObj;
                } else {
                    itemList = new ArrayList<>();
                    itemList.add((Map<String, Object>) itemObj);
                }

                for (Map<String, Object> item : itemList) {
                    String contentid = String.valueOf(item.get("contentid"));
                    if (repository.existsByContentid(contentid)) continue;

                    TouristSpot spot = TouristSpot.builder()
                            .title(String.valueOf(item.get("title")))
                            .addr1(String.valueOf(item.get("addr1")))
                            .addr2(String.valueOf(item.get("addr2")))
                            .zipcode(String.valueOf(item.get("zipcode")))
                            .tel(String.valueOf(item.get("tel")))
                            .areacode(String.valueOf(item.get("areacode")))
                            .sigungucode(String.valueOf(item.get("sigungucode")))
                            .cat1(String.valueOf(item.get("cat1")))
                            .cat2(String.valueOf(item.get("cat2")))
                            .cat3(String.valueOf(item.get("cat3")))
                            .contentid(contentid)
                            .contenttypeid(String.valueOf(item.get("contenttypeid")))
                            .firstimage(String.valueOf(item.get("firstimage")))
                            .firstimage2(String.valueOf(item.get("firstimage2")))
                            .mapx(item.get("mapx") != null ? Double.parseDouble(String.valueOf(item.get("mapx"))) : null)
                            .mapy(item.get("mapy") != null ? Double.parseDouble(String.valueOf(item.get("mapy"))) : null)
                            .createdtime(String.valueOf(item.get("createdtime")))
                            .modifiedtime(String.valueOf(item.get("modifiedtime")))
                            .cpyrhtDivCd(String.valueOf(item.get("cpyrhtDivCd")))
                            .mlevel(String.valueOf(item.get("mlevel")))
                            .lDongRegnCd(String.valueOf(item.get("lDongRegnCd")))
                            .lDongSignguCd(String.valueOf(item.get("lDongSignguCd")))
                            .lclsSystm1(String.valueOf(item.get("lclsSystm1")))
                            .lclsSystm2(String.valueOf(item.get("lclsSystm2")))
                            .lclsSystm3(String.valueOf(item.get("lclsSystm3")))
                            .build();

                    touristSpots.add(spot);
                }

                System.out.println(pageNo + " 페이지 완료 (누적: " + touristSpots.size() + "건)");
                repository.saveAll(touristSpots);
            }


            System.out.println("총 저장된 데이터 수: " + touristSpots.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
