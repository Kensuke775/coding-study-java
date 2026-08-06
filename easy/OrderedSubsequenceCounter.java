// パターン: 複数の文字列を走査し、固定パターンに含まれる文字だけを拾って順番に組み立て、一致するたびに循環的にカウントする
// 効果: StringBuilderで効率的に文字列を構築し、完成したらリセットして次の一致を数える
// 計算量: O(全文字数)（パターン自体は固定長なのでindexOf/charAtは実質O(1)）

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderedSubsequenceCounter {
    static final String PATTERN = "code"; // 一致させたい対象パターン（定数）

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }

        String[] words = input.get(1).split(" ");        // 走査対象の単語配列
        StringBuilder matchedSoFar = new StringBuilder(); // ここまで一致した文字を組み立てるバッファ
        int patternIndex = 0;                             // パターン内で次に期待する文字の位置
        int matchCount = 0;                               // 完全一致した回数
        for (String word : words) {                       // word: 現在処理中の単語
            for (int i = 0; i < word.length(); i++) {      // i: 単語内の文字位置
                char currentChar = word.charAt(i);         // 現在見ている1文字
                if (PATTERN.indexOf(currentChar) == -1) continue;
                char expectedChar = PATTERN.charAt(patternIndex); // パターン側で期待されている1文字
                if (currentChar == expectedChar) {
                    matchedSoFar.append(currentChar);
                    patternIndex = (patternIndex + 1) % PATTERN.length();
                }
                if (matchedSoFar.toString().equals(PATTERN)) {
                    matchedSoFar.setLength(0);
                    matchCount += 1;
                }
            }
        }
        System.out.println(matchCount);
    }
}
