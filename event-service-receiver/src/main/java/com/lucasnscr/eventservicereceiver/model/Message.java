package com.lucasnscr.eventservicereceiver.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String msg;

    public Message(String msg){
        this.msg = msg;
    }

}
