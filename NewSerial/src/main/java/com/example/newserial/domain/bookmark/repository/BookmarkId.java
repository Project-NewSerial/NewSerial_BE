package com.example.newserial.domain.bookmark.repository;

import com.example.newserial.domain.member.repository.Member;
import com.example.newserial.domain.news.repository.News;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookmarkId implements Serializable {

    private Member member;
    private News news;
}
