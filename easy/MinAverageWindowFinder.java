// パターン: 固定長のウィンドウを1つずつずらしながら合計値を求め、平均が最小になるウィンドウの開始・終了位置を特定する
// 効果: ウィンドウ内の値をリストに保持せず、開始位置・終了位置の値だけを直接記録することでメモリ効率を改善
// 計算量: O(M*N) (M=全体のレコード数、N=ウィンドウ幅)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MinAverageWindowFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        var recordCount = Integer.parseInt(input.get(0).split(" ")[0]);
        var windowSize = Integer.parseInt(input.get(0).split(" ")[1]);
        var recordLines = input.subList(1, recordCount + 1);
        var windowAverages = new double[recordCount - windowSize + 1];
        var startArray = new int[recordCount - windowSize + 1];
        var endArray = new int[recordCount - windowSize + 1];

        for (var i = 0; i < windowAverages.length; i++) {
            var total = 0;
            var startPosition = -1;
            var endPosition = -1;
            for (var j = i; j < windowSize + i; j++) {
                var fields = recordLines.get(j).split(" ");
                var position = Integer.parseInt(fields[0]);
                var value = Integer.parseInt(fields[1]);
                if (j == i) startPosition = position;
                if (j == windowSize + i - 1) endPosition = position;
                total += value;
            }
            windowAverages[i] = (double) total / windowSize;
            startArray[i] = startPosition;
            endArray[i] = endPosition;
        }

        var minAverage = Arrays.stream(windowAverages).min().getAsDouble();
        var idx = -1;
        for (var i = 0; i < windowAverages.length; i++) {
            if (minAverage == windowAverages[i]) {
                idx = i;
                break;
            }
        }

        System.out.println(startArray[idx] + " " + endArray[idx]);
    }
}
