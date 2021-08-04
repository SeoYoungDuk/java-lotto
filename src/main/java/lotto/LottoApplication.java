package lotto;

import lotto.domain.CollectionOflLottoNumbers;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoResult;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.*;

public class LottoApplication {
    public static void main(String args[]) {
        int purchaseAmount = InputView.getPurchaseAmount();

        LottoMachine lottoMachine = new LottoMachine(purchaseAmount);

        int lottoCount = lottoMachine.getPurchaseLottoCount();
        ResultView.countOfLotto(lottoCount);

        Set<LottoNumbers> totalLottoNumbers = new HashSet<>();

        for (int i = 0; i < lottoMachine.getPurchaseLottoCount(); i++) {
            LottoNumbers generateLottoNumbers = lottoMachine.generateLottoNumber();
            ResultView.printLottoNumber(generateLottoNumbers);
            totalLottoNumbers.add(generateLottoNumbers);
        }

        CollectionOflLottoNumbers collectionOflLottoNumbers = CollectionOflLottoNumbers.of(totalLottoNumbers);

        LottoNumbers winningLottoNumbers = LottoNumbers.of(InputView.getWinningNumber());

        LottoResult lottoResult = LottoResult.of(collectionOflLottoNumbers, winningLottoNumbers);

        ResultView.printWinningStatistics(lottoResult);
        ResultView.printProfitRate(lottoResult.calculateProfitRate(purchaseAmount));

    }
}
