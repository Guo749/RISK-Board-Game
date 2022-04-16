# 1 需要上交材料

* UML， 初期一份，终期一份
* 利用好 Agile / Gitlab / issues / feature branches / pull requests
* Text-Based Client and A Server





# 2 游戏需求分析



## 2.1 游戏界面

* 前期 Text
* 后期 需要 Graphical User Interface





## 2.2 Units

* 单独呈现
* 主题不定



## 2.3 玩家动作



### 2.3.1 Move

`A *move* action lets a player relocate units within their own territories.`

要求

* 只要有一条路，能从A -> B 并且没有经过其他国家，就可以move









### 2.3.2 Attack

* 打仗的规则需要隔离

    





### 2.3.3 综合

move orders happen before attack orders





## 2.4 Timing

* 所有玩家每一轮，需要都作出决定后，再执行对应操作
    * only when they commit their moves



































