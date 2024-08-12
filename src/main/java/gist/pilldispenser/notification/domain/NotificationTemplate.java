package gist.pilldispenser.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class NotificationTemplate {
    private String objectType;
    private String text;
    private LinkList link;
    private String buttonTitle;

    @Builder
    public static class LinkList {
        private String webUrl;
        private String mobileWebUrl;
    }
}
