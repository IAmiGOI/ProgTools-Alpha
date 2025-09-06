import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

/*
 * TODO
 * Сделать модуль для заполнения двумерных массивов (матриц) DONE, нужна небольшая редакция
 * Расширить модули дли ввода/вывода через гуи
 * Возможно как-то ещё оптимизировать (чат гпт в помощь)
 * Начать работать с вложенными классами и передавать инфу через них, а не паровозиком как сейчас.
 * 
 */


public class ProgTools {

    //  Класс для формирования матрицы в формате Integer
    public static int[][] MatrixFormationInteger(){
        String stroka = null;

        //  Объявляем переменные координат в стринге
        String oxs = DataGet("Введите координаты длинну массива (ось x)");
        String oys = null;

        //  Проверяем координату x на соответствие условиям (как в обычном массиве)
        while (!FilterInt(oxs, new int[]{1, 1000})) {
            if (oxs== null) {
                    break;
                }
            oxs = DataGet("Введите координаты длинну массива (ось x)");
        }


        if (oxs != null){
            oys = DataGet("Введите координаты ширину массива (ось y)");

             //  Проверяем координату y на соответствие условиям (как в обычном массиве)
            while (!FilterInt(oys, new int[]{1, 1000})) {
                if (oys== null) {
                        break;
                    }
                oys = DataGet("Введите координаты ширину массива (ось y)");
            }


        // Если строка не нулевая, то промпт юзеру
        if (oys != null){
           stroka = DataGet("Авто заполнение? Если да, напишите 'auto' \nДля ручного заполнения введите 'manual'"); 
        }
        
    }

    //  Объявляем переменные координат уже в инте
    int ox = Integer.parseInt(oxs.trim());
    int oy = Integer.parseInt(oys.trim());

    //  Создаём матрицу из этих переменных
    int[][] matrix = new int[ox][oy];


    //  Если выбран авто режим, то заполняем прогой
    if (SpecWord(stroka, "auto")){

        //  Прогоняем по координатам рандомайзер
        for (int x = 0; x<= ox -1; x++){
            for (int y = 0; y<= oy -1; y++){
                matrix[x][y] = RandomInteger(100);
            }
        }
    }

    //  Если не выбран авто режим (не стал добавлять фильтр по manual, чтобы не добавлять защиту от ввода остального шлака)
    else{

        //  Прогоняем по координатам ввод юзера
        for (int x = 0; x<= ox -1; x++){
            for (int y = 0; y<= oy -1; y++){
                String data = DataGet("Введите элемент матрицы");

                //  Дефолт проверка на дебила
                while (!FilterInt(data, new int[]{0, 100000})) {
                    if (stroka == null) {
                        break;
                    }
                    stroka = DataGet("Ошибка! Введите элемент массива'");
                }
                    matrix[x][y] = Integer.parseInt(data.trim());
                }
            }
        }   
    return matrix;
        }

