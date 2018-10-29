package com.company;

import java.util.Scanner;

public class Main {
    static int enemyDamage,enemyHp=100,enemyDeff,enemyHeal;
    static int playerDamage=0,playerHp=100,playerDeff=0,playerHeal;
    static int charLv=1,charExp=0,charAtp=0,charDeff=0;
    // Player STAT
        // Basic STAT
        static int strength,agility,vitality,intellegent,dexterity;
        // Continous STAT
        static float criticalRate=0,criticalDamage=0,evasion=0,accuracy=100,healt=0,mana=0,
                attPower=0,magicPower=0,deffense=0,magicDeffense=0;
    // END Player STAT

    // STAT Setting
    static class statFixing {
        void strFixing(){
            criticalDamage = strength/10;
            attPower = strength*6/10;
        }
        void agiFixing(){
            criticalRate = agility/10;
            attPower = agility*4/10;
            accuracy += agility/15;
        }
        void vitFixing(){
            healt = vitality*10;
            deffense = vitality*5/10;
        }
        void intFixing(){
            mana = intellegent*10;
            magicPower = intellegent*6/10;
            magicDeffense = intellegent*5/10;
        }
        void dexFixing(){
            evasion = dexterity/15;
        }
    }
    // END STAT Setting

    // Setting Equip
    static class equipList {
        String name = "";
        int stat = 0;
        int minLv = 0;
        boolean equiped = false;
    }
    public static void addWeapon(equipList [] weapon){
        int stat = 10;
        int lv = 5;
        weapon[0].name = "Broadsword";
        weapon[1].name = "Claymore";
        for (int i = 0; i < 2; i++) {
            weapon[i].stat = stat;
            weapon[i].minLv = lv;
            stat+=10;
            lv+=5;
        }
    }
    public static void addHelmet(equipList [] helmet){
        int stat = 15;
        int lv = 5;
        helmet[0].name = "Copper Helmet";
        helmet[1].name = "Metal helmet";
        for (int i = 0; i < 2; i++) {
            helmet[i].stat = stat;
            helmet[i].minLv = lv;
            stat+=10;
            lv+=5;
        }
    }
    public static void addArmor(equipList [] armor){
        int stat = 20;
        int lv = 5;
        armor[0].name = "Bronze Armor";
        armor[1].name = "Chainmail";
        for (int i = 0; i < 2; i++) {
            armor[i].stat = stat;
            armor[i].minLv = lv;
            stat+=10;
            lv+=5;
        }
    }
    // END Setting Equip

    // Setting Enemy
    static class enemyList {
        String nama;
        int exp;
    }
    public static void addEnemy(enemyList [] enemy){
        int exp=50;
        enemy[0].nama="Slime";
        enemy[1].nama="Fungus";
        enemy[2].nama="Orc";
        enemy[3].nama="Ogre";
        for (int i = 0; i < enemy.length; i++) {
            enemy[i].exp = exp;
            exp+=50;
        }
    }
    // END Setting Enemy

    // Kalkulasi Atp dan Deff dengan Equip yang di pakai
    public static int atpCalculation(equipList [] weapon){
        int atp = 0;
        for (int i = 0; i < weapon.length; i++) {
            if (weapon[i].equiped == true){
                atp = weapon[i].stat;
            }
        }
        return atp;
    }
    public static int deffCalculation(equipList [] helmet,equipList [] armor){
        int helmetDeff = 0;
        int armorDeff = 0;
        for (int i = 0; i < helmet.length; i++) {
            if (helmet[i].equiped == true){
                helmetDeff = helmet[i].stat;
            }
        }
        for (int i = 0; i < armor.length; i++) {
            if (armor[i].equiped == true){
                armorDeff = armor[i].stat;
            }
        }
        int totalDeff = helmetDeff + armorDeff;
        return totalDeff;
    }
    // END Kalkulasi Atp dan Deff dengan Equip yang di pakai

    // Kalkulasi Level dan Exp
    public static int expCalculation(int exp){
        int [] expToLvUp = {0,100,200,300,400,500,600,700,800,900};
        int lv = 1;
        for (int i = 0; i < expToLvUp.length; i++) {
            if (exp >= expToLvUp[i] && exp < expToLvUp[i+1]){
                lv = i+1;
            }
        }
        return lv;
    }
    // END Kalkulasi Level dan Exp

    // Action
    public static int action_attack(){
        int damage = ((int) (Math.random()*30+10));
        return damage;
    }
    public static int action_deff(){
        int targetDeff = ((int) (Math.random()*60+20));
        return targetDeff;
    }
    public static int action_heal(){
        int heal = ((int) (Math.random()*20+10));
        return heal;
    }
    // END Action

    // Hitung output damage
    public static int hitung_damage(int dmg,int deff) {
        int damage_output;
        int kalkulasi_deff = dmg*deff/100;
        damage_output = dmg - kalkulasi_deff;
        return damage_output;
    }
    // END Hitung output damage

