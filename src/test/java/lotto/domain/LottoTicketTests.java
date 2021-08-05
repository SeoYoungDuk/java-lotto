package lotto.domain;

import lotto.exception.InvalidLottoNumberCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoTicketTests {

    @DisplayName("로또 번호 담는 일급 객체 생성 테스트")
    @Test
    void createLottoNumbersTest() {

        LottoTicket expectedLottoTicket = LottoTicket.of(new TreeSet<>(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        LottoTicket actualLottoTicket = LottoTicket.of(new TreeSet<>(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket);
    }

    @DisplayName("로또 번호들의 갯수를 10개로 생성 시킬 때 에러 처리 테스트")
    @Test
    void invalidCountLottoNumberCreateTest() {

        assertThatExceptionOfType(InvalidLottoNumberCountException.class)
                .isThrownBy(() -> {
                    LottoTicket lottoTicket = LottoTicket.of(new TreeSet<>(Arrays.asList(
                            LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                            LottoNumber.of(6), LottoNumber.of(7), LottoNumber.of(8), LottoNumber.of(9), LottoNumber.of(10))));
                }).withMessageMatching("로또 숫자는 반드시 6개 여야 합니다.");
    }

    @DisplayName("중복의 경우 5개만 생성 되므로, 그에 관한 에러 처리 테스트")
    @Test
    void duplicatedCountLottoNumberCreateTest() {
        assertThatExceptionOfType(InvalidLottoNumberCountException.class)
                .isThrownBy(() -> {
                    LottoTicket lottoTicket = LottoTicket.of(new TreeSet<>(Arrays.asList(
                            LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(5))));
                }).withMessageMatching("로또 숫자는 반드시 6개 여야 합니다.");
    }

    @DisplayName("일치하는 갯수를 잘 가져오는지 테스트")
    @Test
    void getMatchCountTest() {
        LottoTicket lottoTicket = LottoTicket.of(new TreeSet<>(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));

        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of("1, 2,3, 4 ,5 6");

        assertThat(lottoTicket.getMatchCount(winningLottoTicket)).isEqualTo(6);
    }
}
