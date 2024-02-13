import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;

public final class GameUtils {

    private static final String REGEX_ALPHABET = "^[A-Za-z]{4,12}$";
    //REGEX_ALPHABET：文字比較のために定数を定義する
    //^：文字列の先頭を示す
    //[A-Za-z]：アルファベットの大文字と小文字
    //{4,12}：４文字以上１２文字以下
    //$：文字列の末尾示す

    private GameUtils() {} //クラスのインスタンス化を禁止する

    public static String getInputString() {
        String inputString = null;
        /*
　　　　　<初期化する理由>
        1, 例外が発生した場合に備えるため（ユーザーがプログラムを中断した場合や、ファイルの読み取り中にファイルが削除された場合など）
        2, 未入力に備えるため
        3, 後で参照される可能性があるため
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputString = br.readLine();
        } catch (IOException e) {
        //e：catch ブロック内で発生した IOException オブジェクトが代入される
            System.out.println("Input error...Please retype!");
            getInputString(); //例外が発生した場合にループを使用せずメソッドを実行する
        }
        return inputString;
    }

    public static int getInputInt() {
        int inputInt = 0;
        try {
            inputInt = Integer.parseInt(getInputString());
        } catch (NumberFormatException e) {
        //NumberFormatException：文字列から数値への変換が失敗したときにスローされる例外（例えば、数字以外の文字が含まれるなど）
            System.out.println("Please enter an integer!");
            getInputInt();
        }
        return inputInt;
    }

    public static int getRandomInt(int maxValue) {
        Random random = new Random(); //Randomインスタンスを生成することで様々な乱数を生成することができる
        return random.nextInt(maxValue);
        //nextInt()：整数の範囲内でランダムな値を生成する
        //maxValue：渡された値未満の整数
    }

    public static boolean checkPattern(String targetStr) {
        if(targetStr == null || targetStr.isEmpty()) {
            return false;
        }
        return Pattern.matches(REGEX_ALPHABET, targetStr);
        //Pattern.matches()：指定された正規表現パターンに対して文字列がマッチするかどうかを判定するために使用される（第1引数には正規表現パターンを、第2引数には対象の文字列を指定する）
    }
}
