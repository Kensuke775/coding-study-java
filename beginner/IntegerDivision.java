// パターン: 標準入力の読み込み + 整数除算
// 効果: BufferedReaderで1行ずつ読み込み、Integer.parseIntで数値変換してから計算する基本形
// 計算量: O(1)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerDivision {
    static final int DIVISOR = 3; // 割る数

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 標準入力を読み込むためのオブジェクト
        List<String> input = new ArrayList<>(); // 読み込んだ行を格納するリスト
        String line; // 1行分の文字列を一時的に格納する変数
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int n = Integer.parseInt(input.get(0)); // 割られる数（入力値）
        System.out.println(n / DIVISOR);
    }
}
