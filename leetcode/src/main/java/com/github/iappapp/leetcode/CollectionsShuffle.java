package com.github.iappapp.leetcode;

import com.github.iappapp.modal.Card;

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


