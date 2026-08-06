// パターン: 標準入力の読み込み(3行) + しきい値判定による条件付き加算
// 効果: 3つの数値(しきい値, 加算量, 現在値)を読み込み、現在値がしきい値以下なら加算量を足す
// 計算量: O(1)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThresholdRestock {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int threshold = Integer.parseInt(input.get(0));      // しきい値
        int restockAmount = Integer.parseInt(input.get(1));  // 補充する量
        int currentStock = Integer.parseInt(input.get(2));   // 現在の在庫数
        if (currentStock <= threshold) {
            currentStock += restockAmount;
        }
        System.out.println(currentStock);
    }
}
