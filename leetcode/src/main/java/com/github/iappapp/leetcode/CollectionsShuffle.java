package com.github.iappapp.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionsShuffle {

    public static void main(String[] args) {
        List<Card> cards = new ArrayList<Card>();
        // 生成一副牌
        for (int rank = Card.THREE; rank <= Card.DEUCE; rank++) {
            cards.add(new Card(Card.DIAMOND, rank));
            cards.add(new Card(Card.CLUB, rank));
            cards.add(new Card(Card.HEART, rank));
            cards.add(new Card(Card.SPADE, rank));
        }
        cards.add(new Card(Card.JOKER, Card.BLACK));
        cards.add(new Card(Card.JOKER, Card.COLOR));
        System.out.println(cards.toString());
        /*
         * [方块3, 梅花3, 红桃3, 黑桃3, 方块4, 梅花4, 红桃4, 黑桃4, 方块5, 梅花5, 红桃5, 黑桃5, 方块6,
         * 梅花6, 红桃6, 黑桃6, 方块7, 梅花7, 红桃7, 黑桃7, 方块8, 梅花8, 红桃8, 黑桃8, 方块9, 梅花9, 红桃9,
         * 黑桃9, 方块10, 梅花10, 红桃10, 黑桃10, 方块J, 梅花J, 红桃J, 黑桃J, 方块Q, 梅花Q, 红桃Q, 黑桃Q,
         * 方块K, 梅花K, 红桃K, 黑桃K, 方块A, 梅花A, 红桃A, 黑桃A, 方块2, 梅花2, 红桃2, 黑桃2, 小王, 大王]
         */
        // 经典洗牌算法
        Random random = new Random();
        for (int i = cards.size(); i > 1; i--) {
            int m = random.nextInt(i);
            swap(cards, i - 1, m);
        }
        System.out.println(cards.toString());
        /*
         * [黑桃7, 黑桃A, 梅花A, 红桃9, 梅花4, 红桃K, 方块5, 梅花7, 梅花6, 方块A, 黑桃Q, 梅花5, 红桃10,
         * 梅花Q, 梅花J, 方块J, 梅花K, 方块8, 方块6, 方块10, 红桃7, 方块K, 红桃6, 黑桃2, 黑桃K, 梅花10,
         * 红桃8, 方块Q, 红桃Q, 大王, 梅花3, 梅花2, 方块7, 方块9, 方块4, 红桃3, 梅花9, 红桃J, 黑桃8, 红桃2,
         * 黑桃6, 红桃A, 黑桃9, 黑桃4, 黑桃J, 黑桃10, 小王, 黑桃3, 黑桃5, 红桃5, 红桃4, 方块2, 方块3, 梅花8]
         */

    }

    public static void swap(List<?> list, int i, int j) {
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }
}

class Card {

    public static final int DIAMOND = 0; // 方块(钻石)
    public final static int CLUB = 1; // 梅花
    public static final int HEART = 2; // 红桃(红心)
    public static final int SPADE = 3; // 黑桃(花锄)
    public static final int JOKER = 4; // 王

    public final static int THREE = 0;
    public final static int FOUR = 1;
    public final static int FIVE = 2;
    public final static int SIX = 3;
    public final static int SEVEN = 4;
    public final static int EIGHT = 5;
    public final static int NINE = 6;
    public final static int TEN = 7;
    public final static int JACK = 8;// J
    public final static int QUEEN = 9;// Q
    public final static int KING = 10;// K
    public final static int ACE = 11;// A
    public final static int DEUCE = 12; // 2
    public final static int BLACK = 13; // 小王
    public final static int COLOR = 14;// 大王

    /** 花色 0代表方块, 1代表梅花, 2代表红桃, 3代表黑桃,4:王 */
    private int suit;
    /** 点数 规定: 0代表3, 1代表4, 2代表5,... */
    private int rank;

    public Card() {
    }

    public Card(int suit, int rank) {
        // this.rank = rank;
        // this.suit = suit;
        setRank(rank);
        setSuit(suit);
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        if (suit < DIAMOND || suit > JOKER)
            throw new RuntimeException("花色超过范围!");
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        if (rank < THREE || rank > COLOR) {
            throw new RuntimeException("点数超过范围!");
        }
        this.rank = rank;
    }

    private static final String[] RANK_NAMES = { "3", "4", "5", "6", "7", "8",
            "9", "10", "J", "Q", "K", "A", "2", "小王", "大王" };
    private static final String[] SUIT_NAMES = { "方块", "梅花", "红桃", "黑桃", "" };

    // 覆盖Object 类的toStirng() 方法. 实现对象的文本描述
    public String toString() {
        return SUIT_NAMES[suit] + RANK_NAMES[rank];
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Card) {
            Card other = (Card) obj;
            return this.rank == other.rank && this.suit == other.suit;
        }
        return false;
    }

    public int hashCode() {
        // return suit*100+rank;
        // suit=3= 00000000 00000000 00000000 00000011
        // rank=10=00000000 00000000 00000000 00000011
        // suit<<16=00000000 00000011 00000000 00000000
        // 00000000 00000011 00000000 00000011
        return (suit << 16) + rank;// (x<<16)+y
    }
}
