// パターン: 複数の入力値に対する「回数のカウント」と「合計値」の二重条件判定
// 効果: ループ内で回数と合計を同時に更新し、両条件を満たした時点でbreakする早期終了の最適化
// 計算量: O(N)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DualThresholdCheck {
    static final int COUNTS = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            input.add(line);
        }
        int record = Integer.parseInt(input.get(0));
        String[] parts = input.get(1).split(" ");
        int minSingleAmount = Integer.parseInt(parts[0]);
        int minTotalAmount = Integer.parseInt(parts[1]);
        String[] shoppingRecords = input.get(2).split(" ");
        boolean isSilver = false;
        int totalCounts = 0;
        int totalAmounts = 0;
        for(String recordText : shoppingRecords){
            int amount = Integer.parseInt(recordText);
            if(amount >= minSingleAmount) totalCounts += 1;
            totalAmounts += amount;
            if(totalCounts >= COUNTS && totalAmounts >= minTotalAmount) {
                isSilver = true;
                break;
            }
        }
        System.out.println(isSilver ? "silver" : "bronze");
    }
}
