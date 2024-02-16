import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class HighAndLowGame {
    private int earnedCoinCount;//獲得コイン枚数
    private int maxWinCoin = 10000;//最大獲得コイン数
    private int deckSetCount;//カードセット数

    public HighAndLowGame(int earnedCoinCount, int deckSetCount) {
        this.earnedCoinCount = earnedCoinCount;
        this.deckSetCount = deckSetCount;
        this.execute();
    }

    public int execute() {
        while (true) {
            if (earnedCoinCount > maxWinCoin) {
            return earnedCoinCount;
            }
            while (true) {
                System.out.println("Your winCoin is " + earnedCoinCount);
                System.out.println("Playing High And Low ? y / n");
                String startValue = GameUtils.getInputString();
                if (startValue.equals("y")) {
                    break;
                } else if (startValue.equals("n")) {
                    return this.earnedCoinCount;
                } else {
                    System.out.println("Input error...Please retype!");
                }
            }

            boolean choice;
            while (true) {
                System.out.println("High or Low ? h / l");
                String startValue = GameUtils.getInputString();
                if (startValue.equals("h")) {
                    choice = true;
                    break;
                } else if (startValue.equals("l")) {
                    choice = false;
                    break;
                } else {
                    System.out.println("Input error...Please retype!");
                }
            }

            List<Integer> cardList = new ArrayList<Integer>();
            for (int i = 0; i < deckSetCount * 2; i++) {
                getCard(cardList);
            }
            boolean result = judgeCard(cardList, choice);

            if (result == true) {
                earnedCoinCount *= 2;
            } else {
                earnedCoinCount = 0;
            }

            if (earnedCoinCount == 0) {
                return 0;
            } else {
                System.out.println("You got " + earnedCoinCount + " Coin !!");
                execute();
            }
        }
    }

    private List<Integer> getCard(List<Integer> cardList) {
        List<List<Integer>> setDeck = new ArrayList<List<Integer>>();
        List<Integer> onePair = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (int i = 0; i < this.deckSetCount; i++) {
            setDeck.add(i, onePair);
        }

        int cardA;
        while (true) {
            int randNumA1 = GameUtils.getRandomInt(deckSetCount);
            int randNumA2 = GameUtils.getRandomInt(onePair.size());
            cardA = setDeck.get(randNumA1).get(randNumA2);

            int count = 0;
            for (int value : cardList) {
                if (cardA == value) {
                    count++;
                }
            }
            if (count < this.deckSetCount) {
                break;
            }
        }

        cardList.add(cardA);
        int lastIdx = cardList.size() - 1;
        int showValue = cardList.get(lastIdx);
        System.out.println("pick card --" + showValue + "--");
        return cardList;
    }

    private boolean judgeCard(List<Integer> cardList, boolean pickChoice) {
        int num = cardList.size();
        int lastCard = cardList.get(num - 1);
        int penultimateCard = cardList.get(num - 2);
        // penultimate：最後から二番目

        if (lastCard == penultimateCard) {
            return false;
        }
        boolean flg = (lastCard > penultimateCard) ? true : false;
        if (pickChoice == flg) {
            return true;
        }
        return false;
    }
}
