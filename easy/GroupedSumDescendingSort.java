// パターン: 名前一覧を先にMapへ0で初期化してから、複数行の集計データを加算していく
// 効果: 1件も加算されない(=対象データが無い)キーもMapから漏れずに残せる。Map.Entryのリスト化+sort()で値の降順ランキングを作る
// 計算量: O(N + M + NlogN)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupedSumDescendingSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        int recordCount = Integer.parseInt(input.get(2));                  // 集計対象の行数M
        String[] nameList = input.get(1).split(" ");                       // キーとなる名前の一覧
        Map<String, Integer> totalByName = new HashMap<>();                // 名前→合計値

        for (String name : nameList) {
            totalByName.put(name, 0);                                      // 1件も無い名前も先に0で登録しておく(境界値対策)
        }

        List<String> recordLines = input.subList(3, recordCount + 3);      // 集計対象の行一覧
        for (String recordLine : recordLines) {
            String name = recordLine.split(" ")[0];                        // 対象の名前
            int amount = Integer.parseInt(recordLine.split(" ")[1]);       // 加算する金額
            int currentTotal = totalByName.get(name);                      // これまでの合計
            totalByName.put(name, currentTotal + amount);                  // 合計を加算して更新
        }

        List<Map.Entry<String, Integer>> rankedEntries = new ArrayList<>(totalByName.entrySet()); // 集計結果のエントリ一覧
        rankedEntries.sort((a, b) -> b.getValue() - a.getValue());         // 合計値の降順に並び替え

        for (Map.Entry<String, Integer> entry : rankedEntries) {
            System.out.println(entry.getKey());                           // ランキング順に名前を出力
        }
    }
}
