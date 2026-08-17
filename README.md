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

#### `easy/DottedQuadValidator.java`
正規表現の量指定子(`\.+`)による連続区切り文字の吸収、空文字チェック・桁数チェックによるInteger.parseInt()前のバリデーション(桁あふれ例外対策)、判定ロジックをboolean返却メソッドへ切り出すことでlabeled continueの重複を解消するリファクタリング(continueはメソッド境界を越えられないためreturnで代替)

入力例
```
4
192.168.0.1
192.400.1.10.1000...
0..33.444...
4.3.2.1
```
出力例
```
True
False
False
True
```

#### `easy/MinAverageWindowFinder.java`
固定長ウィンドウを1つずつずらしながらの合計値計算(スライディングウィンドウ)、ウィンドウ内の値をリスト化せず開始・終了位置だけ直接記録する省メモリな実装、Arrays.stream().min()による最小平均値の取得

入力例
```
5 3
1 10
2 5
3 20
4 3
5 8
```
出力例
```
2 4
```

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

#### `easy/PeakBloomDayFinder.java`
配列インデックスをタイミング(日付)として使う頻度カウント(バケット法)、Arrays.stream().max()による最大値取得、同率最大が複数ある場合に先頭から探索して最小インデックスを採用

入力例
```
4
1 3
2 2
1 1
1 2
```
出力例
```
4
```

#### `easy/GroupedSumDescendingSort.java`
名前一覧をMapへ先に0で初期化(1件も加算されないキーの取りこぼし対策)、複数行の集計データをMapに加算、Map.Entryのリスト化+sort()による値の降順ランキング作成

入力例
```
3
A B C
4
A 1000
B 1000
B 2000
C 2000
```
出力例
```
B
C
A
```
