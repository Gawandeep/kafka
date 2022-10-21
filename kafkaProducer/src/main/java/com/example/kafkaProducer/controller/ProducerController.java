package com.example.kafkaProducer.controller;

import com.example.kafkaProducer.config.KafkaConfiguration;
import com.example.kafkaProducer.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produce")
public class ProducerController {

    @Autowired
   private KafkaTemplate<String,String> template;

    @Autowired
    private KafkaTemplate<String,Order> template1;
    @PostMapping("/{name}")
    public String produce(@PathVariable String name){
        template.send(KafkaConfiguration.topic,"Hello "+name);
        return "Data Published";
    }

    @PostMapping("/publish")
    public Order publish(@RequestBody Order order){
        order.setId(UUID.randomUUID().toString());
        template1.send("userTopic",order);
        return order;
    }
}
