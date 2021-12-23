package com.company;

import java.io.Serializable;

public class Article implements Serializable {

    private static final long serialVersionUID = 810939092043026342L;
    public int seq;
    public String writer;
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
