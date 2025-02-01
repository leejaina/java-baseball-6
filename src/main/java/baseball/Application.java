package baseball;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        // 프로그램 시작 메시지
        System.out.println("숫자 야구 게임을 시작합니다.");

        // 게임 시작
        startGame();
    }

    private static void startGame() {
        // 컴퓨터의 숫자 뽑기 (정답)
        List<Integer> random_Num = new ArrayList<>();
        Random rand = new Random();

        // 숫자 조건
        while (random_Num.size() < 3) {
            int num = rand.nextInt(9) + 1;
            if (!random_Num.contains(num)) random_Num.add(num);
        }

        // 게임 시작
        boolean gameEnded = game(random_Num);

        // 게임 종료, 새로 시작할지 종료할지
        if (gameEnded) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요: ");
            String gameMenu = scanner.next();

            if (gameMenu.equals("1")) {
                startGame();  // 새 게임 시작
            } else if (gameMenu.equals("2")) {
                System.out.println("게임 종료");
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private static boolean game(List<Integer> answer) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("숫자를 입력해주세요: ");
            String input = scanner.next();  //플레이어 입력 받기

            //입력 조건
            if (input.length() != 3 || !input.matches("[1-9]{3}") ||
                    input.charAt(0) == input.charAt(1) ||
                    input.charAt(1) == input.charAt(2) ||
                    input.charAt(0) == input.charAt(2)) {
                System.out.println("잘못된 입력입니다. 숫자를 다시 입력하세요.");
                continue;  // 잘못된거면 다시 입력 받기
            }

            // 입력된 숫자를 리스트로 변환
            List<Integer> player_Num = new ArrayList<>();
            for (char c : input.toCharArray()) {
                player_Num.add(Character.getNumericValue(c));
            }

            // 스트라이크, 볼, 낫싱 계산
            int strike = 0, ball = 0;
            for (int i = 0; i < 3; i++) {
                if (player_Num.get(i).equals(answer.get(i))) {
                    strike++;  // 자리와 숫자가 맞으면 스트라이크
                } else if (answer.contains(player_Num.get(i))) {
                    ball++;    // 자리만 틀리고 숫자가 맞으면 볼
                }
            }

            // 결과 출력
            if (strike == 0 && ball == 0) {
                System.out.println("낫싱");
            } else if (strike == 3) {
                System.out.println("3스트라이크!");
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                return true;  // 게임 끝
            } else {
                System.out.println(strike + " 스트라이크, " + ball + " 볼");
            }
        }
    }
}