// パターン: 複数の入力値に対する「回数のカウント」と「合計値」の二重条件判定
// 効果: ループ内で回数と合計を同時に更新し、両条件を満たした時点でbreakする早期終了の最適化
// 計算量: O(N)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DualThresholdCheck {
    static final int REQUIRED_COUNT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int recordCount = Integer.parseInt(input.get(0));
        String[] thresholds = input.get(1).split(" ");
        int singleThreshold = Integer.parseInt(thresholds[0]);
        int totalThreshold = Integer.parseInt(thresholds[1]);
        String[] amounts = input.get(2).split(" ");

        boolean meetsBothConditions = false;
        int countOverThreshold = 0;
        int totalAmount = 0;
        for (String amountText : amounts) {
            int amount = Integer.parseInt(amountText);
            if (amount >= singleThreshold) countOverThreshold += 1;
            totalAmount += amount;
            if (countOverThreshold >= REQUIRED_COUNT && totalAmount >= totalThreshold) {
                meetsBothConditions = true;
                break;
            }
        }
        System.out.println(meetsBothConditions ? "qualified" : "not-qualified");
    }
}
