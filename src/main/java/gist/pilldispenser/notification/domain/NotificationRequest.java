package gist.pilldispenser.notification.domain;

/**
 * 임의로 가정한 아두이노에서 오는 약 복용 확인 요청
 *
 * @param hardwareNo 하드웨어 시리얼 넘버
 * @param isPillTaken 약 복용 여부
 */
public record NotificationRequest(
        String hardwareNo,
        boolean isPillTaken) {
}
