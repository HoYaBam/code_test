package com.eastho.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;


@SpringBootTest
class ServerExpansionTests {
/*
당신은 온라인 게임을 운영하고 있습니다. 같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요합니다.
어느 시간대의 이용자가 m명 미만이라면, 서버 증설이 필요하지 않습니다. 어느 시간대의 이용자가 n x m명 이상 (n + 1) x m명 미만이라면 최소 n대의 증설된 서버가 운영 중이어야 합니다.
한 번 증설한 서버는 k시간 동안 운영하고 그 이후에는 반납합니다. 예를 들어, k = 5 일 때 10시에 증설한 서버는 10 ~ 15시에만 운영됩니다.
하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야 하는지 알고 싶습니다. 같은 시간대에 서버를 x대 증설했다면 해당 시간대의 증설 횟수는 x회입니다.
다음은 m = 3, k = 5 일 때의 시간대별 증설된 서버의 수와 증설 횟수 예시입니다.

[0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5]	3	5	7
[0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0]	5	1	11
[0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1]	1	1	12
*/

    @Test
    void contextLoads() {
//        int[] players = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
//        int[] players = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        int answer = 0;

        HashMap<Integer, Integer> server = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            int serverCount = 0;
            int player = players[i];

            System.out.println(server);
            if (server.get((i-k)) != null) {
                server.remove((i-k));
            }

            for (Integer serverValue : server.values()) {
                serverCount += serverValue;
            }

            if (player >= m) {
                if (serverCount != 0) {
                    if ((serverCount + 1) * m > player && player <= serverCount * m) {
                        continue;
                    }
                    player = player - (serverCount * m);
                }

                if (player / m <= 0) {
                    continue;
                }
                server.put(i, player / m);
                answer += player / m;
            }
        }
        System.out.println(answer);
    }
}
