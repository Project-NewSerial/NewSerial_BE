package com.example.newserial.domain.news.repository;

import com.example.newserial.domain.category.repository.Category;
import com.example.newserial.domain.quiz.repository.NewsQuiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String press;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String body;

    @NotNull
    private String url;

    @Column
    private String imageUrl;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp date; //YYYY-MM-DD hh:mm:ss.000000’ 형식, 2038–01–19 03:14:07.999999’

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "news")
    @PrimaryKeyJoinColumn
    private NewsQuiz newsQuiz;
}
