package com.mysite.sbb.answer;


import com.mysite.sbb.question.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    //답변은 하나의 질문에 여러개가 달릴 수 있는 구조이다. 따라서 답변은 Many(많은 것)가 되고 질문은 One(하나)이 된다.
    // 따라서 @ManyToOne은 N:1 관계라고 할 수 있다.
    // 이렇게 @ManyToOne 애너테이션을 설정하면 Answer 엔티티의 question 속성과 Question 엔티티가 서로 연결된다.
    // (실제 데이터베이스에서는 ForeignKey 관계가 생성된다.)
    //@ManyToOne은 부모 자식 관계를 갖는 구조에서 사용한다. 여기서 부모는 Question, 자식은 Answer라고 할 수 있다.
    @ManyToOne
    private Question question;

}
