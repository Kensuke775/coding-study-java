# 第5章 クラスの宣言とインスタンス化 — 復習問題まとめ

教科書の5章末問題（問題5-1〜5-10）を解いた後、同じ論点を数値・コードを変えて出題し直した
オリジナル復習問題17問と、その解答・解説をまとめたもの。全問正解まで確認済み。

対応する教科書ページ: 5章（クラスの宣言とインスタンス化, p.195〜255）

---

## 1. メンバ変数宣言の妥当性

```java
public class Book {
    int pages = 0;
    final String title;
    var isbn = "1234567890";
    private double price;
    protected static int totalCount = 0;
}
```
メンバ変数の宣言として、コンパイルが成功するものはいくつありますか。(1つ選択)

A. すべて　B. 4つ　**C. 3つ**　D. 2つ　E. 1つ

**解答: C**

| 行 | 結果 | 理由 |
|---|---|---|
| `int pages = 0;` | ✅ | 問題なし |
| `final String title;` | ❌ | blank final。コンストラクタが1つも定義されていないため、デフォルトコンストラクタが`title`を初期化せず「変数titleはデフォルトコンストラクタで初期化されていません」というコンパイルエラーになる |
| `var isbn = "...";` | ❌ | `var`はローカル変数専用。メンバ変数（フィールド）の宣言には使えない |
| `private double price;` | ✅ | finalではないため未初期化でもOK（デフォルト値0.0が入る） |
| `protected static int totalCount = 0;` | ✅ | 問題なし |

有効なのは`pages`・`price`・`totalCount`の3つ。

---

## 2. カプセル化の修正（パッケージをまたぐアクセス）

```java
// shopパッケージ
package shop;
public class Coupon {
    /* w */ int code;
    /* x */ String getCode() { return String.valueOf(code); }
    /* y */ void setCode(int code) { this.code = code; }
}

// storeパッケージ
package store;
import shop.Coupon;
public class Main {
    public static void main(String[] args) {
        Coupon c = new Coupon();
        c.setCode(100);
        System.out.println(c.getCode());
    }
}
```
Couponクラスを適切にカプセル化し、プログラムが正常に動作するための修正はどれですか。(1つ選択)

A. wをpublicにし、xとyをprivateにする　**B. wをprivateにし、xとyをpublicにする**　C. wをprivateにする　D. xとyをprotectedにする　E. 何も変更する必要はない

**解答: B**

- フィールド`code`は`private`にしてカプセル化する
- `getCode`/`setCode`は**別パッケージ（store）から呼ばれる**ため`public`が必須
- Cのように`w`だけprivateにしても、getter/setterがデフォルトアクセスのままだと別パッケージから呼べずコンパイルエラーになる
- Dの`protected`も同一パッケージ＋サブクラス限定のアクセスなので、無関係パッケージの`store.Main`からは呼べない

---

## 3. オーバーロードと戻り値の代入互換性

```java
public class Calc {
    public static void main(String[] args) {
        Calc obj = new Calc();
        double result = obj.compute(3, 4);
        // insert code here
    }
}
```
挿入し、コンパイルが成功するメソッドはどれですか。(1つ選択)

**A.** `public int compute(int a, int b) { return a + b; }`
B. `public void compute(int a, int b) { return a + b; }`
C. `public double compute(int...a) { double sum = 0; for (int v : a) sum += v; }`
D. `public void compute(int a, double b) { double z = a + b; }`
E. `public double compute(int a) { return a; }`

**解答: A**

- A：戻り値`int`は`double`へ**暗黙の拡大変換**が効くため`double result`への代入OK
- B：`void`メソッドの中で`return a + b;`（値を返している）→ コンパイルエラー
- C：戻り値`double`を宣言しているのに`return`文が無い → missing return statement
- D：戻り値`void`を`double`変数へ代入 → 型不一致
- E：呼び出しは`compute(3, 4)`（引数2個）だが定義は引数1個 → シグネチャ不一致で呼び出し自体が成立しない

---

## 4. コンストラクタの`this()`委譲トレース

