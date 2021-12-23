package com.company;

import java.io.Serializable;

public class Article implements Serializable {

    private static final long serialVersionUID = 810939092043026342L;
    public int seq;
    public String writer;
    // 직렬화 시 transient가 붙은 대상을 제외하고 직렬화한다. transient필드는 역직렬화 시 null 또는 타입의 기본값으로 반환된다.
    public transient String message;

    public Article(int seq, String writer, String message) {
        this.seq = seq;
        this.writer = writer;
        this.message = message;
    }

    @Override
    public String toString() {
        return seq + "\t" + writer + "\t" + message;
    }

}
