package cwowhappy.study.thread.chapter01;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cwowhappy.study.common.json.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleMessage implements Serializable {
    private LocalDateTime timestamp;
    private String content;

    public static SimpleMessage build(String content) {
        return build(LocalDateTime.now(), content);
    }

    public static SimpleMessage build(LocalDateTime timestamp, String content) {
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setTimestamp(timestamp);
        simpleMessage.setContent(content);
        return simpleMessage;
    }


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
