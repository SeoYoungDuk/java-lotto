package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    OUT_OF_RANK(0, 0);

    private int countOfMatch;
    private int price;

    LottoRank(int countOfMatch, int price) {
        this.countOfMatch = countOfMatch;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static LottoRank valueOf(int countOfMatch, boolean bonusMatch) {
        if (bonusMatch && countOfMatch == SECOND.countOfMatch) {
            return SECOND;
        }
        if (!bonusMatch && countOfMatch == THIRD.countOfMatch) {
            return THIRD;
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRankEnum -> lottoRankEnum.countOfMatch == countOfMatch)
                .findFirst()
                .orElse(OUT_OF_RANK);

    }
}
