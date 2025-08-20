package com.hoyabam.codeTest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

class CT12939 {
    private static final Logger log = LoggerFactory.getLogger(CT12939.class);

    /*
        https://school.programmers.co.kr/learn/courses/30/lessons/12939
        문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다. str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
        예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.

        제한 조건
        s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.
         */
    public static void main(String[] args) {
        String s = "-3 -2 -4 -5"; //	"1 4"
        String answer = "";
        String[] tokens = s.split(" ");
        IntStream.Builder negBuilder = IntStream.builder();
        IntStream.Builder posBuilder = IntStream.builder();

        for (String token : tokens) {
            int num = Integer.parseInt(token);
            if (num < 0) {
                negBuilder.add(num);
            } else if (num > 0) {
                posBuilder.add(num);
            }
        }

        int[] neg = negBuilder.build().toArray();
        int[] pos = posBuilder.build().toArray();

        if (pos.length == 0) {
            answer = Arrays.stream(neg).min().getAsInt() + " " + Arrays.stream(neg).max().getAsInt();
        } else if (neg.length == 0) {
            answer = Arrays.stream(pos).min().getAsInt() + " " + Arrays.stream(pos).max().getAsInt();
        } else {
            answer = Arrays.stream(neg).min().getAsInt() + " " + Arrays.stream(pos).max().getAsInt();
        }

        System.out.println(answer);
    }
}
