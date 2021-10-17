import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
// declaracion de variables que voy a usar durante el programa
        String text="", role;
        int menu, sec_menu, pop_champ_count ;
        double rework_sum;
        boolean role_exists;
        ArrayList<Champion> championlist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

// Leer Fichero-----------------------------------------------------------------------------------------------------------------------------------------
        try {
            text = new String(Files.readAllBytes(Path.of("champions.csv")));
        } catch (IOException e) {
            System.out.println("Error while opening the file.");
            e.printStackTrace();
        }
       // System.out.println(text);

// Separar y meter champions en la lista-------------------------------------------------------------------------------------------

        for(String linea : text.split("\n")){
            Champion aux = Champion.fromLine(linea);
            championlist.add(aux);
        }

//Menu con funcionalidades-----------------------------------------------------------------------------------------------------------------------------------------
        try {
            do {
                rework_sum = 0;
                pop_champ_count=0;
                role_exists=false;

                System.out.println("--Champions--\n1.Rework champion\n2.Show reworked\n3.Show popular difficulty\n4.Show images by role\n5.Exit\n\nEnter an option: ");
                menu = sc.nextInt();

                if (menu>0 && menu<6){
                    switch (menu) {
                        case 1:
                            try {
                                System.out.println("Enter an identifier: ");
                                sec_menu = sc.nextInt();
                                if(sec_menu<championlist.size() && sec_menu>0){
                                    for (Champion champion : championlist) {
                                        if (champion.getId() == sec_menu) {
                                            champion.setReworked(true);
                                            System.out.println("Reworked: " + champion.getId() + "- " + champion.getName() + ", " + champion.getTitle());
                                        }
                                    }
                                }else System.out.println("Enter an id that belongs to a champion (your id is too low or too high)");
                            } catch (InputMismatchException e) {
                                System.out.println("Insert a correct option");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            for (Champion champion : championlist) {
                                if (champion.isReworked()) {
                                    System.out.println(champion.getId()+"- "+champion.getName()+", "+champion.getTitle());
                                }
                            }
                            break;
                        case 3:
                            for (Champion champion : championlist) {
                                if (champion.getSkins() >= 10) {
                                    rework_sum += champion.getDifficulty();
                                    pop_champ_count++;
                                }
                            }
                            System.out.println("Average difficulty for popular champions: " + rework_sum / pop_champ_count);
                            break;
                        case 4:
                            System.out.println("Enter a role: ");
                            role = sc.next();
                            for (Champion champion : championlist) {
                                if (champion.getRole().compareTo(role) == 0) {
                                    System.out.println(champion.getUrl());
                                    role_exists=true;
                                }
                            }
                            if(!role_exists){
                                System.out.println("Enter a role that exists");
                            }
                            break;
                    }
                }else System.out.println("Enter a correct option.");
                System.out.println();
            } while (menu != 5);

        }catch (InputMismatchException e){
            System.out.println("Insert a correct option\n");
            e.printStackTrace();
        }
        System.out.println("Bye!\n");
    }
}
