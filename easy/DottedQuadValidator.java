// パターン: 区切り文字で分割した各要素に対し、空文字チェック→桁数チェック→数値変換→範囲チェックの順で早期リターンしながら検証する
// 効果: 桁数の多い文字列をInteger.parseInt()に渡す前に弾くことで、桁あふれ例外(NumberFormatException)を未然に防ぐ
// 計算量: O(M*K) (M=判定対象の件数、K=1件あたりの区切り数)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DottedQuadValidator {
    static final int OCTET_MIN = 0;
    static final int OCTET_MAX = 255;
    static final int OCTET_MAX_DIGITS = 3;
    static final int REQUIRED_SEGMENT_COUNT = 4;

    static boolean isValidOctet(int value){
        return value >= OCTET_MIN && value <= OCTET_MAX;
    }

    static boolean isValidDottedQuad(String record){
        var segments = record.split("\\.+");
        if (segments.length != REQUIRED_SEGMENT_COUNT) {
            return false;
        }
        for (String segment : segments) {
            if (segment.isEmpty() || segment.length() > OCTET_MAX_DIGITS) {
                return false;
            }
            var value = Integer.parseInt(segment);
            if (!isValidOctet(value)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        var recordCount = Integer.parseInt(input.get(0).split(" ")[0]);
        var recordList = input.subList(1, recordCount + 1);
        for(String record: recordList){
            System.out.println(isValidDottedQuad(record) ? "True" : "False");
        }
    }
}
