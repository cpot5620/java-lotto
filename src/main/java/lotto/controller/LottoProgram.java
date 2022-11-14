package lotto.controller;

import lotto.model.WinningType;
import lotto.model.LottoStore;
import lotto.model.LottoWinningAnalyzer;
import lotto.model.WinningLotto;
import lotto.model.Lotto;

import java.util.EnumMap;
import java.util.List;

import static lotto.view.LottoSeller.*;

public class LottoProgram {
    private static final int INIT_WINNING_COUNT = 0;

    private List<Lotto> lottoTickets;
    private WinningLotto winningLotto;
    private EnumMap<WinningType, Integer> winningResult;

    public LottoProgram() {
        winningResult = new EnumMap<>(WinningType.class);

        for (WinningType type : WinningType.values()) {
            winningResult.put(type, INIT_WINNING_COUNT);
        }
    }

    public void run() {
        List<Integer> numbers;
        int bonusNumber, purchasePrice;

        purchasePrice = receivePurchasePrice();
        buyLotto(purchasePrice);
        numbers = receiveWinningNumbers();
        bonusNumber = receiveBonusNumber();

        initializeWinningLotto(numbers, bonusNumber);
        initializeWinningResult();

        printWinningResult(purchasePrice);
    }

    private void printWinningResult(int purchasePrice) {
        LottoWinningAnalyzer analyzer = new LottoWinningAnalyzer(winningResult);

        analyzer.printWinningResult();

        analyzer.printProfit(purchasePrice);
    }

    private void buyLotto(int price) {
        LottoStore lottoStore = new LottoStore();

        this.lottoTickets = lottoStore.buyLottoNumber(price);

        printLottoNumbers(lottoTickets);
    }

    private void initializeWinningLotto(List<Integer> numbers, int bonusNumber) {
        this.winningLotto = new WinningLotto(new Lotto(numbers), bonusNumber);
    }

    private void initializeWinningResult() {
        for (int i = 0; i < lottoTickets.size(); i++) {
            Lotto lottoTicket = lottoTickets.get(i);
            int count = winningLotto.countWinningNumber(lottoTicket);

            if (count >= WinningType.THREE.getNumberOfMatching())
                updateWinningResult(count);
        }
    }

    private void updateWinningResult(int count) {
        WinningType type = winningResult.keySet().stream().
                filter(winningType -> winningType.getNumberOfMatching() == count)
                .findAny()
                .get();
        int currentCount = winningResult.get(type);

        winningResult.put(type, currentCount + 1);
    }
}
