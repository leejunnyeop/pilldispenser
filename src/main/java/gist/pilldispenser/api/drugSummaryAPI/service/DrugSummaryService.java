package gist.pilldispenser.api.drugSummaryAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gist.pilldispenser.api.drugSummaryAPI.domain.dto.request.DrugSummaryRequest;
import gist.pilldispenser.api.drugSummaryAPI.domain.DrugMapper;
import gist.pilldispenser.api.drugSummaryAPI.domain.dto.response.ApiResponse;
import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.api.drugSummaryAPI.repository.DrugSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugSummaryService {

    private final DrugSummaryRepository drugRepository;
    private static final String BASE_URL = "https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?serviceKey=MkvGYtjqXwhLG79ljGRPqBYsjnMT9M6atTQI0YofGQJQaPIj4miDdSkxAPNQWlVLuN%2FbFkbX3CeAjNGP3JNlrA%3D%3D";
    private static final int NUM_OF_ROWS = 100;
    private static final int TOTAL_COUNT = 4791;

    public void save(DrugSummaryRequest drugRequest) {
        DrugSummary drugSummary = DrugMapper.toEntity(drugRequest);
        drugRepository.save(drugSummary);
    }

    public String fetchAndSaveDrugs() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        int totalPages = (int) Math.ceil((double) TOTAL_COUNT / NUM_OF_ROWS);

        try {
            for (int i = 0; i < totalPages; i++) {
                String apiUrl = buildApiUrl(i + 1);
                System.out.println("API URI: " + apiUrl);

                URI uri = new URI(apiUrl);
                String responseString = restTemplate.getForObject(uri, String.class);

                // 응답 데이터 로깅
                System.out.println("Response: " + responseString);

                if (responseString != null) {
                    ApiResponse response = objectMapper.readValue(responseString, ApiResponse.class);
                    if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
                        List<ApiResponse.DrugItem> items = response.getBody().getItems();

                        items.stream()
                                .map(drugItem -> DrugSummaryRequest.builder()
                                        .entpName(drugItem.getEntpName())
                                        .itemName(drugItem.getItemName())
                                        .itemSeq(drugItem.getItemSeq())
                                        .efcyQesitm(drugItem.getEfcyQesitm())
                                        .useMethodQesitm(drugItem.getUseMethodQesitm())
                                        .atpnQesitm(drugItem.getAtpnQesitm())
                                        .intrcQesitm(drugItem.getIntrcQesitm())
                                        .seQesitm(drugItem.getSeQesitm())
                                        .depositMethodQesitm(drugItem.getDepositMethodQesitm())
                                        .itemImage(drugItem.getItemImage())
                                        .build())
                                .forEach(this::save);
                    }
                } else {
                    return "페이지 " + (i + 1) + "에서 데이터를 가져오지 못했습니다.";
                }
            }
        } catch (Exception e) {
            throw new Exception("데이터를 가져오는 동안 오류가 발생했습니다: " + e.getMessage());
        }

        return "의약정보가 저장되었습니다.";
    }

    private String buildApiUrl(int pageNo) {
        try {
            return BASE_URL + "&pageNo=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8") +
                    "&numOfRows=" + URLEncoder.encode(String.valueOf(NUM_OF_ROWS), "UTF-8") +
                    "&type=" + URLEncoder.encode("json", "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("URL 인코딩 중 오류가 발생했습니다.", e);
        }
    }
}
