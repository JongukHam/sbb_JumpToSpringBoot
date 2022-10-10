package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


//일반적으로 엔티티에는 Setter 메서드를 구현하지 않고 사용하기를 권한다.
// 왜냐하면 엔티티는 데이터베이스와 바로 연결되어 있으므로 데이터를 자유롭게 변경할 수 있는
// Setter 메서드를 허용하는 것이 안전하지 않다고 판단하기 때문이다.
//엔티티를 생성할 경우에는 롬복의 @Builder 어노테이션을 통한 빌드패턴을 사용하고,
// 데이터를 변경해야 할 경우에는 그에 해당되는 메서드를 엔티티에 추가하여 데이터를 변경하면 된다.
@Getter
@Setter
@Entity
public class Question {
    //엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블 컬럼으로 인식한다.
    // 테이블 컬럼으로 인식하고 싶지 않은 경우에만 @Transient 애너테이션을 사용한다.


    //기본키(primary key) 설정
    @Id
    //@GeneratedValue는 데이터 저장시 값을 따로 지정하지 않아도 자동으로 1씩 증가하여 저장됨
    //strategy는 고유번호를 생성하는 옵션
    //GenerationType.IDENTITY 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다.
    //strategy 옵션을 생략할 경우에 @GeneratedValue 애너테이션이 지정된 컬럼들이 모두 동일한 시퀀스로 번호를 생성하기 때문에 일정한 순서의 고유번호를 가질수 없게 된다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //엔티티의 속성은 테이블의 컬럼명과 일치, 컬럼의 세부 설정을 위해 @Column 애너테이션을 사용
    // length는 컬럼의 길이를 설정
    @Column(length = 200)
    private String subject;

    //columnDefinition은 컬럼의 속성을 정의할 때 사용
    //columnDefinition = "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용
    @Column(columnDefinition = "TEXT")
    private String content;

    //실제 컬럼명은  create_date가 된다.
    //대소문자 형태의 카멜케이스(Camel Case) 이름은 create_date 처럼 모두 소문자로 변경되고
    // 언더바(_)로 단어가 구분되어 실제 테이블 컬럼명이 된다.
    private LocalDateTime createDate;

    // 답변과 질문이 N:1의 관계라면 질문과 답변은 1:N의 관계라고 할 수 있다.
    // 이런경우에는 @ManyToOne이 아닌 @OneToMany애너테이션을 사용한다.
    // Question 하나에 Answer는 여러개이므로 Question 엔티티에 추가할 답변의 속성은 List 형태로 구성해야 한다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;


}
