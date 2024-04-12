package PandaZooData;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {

   /* private static PandaZoo zoo = new PandaZoo();
    private static int members_amount=-1;


    private static void instructions(int kind) {
        switch (kind) {
            case 1:
                System.out.println("Выберете команду для работы:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Добавить участника группы");
                System.out.println("2. Удалить участника группы");
                System.out.println("3. Работа с участниками группы");
                System.out.println("4. Вывести имена участников и их идентификаторы");
                System.out.println("5. Записать участников группы в файл");
                System.out.println("6. Заполнить группу участниками из файла");
                System.out.println("7. Выход");
                break;
            case 2:
                System.out.println("Выберете команду для работы с участниками группы:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Вывести описание участника группы");
                System.out.println("2. Вывести описание всех участников группы");
                System.out.println("3. Открыть фото участника группы");
                System.out.println("4. Открыть фото всех участников группы");
                System.out.println("5. Отправить млекопитающих участников группы на охоту");
                System.out.println("6. Отправить участников группы рыбного происхожения поплавать");
                System.out.println("7. Отправить участников группы муравьиного происхожения строить муравейник");
                System.out.println("8. Сериализовать участника");
                System.out.println("9. Выйти в главное меню");
                break;
            case 3:
                System.out.println("Выберете команду для добавления участника в группу:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Добавить Китайскую Панду");
                System.out.println("2. Добавить Красную Панду");
                System.out.println("3. Добавить Коридорас Панду (рыба)");
                System.out.println("4. Добавить Панду-муравья");
                System.out.println("5. Добавить случайного участника");
                System.out.println("6. Выход в главное меню");
                break;
        }
    }

    public static void callMenu(){

        Scanner in = new Scanner(System.in);
        System.out.println("**************************");
        System.out.println("Вы в главном меню!");
        System.out.println("**************************");
        instructions(1);

        try {
            int vvod = in.nextInt();
            while (vvod !=7)
            {
                switch (vvod) {
                    case 0:
                        instructions(1);
                        break;
                    case 1:
                        addMember();
                    case 2:
                        System.out.println("Введите индекс участника");
                        int index=in.nextInt();
                        if (index>=0 && index<=members_amount){
                            zoo.removePanda(index);
                            System.out.println("Участник успешно удален!");
                            members_amount--;
                        }
                        else
                            System.out.println("Участник с таким индексом еще не добавлен!");
                        break;
                    case 3:
                        MemberMenu();
                    case 4:
                        if (members_amount!=-1)
                            zoo.outputPanda();
                        else
                            System.out.println("Еще не добавлено ни одного участника!");
                        break;
                    case 5:
                        if (members_amount!=-1){
                            System.out.println("Введите путь к файлу");
                            in.nextLine();
                            try {
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(in.nextLine()));
                                oos.writeInt(members_amount);
                                for (int i = 0; i <= members_amount; i++) {
                                    if (zoo.list.get(i) instanceof BigPanda)
                                    {
                                        oos.writeInt(0);
                                        oos.writeObject((BigPanda) zoo.list.get(i));
                                    }
                                    else if (zoo.list.get(i) instanceof SmallPanda)
                                    {
                                        oos.writeInt(1);
                                        oos.writeObject((SmallPanda) zoo.list.get(i));
                                    }
                                    else if (zoo.list.get(i) instanceof PandaFish)
                                    {
                                        oos.writeInt(2);
                                        oos.writeObject((PandaFish) zoo.list.get(i));
                                    }
                                    else
                                    {
                                        oos.writeInt(3);
                                        oos.writeObject((PandaAnt) zoo.list.get(i));
                                    }
                                }
                                System.out.println("Данные успешно записаны в файл");
                            }
                            catch(IOException ex){System.out.println("Некорректный ввод пути");}
                        }
                        else
                            System.out.println("Еще не добавлено ни одного участника!");
                        break;
                    case 6:
                        System.out.println("Введите путь к файлу");
                        in.nextLine();
                        try {
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(in.nextLine()));
                            int amount = ois.readInt();
                            int c;
                            for (int i = 0; i <= amount; i++) {
                                c=ois.readInt();
                                members_amount++;
                                if (c==0)
                                    zoo.addPanda((BigPanda)ois.readObject());
                                else if (c==1)
                                    zoo.addPanda((SmallPanda)ois.readObject());
                                else if (c==2)
                                    zoo.addPanda((PandaFish)ois.readObject());
                                else
                                    zoo.addPanda((PandaAnt)ois.readObject());
                            }
                            System.out.println("Данные успешно считаны из файла");
                        }
                        catch(ClassNotFoundException ex){System.out.println("Класс не найден");}
                        catch(IOException ex){System.out.println("Некорректный ввод пути");}
                        break;
                    default:
                        System.out.println("Команды с таким номером нет - повторите ввод /:");
                        break;
                }
                System.out.println(">>>Введите следующую команду>>>>");
                System.out.println("(Введите 0 для вывода списка команд)");
                vvod = in.nextInt();
            }
            exit(0);
        }
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            callMenu();
        }

    }

   private static void addMember() // добавление участника
    {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("Вы вызвали меню добавления участника в группу");
        System.out.println("---------------------------------------------");
        instructions(3);

        try {
            int vvod = in.nextInt();
            while (vvod != 6) {
                if (vvod == 0)
                    instructions(3);
                else if (vvod > 6)
                    System.out.println("Команды с таким номером нет - повторите ввод");
                else {
                    members_amount++;
                    System.out.println("Введите кличку");
                    in.nextLine();
                    String name = in.nextLine();
                    if (vvod == 1)
                        zoo.addPanda(new BigPanda(name));
                    else if (vvod == 2)
                        zoo.addPanda(new SmallPanda(name));
                    else if (vvod == 3)
                        zoo.addPanda(new PandaFish(name));
                    else if (vvod == 4) {
                        System.out.println("Введите должность в муравейнике");
                        //in.nextLine();
                        //String position = in.nextLine();
                        zoo.addPanda(new PandaAnt(name, in.nextLine()));
                    } else {
                        Random random = new Random();
                        switch (random.nextInt(4)) {

                            case 0:
                                zoo.addPanda(new BigPanda(name));
                                break;
                            case 1:
                                zoo.addPanda(new SmallPanda(name));
                                break;
                            case 2:
                                zoo.addPanda(new PandaFish(name));
                                break;
                            case 3:
                                System.out.println("Введите должность в муравейнике");
                                String position = in.nextLine();
                                zoo.addPanda(new PandaAnt(name, position));
                                break;

                        }
                    }
                    System.out.println("-------Участник успешно добавлен!-------");

                }
                System.out.println(">>>Введите следующую команду>>>>");
                System.out.println("(Введите 0 для вывода списка команд)");
                vvod = in.nextInt();
            }
            callMenu();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            addMember();
        }
    }

    private static void MemberMenu(){

        Scanner in = new Scanner(System.in);
        System.out.println("...........................................");
        System.out.println("Вы вызвали меню работы с участниками группы");
        System.out.println("...........................................");
        instructions(2);

        try {
            int vvod = in.nextInt();
            while (vvod != 9) {

                if (vvod == 0)
                    instructions(2);
                else if (vvod > 10)
                    System.out.println("Команды с таким номером нет - повторите ввод");
                else if (members_amount != -1) {
                    switch (vvod) {

                        case 1:
                            System.out.println("Введите идентификатор участника");
                            int des = in.nextInt();
                            if (des>=0 && des<=members_amount)
                                zoo.getMemberDescription(des);
                            else
                                System.out.println("Участник с таким индексом еще не добавлен!");
                            break;
                        case 2:
                            for (int i = 0; i <= members_amount; i++)
                                zoo.getMemberDescription(i);
                            break;
                        case 3:
                            System.out.println("Введите идентификатор участника");
                            int ph = in.nextInt();
                            if (ph>=0 && ph<=members_amount)
                                zoo.getMemberPhoto(ph);
                            else
                                System.out.println("Участник с таким индексом еще не добавлен!");
                            break;
                        case 4:
                            for (int i = 0; i <= members_amount; i++)
                                zoo.getMemberPhoto(i);
                            break;
                        case 5:
                            zoo.makeMemberHunt();
                            break;
                        case 6:
                            zoo.makeMemberSwim();
                            break;
                        case 7:
                            zoo.makeMemberBuildAntHill();
                            break;
                        case 8:
                            System.out.println("Введите идентификатор участника");
                            int id = in.nextInt();
                            if (id>=0 && id<=members_amount){
                            System.out.println("Введите путь к файлу");
                            in.nextLine();
                            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream( in.nextLine())))
                            {
                                if (zoo.list.get(id) instanceof BigPanda)
                                    oos.writeObject((BigPanda)zoo.list.get(id));
                                else if (zoo.list.get(id) instanceof SmallPanda)
                                    oos.writeObject((SmallPanda)zoo.list.get(id));
                                else if (zoo.list.get(id) instanceof PandaFish)
                                    oos.writeObject((PandaFish)zoo.list.get(id));
                                else

                                System.out.println("Данные успешно записаны в файл");
                            }
                            catch(Exception ex){System.out.println("Некорректный ввод пути");}
                            }
                            else
                                System.out.println("Участник с таким индексом еще не добавлен!");
                            break;
                    }
                } else
                    System.out.println("Еще не добавлено ни одного участника!");
                System.out.println(">>>Введите следующую команду>>>>");
                System.out.println("(Введите 0 длoos.writeObject((PandaAnt)zoo.list.get(id));я вывода списка команд)");
                vvod = in.nextInt();
            }
            callMenu();
        }
        catch (InputMismatchException e)
            {
                System.out.println("Неккоректный ввод, повторите попытку /:");
                MemberMenu();
            }
    }
*/
}
