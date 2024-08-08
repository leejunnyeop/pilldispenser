package gist.pilldispenser.drug.api.drugIdentificationAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.DrugIdentificationMapper;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentificationApiResponse;
import gist.pilldispenser.drug.api.drugIdentificationAPI.repository.DrugIdentificationRepository;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugIdentificationService {

    private final DrugIdentificationRepository drugIdentificationRepository;
    private static final String BASE_URL = "https://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01?serviceKey=MkvGYtjqXwhLG79ljGRPqBYsjnMT9M6atTQI0YofGQJQaPIj4miDdSkxAPNQWlVLuN%2FbFkbX3CeAjNGP3JNlrA%3D%3D";
    private static final int NUM_OF_ROWS = 100;
    private static final int TOTAL_COUNT = 25445;

    public void save(DrugIdentificationRequest drugRequest) {
        DrugIdentification drugIdentification = DrugIdentificationMapper.toDrugIdentification(drugRequest);
        drugIdentificationRepository.save(drugIdentification);
    }

    public String fetchAndSaveDrugIdentifications() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        int totalPages = (int) Math.ceil((double) TOTAL_COUNT / NUM_OF_ROWS);

        try {
            for (int i = 0; i < totalPages; i++) {
                String apiUrl = buildApiUrl(i + 1);
                System.out.println("API URI: " + apiUrl);

                URI uri = new URI(apiUrl);
                String responseString = restTemplate.getForObject(uri, String.class);



                if (responseString != null) {
                    DrugIdentificationApiResponse response = objectMapper.readValue(responseString, DrugIdentificationApiResponse.class);
                    if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
                        List<DrugIdentificationApiResponse.DrugItem> items = response.getBody().getItems();

                        items.stream()
                                .map(drugItem -> DrugIdentificationRequest.builder()
                                        .itemSeq(drugItem.getItemSeq())
                                        .itemName(drugItem.getItemName())
                                        .entpName(drugItem.getEntpName())
                                        .chart(drugItem.getChart())
                                        .drugShape(drugItem.getDrugShape())
                                        .colorClass1(drugItem.getColorClass1())
                                        .colorClass2(drugItem.getColorClass2())
                                        .lengLong(drugItem.getLengLong())
                                        .lengShort(drugItem.getLengShort())
                                        .thick(drugItem.getThick())
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

        return "의약품 식별 정보가 저장되었습니다.";
    }

    private String buildApiUrl(int pageNo) {
        return BASE_URL + "&pageNo=" + pageNo + "&numOfRows=" + NUM_OF_ROWS + "&type=json";
    }
}
