// パターン: 配列から最大値・最小値を1つずつ除外し、残りの平均を小数第1位まで切り捨てて求める
// 効果: streamでmax/min/sumを取得、List.remove(Integer.valueOf(...))で値ベースの削除(インデックスではなく値一致の最初の1件のみ)、int演算のみで小数第1位までの切り捨てを実現
// 計算量: O(N)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrimmedAverageTruncated {
    static final int EXCLUDED_COUNT = 2; // 除外する件数（最大値1つ＋最小値1つ）

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int n = Integer.parseInt(input.get(0));               // 全体の件数
        int remainingCount = n - EXCLUDED_COUNT;               // 除外後に残る件数
        List<Integer> pointList = Arrays.stream(input.get(1).split(" "))
                                         .map(Integer::parseInt)
                                         .collect(Collectors.toList());
        int maxPoint = pointList.stream().mapToInt(Integer::intValue).max().getAsInt();
        int minPoint = pointList.stream().mapToInt(Integer::intValue).min().getAsInt();
        pointList.remove(Integer.valueOf(minPoint)); // 値が一致する最初の要素だけを削除（インデックス指定ではない）
        pointList.remove(Integer.valueOf(maxPoint));
        int total = pointList.stream().mapToInt(Integer::intValue).sum();
        double result = (int) (total * 10 / remainingCount) / 10.0; // 小数第1位までを残して切り捨て
        System.out.println(result);
    }
}
