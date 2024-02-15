public class Playing {
    public static void main(String[] args) {
        String username = "";
        int possessionCoin = 1000;

        System.out.println("Welcome !");
        System.out.println("Enter your username");

        while (true) {
            username = GameUtils.getInputString();
            if (GameUtils.checkPattern(username)) {
                break;
            } else {
                System.out.println("Does not match condition of the username");
            }
        }

        System.out.println("Hello " + username);

        CardPickGame cardPickGame = new CardPickGame(possessionCoin);
        possessionCoin = cardPickGame.execute();

        System.out.println(username + " Possession : " + possessionCoin + " Coin");
    }
}
