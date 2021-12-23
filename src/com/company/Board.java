package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Board {

    static List<Article> list = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            // 직렬화된 파일을 읽어와 보여줌
            readFromFile();
        } catch(FileNotFoundException e) {
            // 없다면 e 발생
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        // FileInputStream을 통해 파일 입력 스트림 객체를 만든 후 "board.txt" 파일로부터 읽어온다.
        // fis를 바탕으로 오브젝트 입력 스트림을 생성한 뒤 readObject 한다.
        // 오브젝트 형태로 읽으면 안에 있는 내용이 무슨 타입인지 정확히 알기 어려우므로 타입 캐스팅 해준다.
        try(FileInputStream fis = new FileInputStream("board.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            list = (List<Article>) ois.readObject();
        }
    }
}
