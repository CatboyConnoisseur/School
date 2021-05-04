import java.util.*;
import java.util.Scanner;

public class SkydivingMain {
    public static void main(String args[]) {
        ArrayList<ArrayList<String>> ATeams = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> AATeams = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> AAATeams = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<Integer>> ATeamsScores = new ArrayList<ArrayList<Integer>>(); //Scores stored vertically, with team names horizontally
        ArrayList<ArrayList<Integer>> AATeamsScores = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> AAATeamsScores = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> ARounds = new ArrayList<ArrayList<Integer>>(); //Dives stored vertically, with rounds horizontally
        ArrayList<ArrayList<Integer>> AARounds = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> AAARounds = new ArrayList<ArrayList<Integer>>();
        ATeamsScores.add(new ArrayList<Integer>());
        AATeamsScores.add(new ArrayList<Integer>());
        AAATeamsScores.add(new ArrayList<Integer>());
        String[] ClassChecks = new String[3];

        Scanner sc = new Scanner(System.in);
        System.out.println("A class draw? y/n");
        ClassChecks[0] = sc.nextLine();
        int ACount = 0;
        int AACount = 0;
        int AAACount = 0;

        if (ClassChecks[0].equals("y")) {
            int i = 0;
            int j = 0;
            String conti;
            ATeams.add(new ArrayList<String>());
            do {
                ACount++;
                Scanner teams = new Scanner(System.in);
                System.out.println("Enter team name");
                ATeams.get(i).add(teams.nextLine());
                j = 0;
                for (int k = 0; k < 5; k++) {
                    teams = new Scanner(System.in);
                    System.out.println("Enter each players name followed by a return");
                    ATeams.get(i).add(j + 1, teams.nextLine());
                    j++;
                }
                Scanner cont = new Scanner(System.in);
                System.out.println("Enter another team? y/n");
                conti = cont.nextLine();
            } while (conti.equals("y"));
        }
        sc = new Scanner(System.in);
        System.out.println("AA class draw? y/n");
        ClassChecks[1] = sc.nextLine();
        if (ClassChecks[1].equals("y")) {
            int i = 0;
            int j = 0;
            String conti;
            AATeams.add(new ArrayList<String>());
            do {
                AACount++;
                Scanner teams = new Scanner(System.in);
                System.out.println("Enter team name");
                AATeams.get(i).add(teams.nextLine());
                j = 0;
                for (int k = 0; k < 5; k++) {
                    teams = new Scanner(System.in);
                    System.out.println("Enter each players name followed by a return");
                    AATeams.get(i).add(j + 1, teams.nextLine());
                    j++;
                }
                Scanner cont = new Scanner(System.in);
                System.out.println("Enter another team? y/n");
                conti = cont.nextLine();
            } while (conti.equals("y"));
        }
        sc = new Scanner(System.in);
        System.out.println("AAA class draw? y/n");
        ClassChecks[2] = sc.nextLine();
        if (ClassChecks[2].equals("y")) {
            int i = 0;
            int j = 0;
            String conti;
            AAATeams.add(new ArrayList<String>());
            do {
                AAACount++;
                Scanner teams = new Scanner(System.in);
                System.out.println("Enter team name");
                AAATeams.get(i).add(teams.nextLine());
                j = 0;
                for (int k = 0; k < 5; k++) {
                    teams = new Scanner(System.in);
                    System.out.println("Enter each players name followed by a return");
                    AAATeams.get(i).add(j + 1, teams.nextLine());
                    j++;
                }
                Scanner cont = new Scanner(System.in);
                System.out.println("Enter another team? y/n");
                conti = cont.nextLine();
            } while (conti.equals("y"));
        }
        int rounds = 6; // asks for 6 or 10 rounds (only if there are no A class draws)
        if (ClassChecks[0].equals("n")) {
            new Scanner(System.in);
            System.out.println("6 or 10 rounds?");
            rounds = sc.nextInt();
        }
        int[][] ADraws = new int[rounds][3];
        int[][] AADraws = new int[rounds][3];
        int[][] AAADraws = new int[rounds][5];
        int diveCount = 0;
        Skydives dives = new Skydives();
        for (int j = 0; j < rounds; j++) {
            int[] DivesList = dives.Dives(ClassChecks, rounds);
            if (ClassChecks[0].equals("y")) {
                System.out.println("A Class Dive Round " + (j + 1));
                for (int DivesNum = 0; DivesNum < 3; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    ADraws[j][DivesNum] = DivesList[DivesNum];
                }
                for (int i = 0; i < ATeams.size(); i++) {
                    System.out.println("Enter Score for team " + ATeams.get(0).get(i));
                    ATeamsScores.get(i).add(j, sc.nextInt());
                }
            }
            if (ClassChecks[1].equals("y") && ClassChecks[0].equals("y")) {
                System.out.println("AA Class Dive Round " + (j + 1));
                for (int DivesNum = 3; DivesNum < 6; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    AADraws[j][DivesNum - 3] = DivesList[DivesNum];
                }
                for (int i = 0; i < AATeams.size(); i++) {
                    System.out.println("Enter Score for team " + AATeams.get(i).get(0));
                    AATeamsScores.get(i).add(j, sc.nextInt());
                }
            } else if (ClassChecks[1].equals("y")) {
                System.out.println("AA Class Dive Round " + (j + 1));
                for (int DivesNum = 0; DivesNum < 3; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    AADraws[j][DivesNum] = DivesList[DivesNum];
                }
                for (int i = 0; i < AATeams.size(); i++) {
                    System.out.println("Enter Score for team " + AATeams.get(i).get(0));
                    AATeamsScores.get(i).add(j, sc.nextInt());
                }
            }
            if (ClassChecks[2].equals("y") && ClassChecks[0].equals("y") && ClassChecks[1].equals("y")) {
                System.out.println("AAA Class Dive Round " + (j + 1));
                for (int DivesNum = 6; DivesNum < DivesList.length; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    AAADraws[j][DivesNum - 6] = DivesList[DivesNum];
                }
                for (int i = 0; i < AAATeams.size(); i++) {
                    System.out.println("Enter Score for team " + AAATeams.get(i).get(0));
                    AAATeamsScores.get(i).add(j, sc.nextInt());
                }
            } else if (ClassChecks[2].equals("y") && (ClassChecks[0].equals("y") || ClassChecks[1].equals("y"))) {
                System.out.println("AAA Class Dive Round " + (j + 1));
                for (int DivesNum = 3; DivesNum < DivesList.length; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    AAADraws[j][DivesNum - 3] = DivesList[DivesNum];
                }
                for (int i = 0; i < AAATeams.size(); i++) {
                    System.out.println("Enter Score for team " + AAATeams.get(i).get(0));
                    AAATeamsScores.get(i).add(j, sc.nextInt());
                }
            } else if (ClassChecks[2].equals("y") && (ClassChecks[0].equals("n") && ClassChecks[1].equals("n"))) {
                System.out.println("AAA Class Dive Round " + (j + 1));
                for (int DivesNum = 0; DivesNum < DivesList.length; DivesNum++) {
                    System.out.println(DivesList[DivesNum]);
                    AAADraws[j][DivesNum] = DivesList[DivesNum];
                }
                for (int i = 0; i < AAATeams.size(); i++) {
                    System.out.println("Enter Score for team " + AAATeams.get(i).get(0));
                    AAATeamsScores.get(i).add(j, sc.nextInt());
                }
            }
        }
        //final scoreboard
        if (ClassChecks[0].equals("y")) {
            System.out.println("A Class:");
            for (int i = 0; i < ACount; i++) {
                int score = 0;
                System.out.println(ATeams.get(i).get(0));
                for (int k = 1; k < 6; k++) {
                    System.out.println(ATeams.get(i).get(k) + " ");
                }
                for (int j = 0; j < rounds; j++) {
                    System.out.println("Round " + (j + 1));
                    for (int l = 0; l < 3; l++) {
                        System.out.print(ADraws[j][l] + " ");
                    }
                    System.out.println("Score: " + ATeamsScores.get(i).get(j));
                    score += ATeamsScores.get(i).get(j);
                }
                System.out.println("Total: " + score);
                score /= rounds;
                System.out.println("Average: " + score);
            }
        }
        if (ClassChecks[1].equals("y")) {
            System.out.println("AA Class:");
            for (int i = 0; i < AACount; i++) {
                int score = 0;
                System.out.println(AATeams.get(i).get(0));
                for (int k = 1; k < 6; k++) {
                    System.out.println(AATeams.get(i).get(k) + " ");
                }
                for (int j = 0; j < rounds; j++) {
                    System.out.println("Round " + (j + 1));
                    for (int l = 0; l < 3; l++) {
                        System.out.print(AADraws[j][l] + " ");
                    }
                    System.out.println("Score: " + AATeamsScores.get(i).get(j));
                    score += AATeamsScores.get(i).get(j);
                }
                System.out.println("Total: " + score);
                score /= rounds;
                System.out.println("Average: " + score);
            }
        }
        if (ClassChecks[2].equals("y")) {
            System.out.println("AAA Class:");
            for (int i = 0; i < AAACount; i++) {
                int score = 0;
                System.out.println(AAATeams.get(i).get(0));
                for (int k = 1; k < 6; k++) {
                    System.out.println(AAATeams.get(i).get(k) + " ");
                }
                for (int j = 0; j < rounds; j++) {
                    System.out.println("Round " + (j + 1));
                    for (int l = 0; l < 3; l++) {
                        System.out.print(AAADraws[j][l] + " ");
                    }
                    System.out.println("Score: " + AAATeamsScores.get(i).get(j));
                    score += AAATeamsScores.get(i).get(j);
                }
                System.out.println("Total: " + score);
                score /= rounds;
                System.out.println("Average: " + score);
            }
        }
    }
}