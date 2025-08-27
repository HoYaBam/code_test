package com.hoyabam.codeTest;

/*
    https://school.programmers.co.kr/learn/courses/30/lessons/42897
    도둑이 어느 마을을 털 계획을 하고 있습니다. 이 마을의 모든 집들은 아래 그림과 같이 동그랗게 배치되어 있습니다.
    각 집들은 서로 인접한 집들과 방범장치가 연결되어 있기 때문에 인접한 두 집을 털면 경보가 울립니다.

    각 집에 있는 돈이 담긴 배열 money가 주어질 때, 도둑이 훔칠 수 있는 돈의 최댓값을 return 하도록 solution 함수를 작성하세요.

    제한사항
    이 마을에 있는 집은 3개 이상 1,000,000개 이하입니다.
    money 배열의 각 원소는 0 이상 1,000 이하인 정수입니다.
    입출력 예
    money	return
    [1, 2, 3, 1]	4
 */
public class CT42897 {
    public static void main(String[] args) {
        int[] money = {5, 2, 3, 6, 5, 2, 20};
        int answer;

        int[] a = new int[money.length];
        a[0] = money[0];
        a[1] = Math.max(money[0], money[1]);

        int[] b = new int[money.length];
        b[0] = money[1];
        b[1] = Math.max(money[1], money[2]);

        for (int i = 2; i < money.length - 1; i++) {
            a[i] = Math.max(a[i - 1], money[i] + a[i - 2]);
            b[i] = Math.max(b[i - 1], money[i + 1] + b[i - 2]);
        }

        answer = Math.max(a[a.length - 2], b[b.length - 2]);
        System.out.println(answer);
    }
}
