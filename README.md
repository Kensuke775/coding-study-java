# coding-study-java

Java学習用のコーディングスタディをまとめたリポジトリです。

日々の学習として、少しずつ実装を追加していきます。

## 学習内容

### Beginner

#### `beginner/IntegerDivision.java`
標準入力の読み込み(BufferedReader)、文字列→数値変換(Integer.parseInt)、静的定数(static final)による整数除算の基本形

入力例
```
90
```
出力例
```
30
```

#### `beginner/ThresholdRestock.java`
標準入力3行の読み込み、しきい値判定(if文)による条件付き加算

入力例
```
2
12
1
```
出力例
```
13
```

### Easy

#### `easy/DualThresholdCheck.java`
split()による行内の値分割、for-eachループ、回数カウントと合計値の二重条件判定、条件成立時のbreakによる早期終了

入力例
```
5
800 3000
880 780 1080 970 430
```
出力例
```
qualified
```

#### `easy/OrderedSubsequenceCounter.java`
charAt()による1文字アクセス、String.indexOf()によるO(1)判定、StringBuilderでの効率的な文字列構築(setLength(0)でのリセット含む)、循環インデックスによる順序一致カウント

入力例
```
1
codecode
```
出力例
```
2
```

#### `easy/TieredCapacityMinCost.java`
subList()による範囲指定、階層的なキャパシティ判定(繰越容量を使い切りながらの多段階チェック)、List<Integer>のstream().mapToInt()による最小値取得

入力例
```
40 20 30
3
1000 40 20 40
800 30 30 30
1200 100 0 0
```
出力例
```
1000
```

#### `easy/TrimmedAverageTruncated.java`
Arrays.stream().map().collect()によるString[]→List<Integer>変換、stream().max()/min()による最大値・最小値取得、List.remove(Integer.valueOf(...))による値ベースの削除、int演算のみでの小数第1位切り捨て((int)キャストとdoubleリテラルの使い分け)

入力例
```
5
1 2 4 8 100
```
出力例
```
4.6
```

#### `easy/SetDifferenceCount.java`
HashSet/Setのimportの使い分け(インターフェースと実装クラス)、subList()による2つの期間の切り出し、Set.removeAll()による差集合の取得

入力例
```
3 6
tom
john
tom
paul
jimmy
jimmy
```
出力例
```
2
```