    //  Класс для формирования матрицы в формате Double
    public static double[][] MatrixFormationDouble(){
        String stroka = null;

        //  Объявляем переменные координат в стринге
        String oxs = DataGet("Введите координаты длинну массива (ось x)");
        String oys = null;

        //  Проверяем координату x на соответствие условиям (как в обычном массиве)
        while (!FilterInt(oxs, new int[]{1, 1000})) {
            if (oxs== null) {
                    break;
                }
            oxs = DataGet("Введите координаты длинну массива (ось x)");
        }


        if (oxs != null){
            oys = DataGet("Введите координаты ширину массива (ось y)");

             //  Проверяем координату y на соответствие условиям (как в обычном массиве)
            while (!FilterInt(oys, new int[]{1, 1000})) {
                if (oys== null) {
                        break;
                    }
                oys = DataGet("Введите координаты ширину массива (ось y)");
            }


        // Если строка не нулевая, то промпт юзеру
        if (oys != null){
           stroka = DataGet("Авто заполнение? Если да, напишите 'auto'. \nДля ручного заполнения введите 'manual'"); 
        }
        
    }

    //  Объявляем переменные координат уже в инте
    int ox = Integer.parseInt(oxs.trim());
    int oy = Integer.parseInt(oys.trim());

    //  Создаём матрицу из этих переменных
    double[][] matrix = new double[ox][oy];


    //  Если выбран авто режим, то заполняем прогой
    if (SpecWord(stroka, "auto")){

        //  Прогоняем по координатам рандомайзер
        for (int x = 0; x<= ox -1; x++){
            for (int y = 0; y<= oy -1; y++){
                matrix[x][y] = RandomDouble(1000000);
            }
        }
    }
    else{
        for (int x = 0; x<= ox -1; x++){
            for (int y = 0; y<= oy -1; y++){
                String data = DataGet("Введите элемент матрицы");
                while (!FilterInt(data, new int[]{0, 100000})) {
                    if (stroka == null) {
                        break;
                    }
                    stroka = DataGet("Ошибка! Введите элемент массива'");
                }
                    matrix[x][y] = Double.parseDouble(data.trim());
                }
            }
        }   
    return matrix;
        }
    

    //  Класс для формирования массива в формате Double
    public static ArrayList<Double> ArrayFormation() {

        //  Формируем лист, чтобы иметь возможность хот добавления элемента
        ArrayList<Double> list = new ArrayList<>();

        //  Промпт юзеру
        String stroka = DataGet("Авто заполнение? Если да, напишите 'auto'. \nДля ручного заполнения введите 'manual'. \nДля завершения введите 'stop'");

        //  Проверка на нажатие канцела
        if (stroka == null) {
            return list;
        }


        //  Если выбрали автозаполнение (ввели auto)
        if (SpecWord(stroka, "auto")) {

            Integer len = 0;

            //  Запрашиваем у юзер длинну массива
            stroka = DataGet("Введите длинну массива, максимум - 100 тыс элементов");
            while (!FilterInt(stroka, new int[]{0, 100000})) {

                //  Проверка на нажатие канцела
                if (stroka == null) {
                    break;
                }


                stroka = DataGet("Ошибка! Введите длинну массива");
                }

            //  Если строка не канцельнута, то заполняем массив
            if (stroka != null){
                
                //  Приводим длинну массива к инту
                len = Integer.parseInt(stroka.trim());
                

                //  Для каждого элемента массива подбираем рандомное число
                for (int i = 0; i<= len - 1; i++){
                    list.add(RandomDouble(10000000));
                }
            }
            return list;
        }


        //  Основной цикл заполненеия
        outer:
        while (!SpecWord(stroka, "stop")) {
            stroka = DataGet("Элемент массива (или 'stop')");

            //  Проверка на нажатие канцела
            if (stroka == null) {
                break;
            }

            // Повторяем запрос, пока не получим корректное число или 'stop'
            while (!FilterDouble(stroka, new double[]{-1.0, -1.0})) {
                if (SpecWord(stroka, "stop")) {
                    break outer;
                }
                stroka = DataGet("Ошибка! Введите число (или 'stop')");
                if (stroka == null) {
                    break outer;
                }
            }


            //  Проверяем, был ли ввелен стоп (по какой-то причине стоп выше иногда не работал, оставил так, вроде тепреь не пролетает)
            if (SpecWord(stroka, "stop")) break;

            //  К этому моменту мы отфильтровали остальные варианты, сейчас просто добавляем число в массив
            double formated = Double.parseDouble(stroka.trim());
            list.add(formated);
        }

        return list;
    }

//  Класс для формирования массива в формате Integer
    public static ArrayList<Integer> ArrayFormationInteger() {

        //  Формируем лист, чтобы иметь возможность хот добавления элемента
        ArrayList<Integer> list = new ArrayList<>();

        //  Промпт юзеру
        String stroka = DataGet("Авто заполнение? Если да, напишите 'auto'. \nДля ручного заполнения введите 'manual'. \nДля завершения введите 'stop'");

        //  Проверка на нажатие канцела
        if (stroka == null) {
            return list;
        }


        //  Если выбрали автозаполнение (ввели auto)
        if (SpecWord(stroka, "auto")) {

            Integer len = 0;

            //  Запрашиваем у юзер длинну массива
            stroka = DataGet("Введите длинну массива, максимум - 100 тыс элементов");
            while (!FilterInt(stroka, new int[]{0, 100000})) {

                //  Проверка на нажатие канцела
                if (stroka == null) {
                    break;
                }


                stroka = DataGet("Ошибка! Введите длинну массива");
                }

            //  Если строка не канцельнута, то заполняем массив
            if (stroka != null){
                
                //  Приводим длинну массива к инту
                len = Integer.parseInt(stroka.trim());
                

                //  Для каждого элемента массива подбираем рандомное число
                for (int i = 0; i<= len - 1; i++){
                    list.add(RandomInteger(10000000));
                }
            }
            return list;
        }


        //  Основной цикл заполненеия
        outer:
        while (!SpecWord(stroka, "stop")) {
            stroka = DataGet("Элемент массива (или 'stop')");

            //  Проверка на нажатие канцела
            if (stroka == null) {
                break;
            }

            // Повторяем запрос, пока не получим корректное число или 'stop'
            while (!FilterInt(stroka, new int[]{-1, -1})) {
                if (SpecWord(stroka, "stop")) {
                    break outer;
                }
                stroka = DataGet("Ошибка! Введите число (или 'stop')");
                if (stroka == null) {
                    break outer;
                }
            }


            //  Проверяем, был ли ввелен стоп (по какой-то причине стоп выше иногда не работал, оставил так, вроде тепреь не пролетает)
            if (SpecWord(stroka, "stop")) break;

            //  К этому моменту мы отфильтровали остальные варианты, сейчас просто добавляем число в массив
            int formated = Integer.parseInt(stroka.trim());
            list.add(formated);
        }

        return list;
    }

