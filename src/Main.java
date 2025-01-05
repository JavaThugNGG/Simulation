import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Добро пожаловать в игру Симуляция!");
        MyMap.gameLoop();
        MyMap.printMap();
        System.out.println("Вы победили!");
    }
}