package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoStoreSeller {
    private static final String NUM_ERROR_MSG = "[ERROR] 숫자만 입력해주세요";
    private static final String PURCHASE_PRICE_MSG = "구매금액을 입력해 주세요.";
    private static final String WINNING_NUM_MSG = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUM_MSG = "보너스 번호를 입력해 주세요.";
    private static final String SEPARATOR = ",";

    public int receivePurchasePrice() {
        System.out.println(PURCHASE_PRICE_MSG);
        String userInput = receiveUserInput();
        validatePrice(userInput);

        return Integer.parseInt(userInput);
    }

    private void validatePrice(String input) {
        if (!input.matches("^[0-9]*$")) {
            System.out.println(NUM_ERROR_MSG);
            throw new IllegalArgumentException();
        }
    }

    private String receiveUserInput() {
        return Console.readLine();
    }
}
