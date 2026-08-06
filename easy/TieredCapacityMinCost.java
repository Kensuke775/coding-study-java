// パターン: 階層的なキャパシティ判定(上位の枠は下位のものも収容可) + 条件を満たす候補の中から最小コストを探す
// 効果: 各階層で「繰越容量」を使い切りながら判定し、全階層を満たせば候補として記録、最後にstreamで最小値を取る
// 計算量: O(N × K)（Nは候補数、Kは階層数）

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TieredCapacityMinCost {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            input.add(line);
        }
        String[] itemCounts = input.get(0).split(" ");
        List<String> candidates = input.subList(2, input.size());
        List<Integer> feasibleCosts = new ArrayList<>();
        int result = -1;
        for (int i = 0; i < candidates.size(); i++) {
            int cost = Integer.parseInt(candidates.get(i).split(" ")[0]);
            int carryoverCapacity = 0;
            boolean fitsAllTiers = true;
            for (int j = 1; j <= itemCounts.length; j++) {
                int tierCapacity = Integer.parseInt(candidates.get(i).split(" ")[j]);
                int tierItemCount = Integer.parseInt(itemCounts[j - 1]);
                if (carryoverCapacity + tierCapacity < tierItemCount) {
                    fitsAllTiers = false;
                    break;
                }
                carryoverCapacity += tierCapacity - tierItemCount;
            }
            if (fitsAllTiers) feasibleCosts.add(cost);
        }
        if (feasibleCosts.size() > 0) result = feasibleCosts.stream().mapToInt(Integer::intValue).min().getAsInt();

        System.out.println(result);
    }
}
