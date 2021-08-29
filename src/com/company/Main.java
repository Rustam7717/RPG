package com.company;

import java.sql.SQLOutput;
import java.util.Random;

public class Main {

    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int heroesHealth[] = {250, 250, 250, 200, 400, 250, 250, 250};
    public static int heroesDamage[] = {20, 20, 20, 0, 10, 20, 20, 20};
    public static int medicianTreat = 15;
    public static String heroesAttackType[] = {"Physical", "Magical", "Mental", "Medical", "Tank", "Flash", "Bersek", "Thor"};


    public static void main(String[] args) {
        while (!isFinished()) {
            round();
        }


    }

    public static void round() {
        changeBossDefenceType();
        bossHit();
        setMedicianTreat();
        heroesHit();
        fightInfo();

    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;

        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[5] <= 0 && heroesHealth[6] <= 0 && heroesHealth[7] <= 0) {
            System.out.println("Boss won!!!");
            return true;

        }
        return false;

    }

    public static void changeBossDefenceType() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void bossHit() {
        if (heroesHealth[4] > 0) {
            tankAbility();

        } else {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] - bossDamage < 0)
                    heroesHealth[i] = 0;
                else
                    heroesHealth[i] -= bossDamage;
            }
        }


    }

    private static void tankAbility() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage / 2 < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] -= bossDamage / 2;

            }
            if (heroesHealth[4] - bossDamage / 2 < 0) {
                heroesHealth[4] = 0;

            } else
                heroesHealth[4] -= bossDamage / 2;


        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koef = random2.nextInt(2) + 2;
                    if (bossHealth - heroesHealth[i] * koef < 0) {
                        bossHealth = 0;

                    } else
                        bossHealth = bossHealth - heroesDamage[i] * koef;
                    System.out.println(heroesAttackType[i] + " critical hit " + heroesDamage[i] * koef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void setMedicianTreat() {
        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    heroesHealth[i] += medicianTreat;

                }

            }
        }
    }

    public static void fightInfo() {
        System.out.println(" ++++++++++++++++++++++++++++++");
        System.out.println("Boss health:" + bossHealth);
        System.out.println("Warrior health:" + heroesHealth[0]);
        System.out.println("Magic health:" + heroesHealth[1]);
        System.out.println("Kinetic health:" + heroesHealth[2]);
        System.out.println("Medical health:" + heroesHealth[3]);
        System.out.println("Tank health:" + heroesHealth[4]);
        System.out.println("Flash health:" + heroesHealth[5]);
        System.out.println("Bersek health:" + heroesHealth[6]);
        System.out.println("Thor health:" + heroesHealth[7]);
        System.out.println("________________________________");

    }

}
