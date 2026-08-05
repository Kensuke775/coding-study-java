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
