package com.jueshan.recommend.entitys;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class LogEntity {

    private Integer userId;
    private Integer productId;
    private Long time;
    private String action;

    public static LogEntity LogToEntity(String s){
        String[] values = s.split(",");
        if (values.length < 2){
            log.info("Message is not correct...");
        }

        LogEntity log = new LogEntity();
        log.setUserId(Integer.parseInt(values[0]));
        log.setProductId(Integer.parseInt(values[1]));
        log.setTime(Long.parseLong(values[2]));
        log.setAction(values[3]);

        return log;
    }
}