```java
public class Ticket {
    private int number;
    private String type;
    public Ticket() {
        this(0, "General");
    }
    public Ticket(int number, String type) {
        this.number = number;
        this.type = type;
    }
    public void show() {
        System.out.print(number + ":" + type + " ");
    }
    public static void main(String[] args) {
        new Ticket(1, "VIP").show();
        new Ticket().show();
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `1:VIP 0:General ` が出力される**　B. `0:General 1:VIP `　C. `1:VIP 1:VIP `　D. `0:General 0:General `　E. コンパイルエラー

**解答: A**

`new Ticket(1, "VIP")` → 直接2引数コンストラクタ→`1:VIP `。
`new Ticket()` → `this(0, "General")`経由で2引数コンストラクタに委譲→`0:General `。

---

## 5. インスタンスフィールドとローカル変数の名前衝突（static文脈）

```java
public class Logger {
    String status = "INIT";
    void update() { status = "RUNNING"; }
    void update(String s) {
        status = "DONE";
        System.out.print(status);
    }
    public static void main(String[] args) {
        String status = "START";
        Logger l = new Logger();
        l.update("X");
        System.out.print(l.status);
        l.update();
        System.out.print(status);
        System.out.print(l.status);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `DONEDONESTARTRUNNING`**　B. `DONERUNNINGSTARTDONE`　C. `DONESTARTRUNNINGDONE`　D. `STARTDONEDONERUNNING`　E. コンパイルエラー

**解答: A**

`main`内のローカル変数`status`と、`Logger`インスタンスの`status`フィールドは**別スコープの別物**（衝突しても合法）。
`l.update("X")`はインスタンスメソッド内で無修飾の`status`を使っており、これは`this.status`（インスタンスフィールド）を指す。ローカル変数`status`とは無関係。

1. `l.update("X")` → インスタンスの`status`="DONE" → 出力「DONE」
2. `print(l.status)` → "DONE" → 出力「DONE」
3. `l.update()` → インスタンスの`status`="RUNNING"（出力なし）
4. `print(status)` → mainのローカル変数（"START"のまま）→ 出力「START」
5. `print(l.status)` → "RUNNING" → 出力「RUNNING」

結合すると `DONEDONESTARTRUNNING`。

---

## 6. デフォルトコンストラクタが生成される条件

```java
class Alpha {}
class Beta { Beta() {} }
class Gamma { void Gamma() {} }
class Delta { Delta(int x) {} }
```
コンパイルするとデフォルトコンストラクタが生成されるクラスはどれですか。(1つ選択)

A. Alpha　B. Beta　C. Gamma　D. Delta　**E. AlphaとGamma**　F. BetaとDelta

**解答: E**

- **Alpha**：コンストラクタが何も定義されていない → デフォルトコンストラクタが生成される
- Beta：`Beta() {}`という明示的な無引数コンストラクタが存在する → デフォルトコンストラクタは生成**されない**（見た目は似ているが別物）
- **Gamma**：`void Gamma() {}`は**戻り値`void`が付いているため実はコンストラクタではなく普通の同名メソッド**。実質コンストラクタが1つも無い状態なので、デフォルトコンストラクタが生成される
- Delta：`Delta(int x) {}`という引数ありコンストラクタが定義済み → デフォルトコンストラクタは生成されない（`new Delta()`は逆にコンパイルエラーになる）

---

## 7. インスタンス化の構文

```java
public class Ticket {
    private String seat;
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
}
```
このクラスのインスタンス化を行う記述はどれですか。(2つ選択)

A. `new Ticket() = null;`　**B. `Ticket t = new Ticket();`**　**C. `Ticket t; t = new Ticket();`**　D. `Ticket t, t = new Ticket();`　E. `Ticket t = new Ticket.setSeat("A1");`

**解答: B, C**

- A：`new`式の結果はlvalueではないので代入できない → コンパイルエラー
- D：同一スコープ内で変数`t`を二重宣言している → 「variable t is already defined」でコンパイルエラー
- E：`Ticket.setSeat(...)`はインスタンスメソッドをクラス名経由で誤って呼んでいる形（`setSeat`は静的メソッドではない）→ コンパイルエラー

---

## 8. メソッド宣言の妥当性

メソッド宣言として適切な記述はどれですか。(2つ選択)

A. `public methodA() {}`　B. `public final static String methodB(int id) {}`　C. `void methodC(int id, int id) {}`　**D. `protected int methodD() { return 0; }`**　**E. `public static void methodE(String... args) { }`**　F. `public public void methodF() {}`

**解答: D, E**

- A：戻り値の型が無い → コンパイルエラー（「invalid method declaration; return type required」）
- B：`final`・`static`・`public`の同時使用自体は合法。ただし戻り値`String`を宣言しているのに`return`文が無い → missing return statement
- C：パラメータ名`id`が重複 → 「variable id is already defined」
- F：同じ修飾子`public`を2回書いている → 「repeated modifier」

---

## 9. カプセル化のメリット

カプセル化について正しい説明はどれですか。(2つ選択)

**A. フィールドへの不正な値の代入を防ぐことができる**　B. クラスの継承回数を減らすことができる　**C. メソッド経由でのみフィールドを操作させることで、内部実装を変更しても外部への影響を抑えられる**　D. staticメソッドをオーバーライドできるようになる　E. ガベージコレクタの実行タイミングを制御できる

**解答: A, C**

- A：フィールドを`private`にしてsetterのみで代入させることで、setter内にバリデーション（値チェック）を挟める。フィールドが`public`だと外部から無検証で直接代入されてしまう
- C：外部コードは「メソッドという公開契約（シグネチャ）」だけに依存する。フィールドの型・保持方法・計算ロジックなど**クラス内部の実装**をメソッドのシグネチャを変えずに変更しても、外部コードは無修正で動き続ける
- B・D・E：カプセル化と直接の因果関係がない（継承回数、staticメソッドのオーバーライド可否、GCタイミングはいずれもアクセス修飾子とは別の話）

---

## 10. オーバーロード解決の優先順位（フェーズ1: widening優先）

```java
public class Resolver {
    static void call(long x) { System.out.print("long "); }
    static void call(Integer x) { System.out.print("Integer "); }
    static void call(int... x) { System.out.print("varargs "); }
    public static void main(String[] args) {
        int a = 5;
        call(a);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `long ` が出力される**　B. `Integer `　C. `varargs `　D. コンパイルエラー　E. 実行時例外

**解答: A**

Javaのオーバーロード解決は3フェーズで行われ、**該当するメソッドが見つかった時点でそのフェーズだけが採用される**：

1. **フェーズ1（ボクシング・varargsなし）**：`int → long`の**暗黙の拡大変換**だけで`call(long)`が適用可能 → ここで確定
2. フェーズ2（ボクシングあり）：`call(Integer)`はここでようやく候補になるが、フェーズ1で決着済みのため到達しない
3. フェーズ3（varargs）：`call(int...)`はさらに優先度が低い

「拡大変換 > オートボクシング > 可変長引数」の優先順位を覚えておくこと。

---

## 11. 参照のコピー vs 値のコピー

```java
public class Box {
    int value;
    public static void main(String[] args) {
        Box b1 = new Box();
        b1.value = 10;
        Box b2 = b1;
        b2.value = 20;
        Box b3 = new Box();
        b3.value = b1.value;
        b3.value = 30;
        System.out.println(b1.value + "," + b2.value + "," + b3.value);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `20,20,30` が出力される**　B. `10,20,30`　C. `20,20,20`　D. `10,10,30`　E. コンパイルエラー

**解答: A**

- `Box b2 = b1;` は**新しいオブジェクトを作らず、参照（住所）をコピー**するだけ。b1とb2は同一オブジェクトを指す
- そのため`b2.value = 20;`はb1側にも反映される（b1.value も20になる）
- `b3.value = b1.value;` は`int`という**基本データ型の値そのもの**をコピーしている（参照コピーではない）。以降`b3.value`をいくら変更してもb1・b2には影響しない

| 変数 | 種類 | 代入で起きること |
|---|---|---|
| `Box b2 = b1;` | 参照型変数の代入 | 参照（同じオブジェクトへのリンク）のコピー |
| `b3.value = b1.value;` | 基本データ型の代入 | 値そのもののコピー（独立） |

---

## 12. コンストラクタのバグ修正（`this()`の位置と存在しないコンストラクタ）

```java
 1  public class Room {
 2      int number;
 3      String building;
 4      public void Room(String building) {
 5          this.building = building;
 6      }
 7      public Room(int number, String building) {
 8          this.building = building;
 9          this(number);
10      }
11      public void showInfo() {
12          System.out.println(number + ":" + building);
13      }
14  }
15  class Main {
16      public static void main(String[] args) {
17          Room r = new Room();
18          r.showInfo();
19      }
20  }
```
コンパイルが成功し、"5:North"と出力するために必要な修正はどれですか。(2つ選択)

A. 4行目の`void`を削除する　B. 8行目の`this.building = building;`を削除する　**C. 9行目の`this(number);`を`this.number = number;`に修正する**　D. 8,9行目の処理順を入れ替える　**E. 17行目を`Room r = new Room(5, "North");`にする**

**解答: C, E**

- **Dが不十分な理由**：`this(number)`が先頭文でないこと自体は問題の一因だが、そもそも`Room(int)`という1引数コンストラクタはクラス内に存在しない。8,9行目を入れ替えて`this(number)`を先頭文にしても、「該当するコンストラクタが無い」というエラーは解消されない
- **Cが正しい理由**：`this(number)`（＝存在しないコンストラクタ呼び出し）を、通常のフィールド代入`this.number = number;`に置き換えれば、先頭文である必要も、存在しないコンストラクタを呼ぶ必要も無くなる
- A：4行目の`void`付きメソッドは、コンストラクタとして使われていない未使用コードなので、削除しなくても出力結果には影響しない（放置してもコンパイルは通る）
- B：これを削除すると`building`が設定されず`null`になり、"5:null"になってしまう

---

## 13. 変数のスコープ（パラメータによるフィールドのシャドーイング）

```java
public class Player {
    int score = 0;
    void addScore(int score) {
        score = score + 10;
        this.score = score;
    }
    public static void main(String[] args) {
        Player p = new Player();
        p.addScore(5);
        System.out.println(p.score);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `15` が出力される**　B. `5`　C. `10`　D. `0`　E. コンパイルエラー

**解答: A**

パラメータ`score`は、同名のインスタンスフィールド`score`を**シャドーイング（覆い隠す）**。メソッド内で修飾なしの`score`は「一番近いスコープ」＝パラメータを指す。

```
addScore(5) 呼び出し
→ パラメータ score = 5（ローカル）
→ score = score + 10  → パラメータscore が 15 に
→ this.score = score  → 明示的にフィールドへ15を代入
```

### 補足：スコープ衝突の2パターン

| パターン | 可否 |
|---|---|
| フィールド ↔ パラメータ/ローカル変数（別スコープ） | ○ 許される（シャドーイング。`this.`で区別） |
| パラメータ ↔ 同じメソッド内で改めて宣言するローカル変数（同一スコープ） | ✗ 許されない（二重定義エラー。問題7のD・問題8のCと同じ理屈） |

例えば`addScore`の中で改めて`int score = score + 10;`と書くと、パラメータと同じスコープ内での再定義になるため「variable score is already defined」でコンパイルエラーになる。

---

## 14. アクセス修飾子の可視性マトリックス（パッケージ・継承をまたぐ）

```java
// baseパッケージ
package base;
public class Animal {
    public int a = 1;
    protected int b = 2;
    int c = 3;          // デフォルトアクセス
    private int d = 4;
}

package base;
public class Zoo {
    void check() {
        Animal x = new Animal();
        System.out.println(x.a + x.b + x.c);
    }
}

package derived;
import base.Animal;
public class Dog extends Animal {
    void check() { System.out.println(a + b); }
}

package derived;
import base.Animal;
public class Other {
    void check() {
        Animal x = new Animal();
        System.out.println(x.a);
    }
}
```
コンパイルが成功するものはどれですか。(3つ選択)

**A. `Zoo`内の`x.a + x.b + x.c`**　B. `Zoo`内で`x.d`にアクセス　**C. `Dog`内の`a + b`**　D. `Dog`内で`c`に直接アクセス　**E. `Other`内の`x.a`**

**解答: A, C, E**

| 修飾子 | 同一クラス | 同一パッケージ | 別パッケージのサブクラス | 別パッケージの無関係クラス |
|---|---|---|---|---|
| `private` | ○ | ✗ | ✗ | ✗ |
| デフォルト（無指定） | ○ | ○ | ✗ | ✗ |
| `protected` | ○ | ○ | ○（継承経由のみ） | ✗ |
| `public` | ○ | ○ | ○ | ○ |

- **Zoo（Animalと同じ`base`パッケージ）**：`private`以外は全部OK → `a`,`b`,`c`はアクセス可、`d`は不可
- **Dog（別パッケージの`derived`のサブクラス）**：`protected`は継承経由でOKだが、**デフォルトアクセスは別パッケージだとサブクラスであっても不可** → `a`,`b`はOK、`c`はNG
- **Other（別パッケージの無関係クラス）**：`public`のみOK → `a`だけアクセス可

「デフォルトアクセスは同一パッケージなら誰でもOKだが、パッケージが違えばサブクラスでもNG（`protected`との対比）」が最大のひっかけポイント。

---

## 15. 可変長引数とオーバーロードの優先順位

```java
public class Formatter {
    static void print(int a, int b) { System.out.print("two-args "); }
    static void print(int... nums) { System.out.print("varargs "); }
    public static void main(String[] args) {
        print(1, 2);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `two-args ` が出力される**　B. `varargs `　C. コンパイルエラー（あいまいな呼び出し）　D. 実行時例外　E. 何も出力されない

**解答: A**

固定引数のシグネチャ（フェーズ1、ボクシング・varargsなしで解決可能）は、**常にvarargsより優先される**。あいまいな呼び出しにはならない——「固定引数の完全一致 > 可変長引数」という優先順位を機械的に覚えておくこと。

---

## 16. オブジェクトのライフサイクルとガベージコレクション対象の判定

```java
public class Sensor {
    public static void main(String[] args) {
        Sensor s1 = new Sensor();
        Sensor s2 = new Sensor();
        s1 = s2;
        s2 = null;
    }
}
```
4行目（`s2 = null;`）の実行完了時点で、GC対象となるオブジェクトはどれですか。(1つ選択)

**A. 最初に生成したオブジェクトのみ**　B. 2番目に生成したオブジェクトのみ　C. 最初と2番目の両方　D. どちらも対象にならない　E. コンパイルエラー

**解答: A**

参照の変化を1行ずつ追う：

```java
Sensor s1 = new Sensor();  // オブジェクトA生成: s1 → A
Sensor s2 = new Sensor();  // オブジェクトB生成: s2 → B
s1 = s2;                   // s1 → B に変更。この瞬間、Aを指すものが誰もいなくなる → Aだけが即GC対象に
s2 = null;                 // s2 → null。しかしs1は依然としてBを指している
```

`s2 = null;`実行後もs1はBを指し続けているため、**Bは参照が残っており生き続ける**。GC対象になるのは「誰からも参照されなくなった瞬間」のオブジェクトであり、その瞬間はAについては3行目（`s1 = s2;`）の時点ですでに訪れている。

### 補足：`s1 = null;`にした場合との比較

```java
s1 = s2;      // s1 → B。Aは即GC対象
s1 = null;    // s1 → null。s2 は一切変更していないのでBを指したまま
```
`s2`という別の変数は無関係に存在し続けるため、`s1`をnullにしてもs2経由でBへの参照は残る。**変数への代入は、その変数自身の指し先だけを変える**（他の変数を巻き込まない）という点がポイント。

---

## 17. staticフィールドの初期化タイミングと共有

```java
public class Counter2 {
    static int total = 0;
    int id;
    public Counter2() {
        total++;
        id = total;
    }
    public static void main(String[] args) {
        Counter2 a = new Counter2();
        Counter2 b = new Counter2();
        Counter2 c = new Counter2();
        System.out.println(a.id + "," + b.id + "," + c.id + "," + Counter2.total);
    }
}
```
実行結果はどれですか。(1つ選択)

**A. `1,2,3,3` が出力される**　B. `0,1,2,3`　C. `1,2,3,0`　D. `3,3,3,3`　E. コンパイルエラー

**解答: A**

コンストラクタ内の無修飾名の解決順序：ローカル変数/パラメータ → インスタンスフィールド → staticフィールド。

```java
public Counter2() {
    Counter2.total++;   // total はインスタンスフィールドではないのでstaticフィールドと解決される（クラスに1つだけ共有）
    this.id = total;    // id はインスタンスフィールドと解決される（オブジェクトごとに独立）
}
```

`a`,`b`,`c`という3つの別々のインスタンスが生成されても、`total`はクラスに1つしか無いため3つとも同じ`total`を共有インクリメントし続け、生成時点の値がそれぞれの`id`に「スナップショット」として記録される。これが`1,2,3,3`という結果になる仕組み。

---

## 実験メモ：`Counter.java`（chap5/ex16）での検証

```java
public class Counter {
    static int count;
    public void Counter(int count) { this.count = count; }  // voidが付いているためコンストラクタではない
    public void reset() { count = 0; }
    public static void update(int c) { count += c; }
    public static void main(String[] args) {
        Counter c1 = new Counter();
        c1.update(3);
        Counter.reset();       // reset()がstaticでないため実は「non-static method reset() cannot be referenced from a static context」でコンパイルエラー
        count--;
        new Counter().count++; // 未代入のnew。一時オブジェクトはこの文の後すぐGC対象になるが、countはstaticなので値には影響しない
        Counter c2 = new Counter();
        c2.update(1);
        System.out.println(Counter.count);
    }
}
```

- `new Counter()`を変数に代入しない書き方は、「そのオブジェクトに1度だけアクセスすればよい場合の慣用パターン」（教科書 chap5/11 Item.javaの`new Item(200, "Jacket").printItem();`と同じ発想）
- ただし今回のように**staticフィールドへのアクセスにこの書き方を使うのは非推奨スタイル**。`インスタンス.staticフィールド`は文法上は合法だが、実質`ClassName.staticフィールド`と同じ意味であり、生成したインスタンス自体は無意味な一時オブジェクトになる
- 「裏に何かインスタンスが残り続ける」という理解は誤り。**残り続けるのはクラスに1つだけ存在するstaticフィールドの値そのもの**であり、インスタンスは生成されてもすぐGC対象になる使い捨て
