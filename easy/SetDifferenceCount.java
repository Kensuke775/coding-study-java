// パターン: 2つの期間(全体から直近を除いた前半 / 直近部分)をそれぞれSetにし、removeAll()で差集合を求める
// 効果: HashSetの自動重複排除とSet.removeAll()を組み合わせ、「過去に登場したが直近には登場していない」要素数を求める
// 計算量: O(N + M)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetDifferenceCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int recentWindow = Integer.parseInt(input.get(0).split(" ")[0]); // 直近の除外対象期間
        int totalWindow = Integer.parseInt(input.get(0).split(" ")[1]);  // 全体の対象期間

        List<String> olderPeriodList = input.subList(input.size() - totalWindow, input.size() - recentWindow); // 直近を除いた前半
        List<String> recentPeriodList = input.subList(input.size() - recentWindow, input.size());              // 直近部分

        Set<String> olderPeriodSet = new HashSet<>(olderPeriodList);   // 前半の重複なし集合
        Set<String> recentPeriodSet = new HashSet<>(recentPeriodList); // 直近の重複なし集合

        olderPeriodSet.removeAll(recentPeriodSet); // 直近にも登場した要素を除外(差集合)

        System.out.println(olderPeriodSet.size());
    }
}
