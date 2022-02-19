package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.ConsoleTest;
import racingcar.config.ViewConfig;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.CarsDto;

class OutputViewTest extends ConsoleTest {

    private OutputView outputView;
    private static final String SAMPLE_WINNERS_MESSAGE = "pobi, seung, char가 최종 우승했습니다.\n";
    private static final String SAMPLE_CAR_POSITION = "pobi : -\nseung : -\nchar : -\n\n";

    @DisplayName("우승자 출력이 정상적인지 확인")
    @Test
    void printWinners() {
        changeOutput();
        outputView = ViewConfig.getOutputView();
        List<CarDto> testWinners =
            Stream.of(new Car("pobi"), new Car("seung"), new Car("char"))
                .map(CarDto::new)
                .collect(Collectors.toList());
        outputView.printWinners(testWinners);
        assertThat(outputStream.toString()).hasToString(SAMPLE_WINNERS_MESSAGE);
    }

    @DisplayName("자동차들의 위치가 정상적으로 출력되는지 확인")
    @Test
    void printCarsPosition() {
        changeOutput();
        outputView = ViewConfig.getOutputView();
        String[] input = {"pobi", "seung", "char"};
        Cars cars = Cars.create(input);
        cars.move(() -> 1);
        CarsDto carsDto = new CarsDto(cars);
        outputView.printCarsPosition(carsDto);
        assertThat(outputStream.toString()).hasToString(SAMPLE_CAR_POSITION);
    }
}
