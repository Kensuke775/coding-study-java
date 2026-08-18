// パターン: 移動方向を表す文字をMap<Character, int[]>で座標の差分に変換し、現在位置を更新しながらグリッドを走査する
// 効果: switch文やif-elseの分岐を書かずに、文字から移動量(行・列の差分)を1回のMap参照で取得できる
// 計算量: O(H*W + K) (H*W=グリッドの読み込み、K=移動コマンド数)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GridStepValuePrinter {
    static final Map<Character, int[]> DIRECTION_DELTAS = Map.of(
        'R', new int[]{0, 1},
        'L', new int[]{0, -1},
        'F', new int[]{-1, 0},
        'B', new int[]{1, 0}
    );

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        var gridHeight = Integer.parseInt(input.get(0).split(" ")[1]);
        var startPosition = input.get(1).split(" ");
        var curRow = Integer.parseInt(startPosition[0]) - 1;
        var curCol = Integer.parseInt(startPosition[1]) - 1;
        var directions = input.get(2).toCharArray();
        var gridRows = input.subList(3, 3 + gridHeight);

        int[][] grid = new int[gridRows.size()][];
        for (var i = 0; i < gridRows.size(); i++) {
            var fields = gridRows.get(i).split(" ");
            grid[i] = Arrays.stream(fields).mapToInt(Integer::parseInt).toArray();
        }

        for (char direction : directions) {
            var delta = DIRECTION_DELTAS.get(direction);
            curRow += delta[0];
            curCol += delta[1];
            System.out.println(grid[curRow][curCol]);
        }
    }
}
