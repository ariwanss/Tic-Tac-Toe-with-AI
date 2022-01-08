package tictactoe;

public class PlayerFactory {

    public static Player createPlayer(String kind, char sign) {
        if ("user".equals(kind)) {
            return new HumanPlayer(sign);
        } else if ("easy".equals(kind)){
            return new EasyComputerPlayer(sign);
        } else if ("medium".equals(kind)) {
            return new MediumComputerPlayer(sign);
        } else {
            return new HardComputerPlayer(sign);
        }
    }
}
