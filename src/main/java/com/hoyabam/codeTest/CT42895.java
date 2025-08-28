package com.hoyabam.codeTest;

import java.util.HashSet;
import java.util.Set;

/*
    https://school.programmers.co.kr/learn/courses/30/lessons/42895

    아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
    12 = 5 + 5 + (5 / 5) + (5 / 5)
    12 = 55 / 5 + 5 / 5
    12 = (55 + 5) / 5

    5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
    이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

    제한사항
    N은 1 이상 9 이하입니다.
    number는 1 이상 32,000 이하입니다.
    수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
    최솟값이 8보다 크면 -1을 return 합니다.

    입출력 예
    N	number	return
    5	12	    4
    2	11	    3
    5   31168   -1
 */
public class CT42895 {
    private static int answer = 9;

    public static void main(String[] args) {
        int N = 5;
        int number = 12;

        if (N == number) answer = 1;

        int[] Ns = new int[8];
        Ns[0] = N;
        for (int i = 1; i < 8; i++) {
            Ns[i] = (Ns[i - 1] * 10) + N;
        }

        for (int i = 0; i < Ns.length; i++) {
            Set<Integer> innerList = new HashSet<>();
            innerList.add(Ns[i]);
            DP(i + 1, Ns, number, innerList);
        }

        if (answer > 8) answer = -1;

        System.out.println(answer);
    }

    private static void DP(int index, int[] Ns, int number, Set<Integer> nsList) {
        if (index > 8) return;
        if (nsList.contains(number)) {
            answer = Math.min(answer, index);
            return;
        }

        for (int i = 0; i < Ns.length; i++) {
            int cur = Ns[i];
            Set<Integer> newSet = new HashSet<>();

            for (Integer num : nsList) {
                newSet.add(num + cur);
                newSet.add(num - cur);
                newSet.add(cur - num);
                newSet.add(num * cur);
                if (cur != 0) newSet.add(num / cur);
                if (num != 0) newSet.add(cur / num);
            }

            DP(index + i + 1, Ns, number, newSet); // Ns[i]는 i+1번 사용했으므로 인덱스 증가
        }
    }
}