package gist.pilldispenser.api.drugProductAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gist.pilldispenser.api.drugProductAPI.domain.DrugProductMapper;
import gist.pilldispenser.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.api.drugProductAPI.domain.entity.DrugProductApiResponse;
import gist.pilldispenser.api.drugProductAPI.domain.entity.DrugProductRequest;
import gist.pilldispenser.api.drugProductAPI.repository.DrugProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugProductService {

    private final DrugProductRepository drugProductRepository;
    private static final String BASE_URL = "https://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService05/getDrugPrdtMcpnDtlInq04";
    private static final String SERVICE_KEY = "MkvGYtjqXwhLG79ljGRPqBYsjnMT9M6atTQI0YofGQJQaPIj4miDdSkxAPNQWlVLuN%2FbFkbX3CeAjNGP3JNlrA%3D%3D";
    private static final int PAGE_SIZE = 100;
    private static final int TOTAL_COUNT = 143763;

    public void save(DrugProductRequest drugRequest) {
        DrugProduct drugProduct = DrugProductMapper.toDrugProduct(drugRequest);
        drugProductRepository.save(drugProduct);
    }

    public String fetchAndSaveDrugProducts() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        int totalPages = (int) Math.ceil((double) TOTAL_COUNT / PAGE_SIZE);

        try {
            for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
                String apiUrl = buildApiUrl(pageNo);
                URI uri = new URI(apiUrl);
                String responseString = restTemplate.getForObject(uri, String.class);

                if (responseString != null && !responseString.isEmpty()) {
                    DrugProductApiResponse response = objectMapper.readValue(responseString, DrugProductApiResponse.class);
                    if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
                        List<DrugProductApiResponse.DrugItem> items = response.getBody().getItems();
                        items.stream()
                                .map(drugItem -> DrugProductRequest.builder()
                                        .entrps(drugItem.getEntrps())
                                        .prduct(drugItem.getPrduct())
                                        .mtralSn(drugItem.getMtralSn())
                                        .mtralCode(drugItem.getMtralCode())
                                        .mtralNm(drugItem.getMtralNm())
                                        .qnt(drugItem.getQnt())
                                        .ingdUnitCd(drugItem.getIngdUnitCd())
                                        .itemSeq(drugItem.getItemSeq())
                                        .build())
                                .forEach(this::save);
                    }
                } else {
                    return "페이지 " + pageNo + "에서 데이터를 가져오지 못했습니다.";
                }
            }
        } catch (Exception e) {
            throw new Exception("데이터를 가져오는 동안 오류가 발생했습니다: " + e.getMessage(), e);
        }

        return "의약품 제품 정보가 저장되었습니다.";
    }

    private String buildApiUrl(int pageNo) {
        try {
            return BASE_URL + "?serviceKey=" + URLEncoder.encode(SERVICE_KEY, "UTF-8") +
                    "&pageNo=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8") +
                    "&numOfRows=" + URLEncoder.encode(String.valueOf(PAGE_SIZE), "UTF-8") +
                    "&type=" + URLEncoder.encode("json", "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("URL 인코딩 중 오류가 발생했습니다.", e);
        }
    }
}
