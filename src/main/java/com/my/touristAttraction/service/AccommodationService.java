package com.my.touristAttraction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.touristAttraction.entity.Accommodation;
import com.my.touristAttraction.repository.AccommodationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 전국 지역 코드 배열
    private static final String[] REGION_CODES = {
            "11", "26", "27", "28", "29", "30", "31", "36", // 광역시/세종
            "41", "42", "43", "44", "45", "46", "47", "48", "50" // 도 단위
    };

    @Transactional
    public void fetchAndSaveAccommodations() {
        for (String regionCode : REGION_CODES) {
            fetchAndSaveByRegion(regionCode);
        }
    }

    private void fetchAndSaveByRegion(String regionCode) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551011/KorService2/searchStay2")
                    .queryParam("numOfRows", "1000")
                    .queryParam("MobileOS", "WEB")
                    .queryParam("MobileApp", "TouristAttraction")
                    .queryParam("_type", "json")
                    .queryParam("serviceKey", "a611fd5e397e6ff534ff96406fbca3f002b12ec79c63a8b93a4dee70a06d68f5")
                    .queryParam("lDongRegnCd", regionCode)
                    .toUriString();

            // ✅ 문자열로 받아서 ObjectMapper로 Map 변환
            String responseStr = restTemplate.getForObject(url, String.class);
            Map<String, Object> response = objectMapper.readValue(responseStr, Map.class);

            if (response == null) return;

            Map<String, Object> body = (Map<String, Object>) ((Map<String, Object>) response.get("response")).get("body");
            Map<String, Object> items = (Map<String, Object>) body.get("items");
            if (items == null) return;

            List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
            if (itemList == null) return;

            for (Map<String, Object> item : itemList) {
                String contentid = (String) item.get("contentid");

                if (repository.existsByContentid(contentid)) {
                    continue;
                }

                Accommodation accommodation = Accommodation.builder()
                        .title((String) item.get("title"))
                        .addr1((String) item.get("addr1"))
                        .addr2((String) item.get("addr2"))
                        .tel((String) item.get("tel"))
                        .zipcode((String) item.get("zipcode"))
                        .mapx((String) item.get("mapx"))
                        .mapy((String) item.get("mapy"))
                        .cat1((String) item.get("cat1"))
                        .cat2((String) item.get("cat2"))
                        .cat3((String) item.get("cat3"))
                        .firstimage((String) item.get("firstimage"))
                        .firstimage2((String) item.get("firstimage2"))
                        .contentid(contentid)
                        .contenttypeid((String) item.get("contenttypeid"))
                        .createdtime((String) item.get("createdtime"))
                        .modifiedtime((String) item.get("modifiedtime"))
                        .build();

                repository.save(accommodation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
