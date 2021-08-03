package lotto.domain;

import lotto.LottoRankEnum;
import lotto.view.InputView;
import lotto.view.ResultView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTests {

    @DisplayName("로또 결과를 잘 가져오는지 테스트")
    @Test
    void getLottoResultTest(){
        LottoNumbers lottoNumbers = LottoNumbers.of(new HashSet<>(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        LottoNumbers winningLottoNumbers = LottoNumbers.of(new HashSet<>(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        Set<LottoNumbers> totalLottoNumbers = new HashSet<>();

        totalLottoNumbers.add(lottoNumbers);

        LottoResult lottoResult = LottoResult.of(totalLottoNumbers, winningLottoNumbers);

        assertThat(lottoResult.getLottoResult().get(LottoRankEnum.ALL_MATCH)).isEqualTo(1);
    }
}
