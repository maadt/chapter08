import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class CardPickGame {
    private int maxBetCoin = 100;//最大ベット枚数
    private int deckSetCount = 2;//カードセット数
    private int possessionCoin;//所持コイン数

    public CardPickGame(int possessionCoin) {
        this.possessionCoin = possessionCoin;
    }

    public int execute() {

        while (true) {
            if (possessionCoin <= 0) {
                return possessionCoin;
            }
            while (true) {
                System.out.println("You have " + possessionCoin + " Coin, Start the game? y / n");
                String startValue = GameUtils.getInputString();
                if (startValue.equals("y")) {
                // equals()：文字列の比較演算子
                    break;
                } else if (startValue.equals("n")) {
                    return this.possessionCoin;
                } else {
                    System.out.println("Please enter y or n.");
                }
            }

            int ableBetCoin = Math.min(this.maxBetCoin, this.possessionCoin);
            // Math.min()：与えられた引数のうち小さい方の値を返す
            System.out.println("Please bet Coin 1 ~ " + ableBetCoin);

            int userBetCoin = 0;
            while (true) {
                userBetCoin = GameUtils.getInputInt();
                if (userBetCoin > 0 && userBetCoin <= ableBetCoin) {
                    break;
                }
            }
            this.possessionCoin -= userBetCoin;

            int userCard = this.getCard();
            boolean isWinner = this.judgeCard(userCard);
            int getCoin = 0;
            if (isWinner) { //trueの場合のみ実行される
                getCoin = userBetCoin * 2;
                System.out.println("You Win! Get " + getCoin + "Coin!");
                this.possessionCoin += getCoin;
                HighAndLowGame highAndLowGame = new HighAndLowGame(getCoin, this.deckSetCount);
                getCoin = highAndLowGame.execute();
                this.possessionCoin += getCoin;
            }
            if (getCoin == 0) {
                System.out.println("You lose");
            }
            if (getCoin >= 1) {
                System.out.println("You got " + getCoin + "Coin !!");
            }
        }
    }

    private int getCard() {
        List<List<Integer>> setDeck = new ArrayList<List<Integer>>();
        // List<List<Integer>>：Integer型のList型オブジェクトを格納する
        List<Integer> onePair = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // Arrays.asList()：与えられた要素を持つ固定サイズのリストを返す

        for (int i = 0; i < this.deckSetCount; i++) {
            setDeck.add(i, onePair);
            // setDeck：配列
            // add()：要素の追加
            // i, onePair：配列のキー, 追加する要素
        }

        int randNumA1;
        int randNumA2;
        int randNumB1;
        int randNumB2;
        while (true) { // 無限ループ
            randNumA1 = GameUtils.getRandomInt(2);
            randNumA2 = GameUtils.getRandomInt(10);
            randNumB1 = GameUtils.getRandomInt(2);
            randNumB2 = GameUtils.getRandomInt(10);
            if (!(randNumA1 == randNumB1 && randNumA2 == randNumB2)) {
                break; // 無限ループを停止させる
            }
            // while文内のif文の結果自体、ループ全体の制御フローに影響しない
        }

        int cardA = setDeck.get(randNumA1).get(randNumA2);
        // setDeck：リスト
        // randNumA1：インデックスのキー番号
        // randNumA2：サブリストのキー番号
        int cardB = setDeck.get(randNumB1).get(randNumB2);
        int userNumSum = cardA + cardB;
        System.out.println("Cards drawn are " + cardA + " and " + cardB + ", total is " + userNumSum + ".");
        return userNumSum;
    }

    private boolean judgeCard(int getCardResult) {
        return (getCardResult >= 11) ? true : false;
        // 三項演算子：条件 ? trueの時の処理 : falseの時の処理;
    }
}
