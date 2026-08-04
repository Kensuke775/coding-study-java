// パターン: 標準入力の読み込み + 整数除算
// 効果: BufferedReaderで1行ずつ読み込み、Integer.parseIntで数値変換してから計算する基本形
// 計算量: O(1)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BambooSplit {
    static final int DIVISOR = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int n = Integer.parseInt(input.get(0));
        System.out.println(n / DIVISOR);
    }
}
