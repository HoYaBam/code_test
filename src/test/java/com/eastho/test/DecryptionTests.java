package com.eastho.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class DecryptionTests {
// https://school.programmers.co.kr/learn/courses/30/lessons/388352?language=java
    /*
    10 ≤ n ≤ 30
    1 ≤ (q의 길이 = m) ≤ 10
    q[i]의 길이 = 5
    q[i]는 i+1번째 시도에서 입력한 5개의 서로 다른 정수를 담고 있으며, 오름차순으로 정렬되어 있습니다.
    1 ≤ q[i][j] ≤ n
    ans의 길이 = m
    ans[i]는 i+1번째 시도에서 입력한 5개의 정수 중 비밀 코드에 포함된 정수의 개수를 나타냅니다.
    0 ≤ ans[i] ≤ 5
    비밀 코드가 존재하지 않는(답이 0인) 경우는 주어지지 않습니다.

    코드 출처 : https://icodesiuuuu.tistory.com/136
     */
    static int answer = 0;
    static int[] arr;
	@Test
	void contextLoads() {
        int n = 10;
        int[][] q = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {3, 7, 8, 9, 10},
                {2, 5, 7, 9, 10},
                {3, 4, 5, 6, 7}
        };
        int[] ans = {2, 3, 4, 3, 3};

        arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = i+1;
        comb(n, q, ans, 0, new ArrayList<>());
        System.out.println(answer);
    }
    public void comb(int n, int[][] q, int[] ans, int cur, List<Integer> list) {
        if(list.size() == 5) {
            if(isPossible(q, ans, list)) answer++;
            return;
        }

        for(int i=cur; i<n; i++) {
            list.add(arr[i]);
            comb(n, q, ans, i+1, list);
            list.remove(list.size() - 1);
        }
    }

    public boolean isPossible(int[][] q, int[] ans, List<Integer> list) {
        for (int i=0 ; i< q.length; i++) {
            int cnt = 0;
            for (int j=0 ; j< q[i].length; j++) {
                for (Integer integer : list) {
                    if (integer == q[i][j]) {
                        cnt++;
                        break;
                    }
                }
            }
            if (cnt != ans[i]) return false;
        }
        return true;
    }
}
