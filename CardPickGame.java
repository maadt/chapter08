class CardPickGame {
    private int maxBetCoin = 100;//最大ベット枚数
    private int deskSetCount = 2;//カードセット数
    private int possessionCoin;//所持コイン数
    public int A;//一枚目のカード
    public int B;//二枚目のカード
    public int C;//AとBの合計値

    public CardPickGame(int possessionCoin) {
        this.possessionCoin = possessionCoin;
    }

    public void execute() {
        this.getCard();
    }

    private int getCard() {
        A = GameUtils.getRandomInt(10) + 1;
        B = GameUtils.getRandomInt(10) + 1;
        C = 0;
        if (A == B) {
            getCard();
        } else {
            C = A + B;
            System.out.print("Cards drawn are" + " " + A + " ");
            System.out.print("and" + " " + B + ", total is" + " " + C);
            System.out.println();
        }
        return C;
    }

}