    //  S2E Функция для получения и фильтрации числа в формате интеджер. Имеет защиту от дебила и 1 спец слово. По своей сути является сбором кода из прочих модулей, поэтому без доп коментов.
    public static int GetNumberInt(int[] size, String stop_flag, String promt){
        String stroka = DataGet(promt);
            while (!FilterInt(stroka, size)) {
                if (SpecWord(stroka, stop_flag)) {
                    break;
                }
                if ((size[0] == -1 && size[1] == -1)){
                    stroka = DataGet("Ошибка! Введите число (или '" + stop_flag + "')");             
                     }
                else{
                    stroka = DataGet("Ошибка! Введите число в лимите между "+ size[0] + " и " + size[1] + " (или слово '" + stop_flag + "')");
                    }
                if (stroka == null) {
                    break;
                }
            }   
        return Integer.parseInt(stroka);
    }

    //  S2E Функция для получения и фильтрации числа в формате дабл. Имеет защиту от дебила и 1 спец слово. По своей сути является сбором кода из прочих модулей, поэтому без доп коментов.
    public static double GetNumberDouble(double[] size, String stop_flag, String promt){
        String prestroka = DataGet(promt);

                //  Заменяем , на . т.к. джава не понимает запятые в числах
        String stroka = prestroka.replace(',', '.');
            while (!FilterDouble(stroka, size)) {
                if (SpecWord(stroka, stop_flag)) {
                    break;
                }
                if ((size[0] == -1 && size[1] == -1)){
                    stroka = DataGet("Ошибка! Введите число (или '" + stop_flag + "')");             
                     }
                else{
                    stroka = DataGet("Ошибка! Введите число в лимите между "+ size[0] + " и " + size[1] + " (или слово '" + stop_flag + "')");
                    }
                if (stroka == null) {
                    break;
                }
            }   
        return Double.parseDouble(stroka);
    }
    //  Модуль для генерации рандомных чисел в формате Double. Раньше был внутри автозаполнения массива, сейчас есть понимание, что он может пригодиться ещё где-то. Поэтому вынес его отдельно и добавил размеры как параметр. 
    public static double RandomDouble(double size){
        double db = 0;

        //  Создаём генератор
        Random rnd = new Random();
        //  Рандомим знак (0 это +, 1 это -)
        if (rnd.nextInt(2) ==1){

        //  Рандомим значения до 10 лямов по модулю и добавляем в список
            db = (Double.parseDouble(String.valueOf(rnd.nextDouble(size))));
        }else{

            db = (Double.parseDouble(String.valueOf(-1 * rnd.nextDouble(size))));
                    }
        return db;
    }

