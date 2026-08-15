// パターン: 各要素の発生タイミングを配列のインデックスとして頻度カウントし、最大値を持つ最小インデックスを求める
// 効果: ソートせずにバケット配列への加算だけで頻度の最大値とその最小出現位置を線形時間で特定
// 計算量: O(N + maxDay)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PeakBloomDayFinder {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        var itemCount = Integer.parseInt(input.get(0));
        var entryLines = input.subList(1, itemCount + 1);
        int[] bloomDays = new int[itemCount];
        for (var i = 0; i < itemCount; i++) {
            var fields = entryLines.get(i).split(" ");
            var daysToBloom = Integer.parseInt(fields[0]);
            var plantDay = Integer.parseInt(fields[1]);
            bloomDays[i] = daysToBloom + plantDay;
        }
        var maxBloomDay = Arrays.stream(bloomDays).max().getAsInt();
        var bloomCountByDay = new int[maxBloomDay];
        for (var i = 0; i < itemCount; i++) {
            var dayIndex = bloomDays[i] - 1;
            bloomCountByDay[dayIndex] += 1;
        }
        var maxCount = Arrays.stream(bloomCountByDay).max().getAsInt();
        for (var i = 0; i < bloomCountByDay.length; i++) {
            if (maxCount == bloomCountByDay[i]) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}
