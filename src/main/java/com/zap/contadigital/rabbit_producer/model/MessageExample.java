package com.zap.contadigital.rabbit_producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageExample {

    private String messageId;
    private String log;
    private Date messageDate;
    private String httpResponse;
}
