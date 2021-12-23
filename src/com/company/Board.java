package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        //입력을 받음
        Scanner s = new Scanner(System.in);
        while(true) {
            prompt(s);
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

    private static void prompt(Scanner s) throws FileNotFoundException, IOException {

        displayBoard();

        System.out.println(">> 입력을 원하면 w, 종료를 원하면 x를 입력해주세요.");
        String command = s.nextLine();
        if(command.equalsIgnoreCase("w")) {
            System.out.print("작성자 이름을 입력해주세요.");
            String writer = s.nextLine();

            System.out.print("글 내용을 입력해주세요.");
            String message = s.nextLine();

            int seq = 0;
            if(list.size() != 0) {
                seq = list.get(list.size() - 1).seq + 1;
            }

            list.add(new Article(seq, writer, message));
            System.out.println();

            // 직렬화 출력하기: 글이 작성되었으면 메모리의 객체를 직렬화한 뒤
            // 물리적인 파일로 하드디스크에 저장한다.
            try(FileOutputStream fos = new FileOutputStream("board.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(list);
            }
        } else if (command.equalsIgnoreCase("x")) {
            System.exit(0);
        }

    }

    private static void displayBoard() {
        System.out.println("순서\t글쓴이\t메시지");
        System.out.println("-----------------------");

        if(list.size() == 0) {
            System.out.println("[아직 글이 없습니다.]");
        }
        list.forEach(System.out::println);
    }
}
