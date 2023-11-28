package com.example.newserial.domain.quiz.repository;

import com.example.newserial.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="OX_QUIZ")
public class OxQuiz extends BaseTimeEntity {
    /**
     * PK: OX_quiz_id    bigint
     * OX_question    text
     * OX_answer    varchar(1)
     * OX_explanation    text
     */

    @Id
    @Column(name="ox_quiz_id")
    private Long oxQuizId;

    private String oxQuestion;

    @Column(length = 1)
    private String oxAnswer;

    private String oxExplanation;
}