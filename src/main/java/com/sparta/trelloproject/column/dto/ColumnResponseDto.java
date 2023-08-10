package com.sparta.trelloproject.column.dto;

import com.sparta.trelloproject.column.entity.Column;
import com.sparta.trelloproject.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponseDto {
    private Long boardId;
    private String userName;
    private String columnName;
    private int position;
//    private List<CardResponseDto> cardList;

    public ColumnResponseDto(Column column) {
        this.columnName = column.getColumnName();
        this.boardId = column.getBoard().getId();
        this.userName = column.getUser().getUserName();
        this.position = column.getPosition();
//        this.cardList = column.getCardList().stream()
//                .map(ColumnResponseDto::new)
//                .sorted(Comparator.comparing(ColumnResponseDto::getCreateAt))
//                .toList();
    }
}
