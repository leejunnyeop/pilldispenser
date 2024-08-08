package gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ApiResponse {
    private Header header;
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Header {
        private String resultCode;
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    public static class Body {
        private int numOfRows;
        private int pageNo;
        private int totalCount;
        private List<DrugItem> items;

        public int getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(int numOfRows) {
            this.numOfRows = numOfRows;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<DrugItem> getItems() {
            return items;
        }

        public void setItems(List<DrugItem> items) {
            this.items = items;
        }
    }

    public static class DrugItem {
        @JsonProperty("entpName")
        private String entpName;

        @JsonProperty("itemName")
        private String itemName;

        @JsonProperty("itemSeq")
        private String itemSeq;

        @JsonProperty("efcyQesitm")
        private String efcyQesitm;

        @JsonProperty("useMethodQesitm")
        private String useMethodQesitm;

        @JsonProperty("atpnWarnQesitm")
        private String atpnWarnQesitm;

        @JsonProperty("atpnQesitm")
        private String atpnQesitm;

        @JsonProperty("intrcQesitm")
        private String intrcQesitm;

        @JsonProperty("seQesitm")
        private String seQesitm;

        @JsonProperty("depositMethodQesitm")
        private String depositMethodQesitm;

        @JsonProperty("openDe")
        private String openDe;

        @JsonProperty("updateDe")
        private String updateDe;

        @JsonProperty("itemImage")
        private String itemImage;

        @JsonProperty("bizrno")
        private String bizrno;

        // Getters and Setters
        public String getEntpName() {
            return entpName;
        }

        public void setEntpName(String entpName) {
            this.entpName = entpName;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemSeq() {
            return itemSeq;
        }

        public void setItemSeq(String itemSeq) {
            this.itemSeq = itemSeq;
        }

        public String getEfcyQesitm() {
            return efcyQesitm;
        }

        public void setEfcyQesitm(String efcyQesitm) {
            this.efcyQesitm = efcyQesitm;
        }

        public String getUseMethodQesitm() {
            return useMethodQesitm;
        }

        public void setUseMethodQesitm(String useMethodQesitm) {
            this.useMethodQesitm = useMethodQesitm;
        }

        public String getAtpnWarnQesitm() {
            return atpnWarnQesitm;
        }

        public void setAtpnWarnQesitm(String atpnWarnQesitm) {
            this.atpnWarnQesitm = atpnWarnQesitm;
        }

        public String getAtpnQesitm() {
            return atpnQesitm;
        }

        public void setAtpnQesitm(String atpnQesitm) {
            this.atpnQesitm = atpnQesitm;
        }

        public String getIntrcQesitm() {
            return intrcQesitm;
        }

        public void setIntrcQesitm(String intrcQesitm) {
            this.intrcQesitm = intrcQesitm;
        }

        public String getSeQesitm() {
            return seQesitm;
        }

        public void setSeQesitm(String seQesitm) {
            this.seQesitm = seQesitm;
        }

        public String getDepositMethodQesitm() {
            return depositMethodQesitm;
        }

        public void setDepositMethodQesitm(String depositMethodQesitm) {
            this.depositMethodQesitm = depositMethodQesitm;
        }

        public String getOpenDe() {
            return openDe;
        }

        public void setOpenDe(String openDe) {
            this.openDe = openDe;
        }

        public String getUpdateDe() {
            return updateDe;
        }

        public void setUpdateDe(String updateDe) {
            this.updateDe = updateDe;
        }

        public String getItemImage() {
            return itemImage;
        }

        public void setItemImage(String itemImage) {
            this.itemImage = itemImage;
        }

        public String getBizrno() {
            return bizrno;
        }

        public void setBizrno(String bizrno) {
            this.bizrno = bizrno;
        }
    }
}