    // Enemy Action
    public static int randomAction(){
        int action = ((int) (Math.random()*3));
        return action;
    }
    public static void enemyAction(){
        int enemyAction = randomAction();
        switch (enemyAction){
            case 0:
                enemyDamage = action_attack();
                playerHp -= hitung_damage(enemyDamage,playerDeff);
                if (playerHp <= 0) {
                    System.out.println("Kamu telah kalah !!");
                } else {
                    System.out.println("Musuh menyerang mu dengan damage " + enemyDamage);
                    System.out.println("Darahmu tersisa : " + playerHp);
                }
                break;
            case 1:
                enemyDeff = action_deff();
                System.out.println("Musuh melakukan deffense sebanyak : "+enemyDeff);
                break;
            case 2:
                enemyHeal = action_heal();
                enemyHp += enemyHeal;
                if (enemyHp > 100) {
                    enemyHp = 100;
                }
                System.out.println("Musuh Melakukan heal sebanyak : "+enemyHeal);
                System.out.println("Darah musuh sekarang adalah : "+enemyHp);
                break;
        }
    }
    // END Enemy Action

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] actionList = {"Attack","Deffend","Heal"};
        int action,menu,pilihMusuh,pilihEquip,equiped=0;
        boolean exit=false;

        // Setting Objek Equip
        equipList [] weaponList = new equipList[2];
        equipList [] helmetList = new equipList[2];
        equipList [] armorList = new equipList[2];
        for (int i = 0; i < weaponList.length; i++) {
            weaponList[i] = new equipList();
            helmetList[i] = new equipList();
            armorList[i] = new equipList();
        }
        addWeapon(weaponList);
        addHelmet(helmetList);
        addArmor(armorList);

        charAtp = atpCalculation(weaponList);
        charDeff = deffCalculation(helmetList,armorList);
        // END Setting Objek Equip

        // Setting Objek Enemy
        enemyList [] enemy = new enemyList[4];
        for (int i = 0; i < enemy.length; i++) {
            enemy[i] = new enemyList();
        }
        addEnemy(enemy);
        //END Setting Objek Enemy

        charLv = expCalculation(charExp);

        System.out.println("SELAMAT DATANG DI MyRPG \n" +
                "Silahkan pilih menu-menu di bawah untuk bermain");
        do {
            System.out.println("\n1. Informasi karakter \n" +
                    "2. Equip \n" +
                    "3. Pilih Stage \n" +
                    "4. Keluar");
            System.out.print("Pilihan : ");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("Informasi karaktermu : \n" +
                            "Level \t\t: " + charLv + "\n" +
                            "Exp \t\t: " + charExp + "\n" +
                            "Attack \t\t: " + charAtp + "\n" +
                            "Deff \t\t: " + charDeff);
                    break;
                case 2:
                    String eqWeaponName = "Belum Dipakai", eqHelmetName = "Belum Dipakai", eqArmorName = "Belum Dipakai";
                    int eqWeaponStat = 0, eqHelmetStat = 0, eqArmorStat = 0;
                    for (int i = 0; i < weaponList.length; i++) {
                        if (weaponList[i].equiped) {
                            equiped += 1;
                            eqWeaponName = weaponList[i].name;
                            eqWeaponStat = weaponList[i].stat;
                        }
                    }
                    for (int i = 0; i < helmetList.length; i++) {
                        if (helmetList[i].equiped) {
                            equiped += 1;
                            eqHelmetName = helmetList[i].name;
                            eqHelmetStat = helmetList[i].stat;
                        }
                    }
                    for (int i = 0; i < armorList.length; i++) {
                        if (armorList[i].equiped) {
                            equiped += 1;
                            eqArmorName = armorList[i].name;
                            eqArmorStat = armorList[i].stat;
                        }
                    }
                    System.out.println("Pilih nomor untuk mengganti equip : ");
                    System.out.println("1. Weapon \t: " + eqWeaponName + " (Attack +" + eqWeaponStat + ") \n" +
                            "2. Hemlet \t: " + eqHelmetName + " (Deffense +" + eqHelmetStat + ") \n" +
                            "3. Armor \t: " + eqArmorName + " (Deffense +" + eqArmorStat + ") \n" +
                            "4. Kembali");
                    System.out.print("Pilihan : ");
                    pilihEquip = sc.nextInt();
                    int lvEquip;
                    switch (pilihEquip){
                        case 1:
                            System.out.println("Pilih equip yang akan dipakai : ");
                            for (int i = 0; i < weaponList.length; i++) {
                                System.out.println((i+1)+". "+weaponList[i].name+" (Min. Lv "+weaponList[i].minLv+")");
                            }
                            System.out.print("Pilih Equip : ");
                            pilihEquip=sc.nextInt();
                            lvEquip = weaponList[pilihEquip-1].minLv;
                            if (charLv < lvEquip){
                                System.out.println("Level kamu tidak mencukupi untuk menggunakannya");
                            } else {
                                for (int i = 0; i < weaponList.length; i++) {
                                    if (weaponList[i].equiped){
                                        weaponList[i].equiped=false;
                                    }
                                }
                                weaponList[pilihEquip-1].equiped=true;
                                System.out.println("Senjata berhasil digunakan");
                            }
                            break;
                        case 2:
                            System.out.println("Pilih equip yang akan dipakai : ");
                            for (int i = 0; i < helmetList.length; i++) {
                                System.out.println((i+1)+". "+helmetList[i].name+" (Min. Lv "+helmetList[i].minLv+")");
                            }
                            System.out.print("Pilih Equip : ");
                            pilihEquip=sc.nextInt();
                            lvEquip = helmetList[pilihEquip-1].minLv;
                            if (charLv < lvEquip){
                                System.out.println("Level kamu tidak mencukupi untuk menggunakannya");
                            } else {
                                for (int i = 0; i < helmetList.length; i++) {
                                    if (helmetList[i].equiped){
                                        helmetList[i].equiped=false;
                                    }
                                }
                                helmetList[pilihEquip-1].equiped=true;
                                System.out.println("Helmet berhasil digunakan");
                            }
                            break;
                        case 3:
                            System.out.println("Pilih equip yang akan dipakai : ");
                            for (int i = 0; i < armorList.length; i++) {
                                System.out.println((i+1)+". "+armorList[i].name+" (Min. Lv "+armorList[i].minLv+")");
                            }
                            System.out.print("Pilih Equip : ");
                            pilihEquip=sc.nextInt();
                            lvEquip = armorList[pilihEquip-1].minLv;
                            if (charLv < lvEquip){
                                System.out.println("Level kamu tidak mencukupi untuk menggunakannya");
                            } else {
                                for (int i = 0; i < armorList.length; i++) {
                                    if (armorList[i].equiped){
                                        armorList[i].equiped=false;
                                    }
                                }
                                armorList[pilihEquip-1].equiped=true;
                                System.out.println("Armor berhasil digunakan");
                            }
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Pilihan salah");
                    }
                    break;
                case 3:
                    System.out.println("Pilih Musuh yang ingin kamu lawan : ");
                    for (int i = 0; i < enemy.length; i++) {
                        System.out.println((i + 1) + ". " + enemy[i].nama+" (Exp : "+enemy[i].exp+")");
                    }
                    System.out.print("Pilihan : ");
                    pilihMusuh = sc.nextInt();
                    int expGain = 0;
                    boolean lawan = false;
                    for (int i = 0; i < enemy.length; i++) {
                        if (pilihMusuh-1 == i) {
                            expGain = enemy[i].exp;
                            lawan = true;
                        }
                    }
                    if (!lawan){
                        System.out.println("Pilih nomor yang ada di daftar stage");
                        break;
                    }
                    int currentLv = charLv;
                    do {
                        System.out.println("Pilih Action : \n" +
                                "1. Attack \n" +
                                "2. Defend \n" +
                                "3. Heal \n" +
                                "4. Menyerah");
                        System.out.print("Masukan Pilihan : ");
                        action = sc.nextInt();
                        switch (action) {
                            case 1:
                                playerDamage = action_attack() + charAtp;
                                enemyHp -= hitung_damage(playerDamage, enemyDeff);
                                if (enemyHp <= 0) {
                                    System.out.println("Kamu menyerang musuh dengan damage : " + playerDamage);
                                    System.out.println("Musuh telah berhasil kamu kalahkan");
                                    System.out.println("Kamu mendapat " + expGain + " Exp");
                                    charExp += expGain;
                                    charLv = expCalculation(charExp);
                                    if (charLv > currentLv) {
                                        System.out.println("Selamat kamu telah naik level! \n" +
                                                "Level kamu sekarang adalah " + charLv);
                                    }
                                    break;
                                } else {
                                    System.out.println("Kamu menyerang musuh dengan damage : " + playerDamage);
                                    System.out.println("Darah musuh tersisa : " + enemyHp);
                                }

                                enemyAction();
                                break;
                            case 2:
                                playerDeff = action_deff() + charDeff;
                                System.out.println("Kamu melakukan Deffend sebesar : " + playerDeff);

                                enemyAction();
                                break;
                            case 3:
                                playerHeal = action_heal();
                                playerHp += playerHeal;
                                System.out.println("Kamu melakukan Heal sebanyak " + playerHeal);
                                System.out.println("Darah kamu sekarang adalah : "+playerHp);

                                enemyAction();
                                break;
                            case 4:
                                System.out.println("Kamu Kalah !!!");
                                playerHp = 0;
                                break;
                            default:
                                System.out.println("Pilihan salah");
                        }
                        enemyDeff=0;
                        playerDeff=0;
                    } while (playerHp > 0 && enemyHp > 0);

                    // Reset
                    playerHp=100;
                    enemyHp=100;
                    playerDeff=0;
                    enemyDeff=0;
                    // END reset

                    break;
                case 4:
                    exit=true;
            }
        }while (exit==false);
    }
}
