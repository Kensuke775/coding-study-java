// パターン: 標準入力の読み込み(3行) + しきい値判定による条件付き加算
// 効果: 3つの数値(しきい値, 加算量, 現在値)を読み込み、現在値がしきい値以下なら加算量を足す
// 計算量: O(1)

//. JVM自体が外部から呼び出している
//これが一番重要な理由です。前に説明した通り、java Mainというコマンドを打つと、
// JVMというプログラムの外側にいる存在がMain.main(...)を呼び出しにきます。
// もしmainがpublicでなければ、JVMはこのメソッドにアクセスできず、実行できません。

//static インスタンス化(new)せずに使える。
//String[] argsはinput.txtの中身が入る。
//I+O＝Input/Output Exception = 例外

//List	設計図（インターフェース）
//ArrayList → 内部が配列。ランダムアクセス（get(i)）が速い
//LinkedList → 内部が連結リスト（前後のつながりで管理）。先頭・末尾への追加削除が速い

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
        int threshold = Integer.parseInt(input.get(0));
        int restockAmount = Integer.parseInt(input.get(1));
        int currentStock = Integer.parseInt(input.get(2));
        if (currentStock <= threshold) {
            currentStock += restockAmount;
        }
        System.out.println(currentStock);
    }
}
