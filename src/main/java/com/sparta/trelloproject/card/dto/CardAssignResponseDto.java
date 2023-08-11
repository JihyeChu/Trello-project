package com.sparta.trelloproject.card.dto;

import com.sparta.trelloproject.card.entity.CardAssignEntity;
import lombok.Getter;

@Getter
public class CardAssignResponseDto {

    private Long cardId;
    private String worker;

    public CardAssignResponseDto(CardAssignEntity assignEntity) {
        this.worker = assignEntity.getWorker();
        this.cardId = assignEntity.getCard().getId();
    }
}