    //  Модуль для генерации рандомных чисел в формате Integer.
    public static int RandomInteger(int size){
        int it = 0;

        //  Создаём генератор
        Random rnd = new Random();
        //  Рандомим знак (0 это +, 1 это -)
        if (rnd.nextInt(2) ==1){

        //  Рандомим значения до size по модулю и добавляем в список
            it = (Integer.parseInt(String.valueOf(rnd.nextInt(size))));
        }else{

            it = (Integer.parseInt(String.valueOf(-1 * rnd.nextInt(size))));
                    }
        return it;
    }

    // Возвращает тру, если строка парсится как дабл. "Защита от дебила".
    public static boolean FilterDouble(String text, double[] size) {



        //  Проверка на пустую строку
        if (text == null) return false;

        //  Пробуем запарсить
        try {
            Double.parseDouble(text.trim());

            //  Отделяем границы из массива
            double max = size[1];
            double min = size[0];

            //  Проверяем на доп условие (если обе границы -1, значит их не учитывем)
            if ((min == -1.0 && max == -1.0)){
                return true;
            }

            //  Проверяем, находится ли число в пределах границ
            if (max > Double.parseDouble(text.trim()) && min < Double.parseDouble(text.trim())){
                return true;
            }else{
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Возвращает тру, если строка парсится как инт. "Защита от дебила".
    public static boolean FilterInt(String text, int[] size) {

        //  Проверка на пустую строку
        if (text == null) return false;
        try {
            //  Пробуем запарсить
            Integer.parseInt(text.trim());

            //  Отделяем границы из массива
            int max = size[1];
            int min = size[0];

            //  Проверяем на доп условие (если обе границы -1, значит их не учитывем)
            if ((min == -1 && max == -1)){
                return true;
            }

            //  Проверяем, находится ли число в пределах границ
            if (max > Integer.parseInt(text.trim()) && min < Integer.parseInt(text.trim())){
                return true;

            }else{
                return false;
            }


        } catch (NumberFormatException e) {
            return false;
        }
    }


    // Сравнение слов с защитой от пустой строки, также проверяем без учета регистра.
    public static boolean SpecWord(String text, String word) {
        return text != null && text.trim().equalsIgnoreCase(word);
    }

    // Упрощённый ввод: возвращает нулл при нажатии канцела/закрытии. 
    public static String DataGet(String prompt) {
        String input = JOptionPane.showInputDialog(null, prompt);
        if (input == null) return null;
        return input.trim();
    }

    //  Получение факториала через простой цикл фор, не имеет встроенной защиты от дебила
    public static Double Factorial(Integer number){
        Double result = 1.0;
        for (int i = 1; i<= number; i++){
            result = result * i;
        }
        return result;
    }

    //  Основной модуль, данный модуль является ПРИМЕРОМ использования модулей выше. Если будете использовать этот класс в своих работах - читайте README!!
    public static void main(String[] args) {
        ArrayList<Integer> result = ArrayFormationInteger();
        System.out.println("Получено: " + result);
        int[][] x =(MatrixFormationInteger());
        for (int i = 0; i<=1; i++){
            System.out.println(x[i][1]);
        }
    }
}
