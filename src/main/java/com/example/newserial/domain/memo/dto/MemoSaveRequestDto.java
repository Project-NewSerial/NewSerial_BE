package com.example.newserial.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoSaveRequestDto {
    private String body;
    private Long memberId;

    @Builder
    public MemoSaveRequestDto(String body) {
        this.body = body;
    }

}