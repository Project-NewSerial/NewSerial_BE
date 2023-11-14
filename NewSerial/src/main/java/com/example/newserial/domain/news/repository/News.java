package com.example.newserial.domain.news.repository;

<<<<<<< HEAD:NewSerial/src/main/java/com/example/newserial/domain/news/News.java
import com.example.newserial.domain.bookmark.Bookmark;
import com.example.newserial.domain.category.Category;
=======
import com.example.newserial.domain.bookmark.repository.Bookmark;
import com.example.newserial.domain.category.repository.Category;
>>>>>>> 51772be23a5a9daf7bfd060e07f0519e3cf6ff25:NewSerial/src/main/java/com/example/newserial/domain/news/repository/News.java
import com.example.newserial.domain.memo.repository.Memo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String body;

    @NotNull
    private String url;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp date; //YYYY-MM-DD hh:mm:ss.000000’ 형식, 2038–01–19 03:14:07.999999’

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "news") //cascade = CascadeType.REMOVE ?
    private List<Memo> memos; //이게 맞나

//    @OneToOne(fetch = FetchType.LAZY)
//    private Bookmark bookmark;
}