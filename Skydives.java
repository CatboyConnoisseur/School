import java.util.ArrayList;
import java.util.Random;

public class Skydives {
    public int[] Dives(String[] ClassChecks, int rounds) {
        Random random = new Random();
        int randInt;
        int diveNum = 0;
        int[] A = {2, 4, 6, 7, 9, 15, 19, 21}; // all dives must be 3 formations long, as 1-22 are all worth 2 points.
        int[] AA = {1, 2, 3, 6, 7, 8, 9, 11, 13, 14, 15, 18, 19, 20, 21, 22}; // all dives must be 3 formations long, as 1-22 are all worth 2 points.
        int[] AAA = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38};
        int[] AClassDive = new int[3];
        int[] AAClassDive = new int[3];
        ArrayList<Integer> AAAClassDive = new ArrayList<Integer>();
        ArrayList<Integer> AUsed = new ArrayList<Integer>();
        ArrayList<Integer> AAUsed = new ArrayList<Integer>();
        ArrayList<Integer> AAAUsedNum = new ArrayList<Integer>();
        ArrayList<Integer> AAAUsedChar = new ArrayList<Integer>();
            diveNum = 0;
            if (ClassChecks[0].equals("y")) {
                while (diveNum < 3) {
                    if (AUsed.size() < 8) {
                        randInt = random.nextInt(8);
                        while (AUsed.contains(randInt)) {
                            randInt = random.nextInt(8);
                        }
                        randInt = A[randInt];
                        for (int i = 1; i < 4; i++) {
                            while (AClassDive[i - 1] == randInt) {
                                randInt = random.nextInt(8);
                                randInt = A[randInt];
                            }
                        }
                        for (int i = 2; i < 5; i++) {
                            while (AClassDive[i - 2] == randInt) {
                                randInt = random.nextInt(8);
                                randInt = A[randInt];
                            }
                        }
                        AUsed.add(randInt);
                        if (randInt != 4) {
                            AAUsed.add(randInt);
                        }
                        AAAUsedNum.add(randInt);
                        AClassDive[diveNum] = randInt;
                        diveNum++;
                    } else {
                        AUsed.clear();
                    }
                }
            }
            diveNum = 0;
            if (ClassChecks[1].equals("y")) {
                while (diveNum < 3) {
                    if (AAUsed.size() < 16) {
                        randInt = random.nextInt(16);
                        while (AAUsed.contains(randInt)) {
                            randInt = random.nextInt(16);
                        }
                        randInt = AA[randInt];
                        for (int i = 1; i < 4; i++) {
                            while (AAClassDive[i - 1] == randInt) {
                                randInt = random.nextInt(16);
                                randInt = AA[randInt];
                            }
                        }
                        for (int i = 2; i < 5; i++) {
                            while (AAClassDive[i - 2] == randInt) {
                                randInt = random.nextInt(16);
                                randInt = AA[randInt];
                            }
                        }
                        for (int j = 0; j < A.length; j++) {
                            if (A[j] == randInt) {
                                AUsed.add(randInt);
                            }
                        }
                        AAClassDive[diveNum] = randInt;
                        AAAUsedNum.add(randInt);
                        diveNum++;
                    } else {
                        AAUsed.clear();
                    }
                }
            }
            diveNum = 0;
            if (ClassChecks[2].equals("y")) {
                int points = 0;
                while (points < 5) {
                    randInt = random.nextInt(38);
                    if (randInt < 23) {
                        randInt = AAA[randInt];

                        while (AAAUsedNum.contains(randInt)) {
                            randInt = random.nextInt(22) + 1;
                        }
                        if (AAAUsedNum.size() == 22) {
                            AAAUsedNum.clear();
                        }
                        points += 2;
                        for (int i = 1; i < AAAClassDive.size(); i++) {
                            while (AAAClassDive.get(i - 1) == randInt) {
                                randInt = random.nextInt(22) + 1;
                            }
                        }
                        AAAClassDive.add(randInt);
                        AAAUsedNum.add(randInt);
                        AAUsed.add(randInt);
                        AUsed.add(randInt);
                        diveNum++;
                    }
                    if (randInt > 22) {
                        randInt = AAA[randInt];
                        while (AAAUsedChar.contains(randInt)) {
                            randInt = random.nextInt(16) + 23;
                        }
                        if (AAAUsedChar.size() == 16) {
                            AAAUsedChar.clear();
                        }
                        points++;
                        for (int i = 1; i < AAAClassDive.size(); i++) {
                            while (AAAClassDive.get(i - 1) == randInt) {
                                randInt = random.nextInt(16) + 23;
                            }
                        }
                        AAAClassDive.add(randInt);
                        diveNum++;
                    }
                }
            }

        int[] anotherArray = new int[AClassDive.length + AAClassDive.length + AAAClassDive.size()];
        for (int i = 0; i < AClassDive.length + AAClassDive.length + AAAClassDive.size(); i++){
            if (i < AClassDive.length){
                anotherArray[i] = AClassDive[i];
            }
            else if (i < AAClassDive.length + AClassDive.length){
                anotherArray[i] = AAClassDive[i - AClassDive.length];
            }
            else if (i < AAAClassDive.size() + AAClassDive.length + AClassDive.length){
                anotherArray[i] = AAAClassDive.get(i - AClassDive.length - AAClassDive.length);
            }
        }
    return anotherArray;
    }
}