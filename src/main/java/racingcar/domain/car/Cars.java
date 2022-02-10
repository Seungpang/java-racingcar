package racingcar.domain.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import racingcar.exception.CarNamesNotOnlyCommaException;

public class Cars {

	private static final int MIN_LENGTH = 0;
	private static final String DELIMITER = ",";

	private List<Car> cars;

	public Cars(String names) {
		this.cars = createCarsByName(names);
	}

	private List<Car> createCarsByName(String names) {
		String[] carNames = splitByComma(names);
		return createCarList(carNames);

	}

	private String[] splitByComma(String names) {
		String[] carNames = names.split(DELIMITER);
		if (carNames.length == MIN_LENGTH) {
			throw new CarNamesNotOnlyCommaException("자동차 이름에 ,만 사용할 수 없습니다.");
		}
		return carNames;
	}

	private List<Car> createCarList(String[] carNames) {
		List<Car> carList = new ArrayList<>();
		for (String carName : carNames) {
			carList.add(new Car(carName));
		}
		return carList;
	}

	public List<Car> getCarList() {
		return Collections.unmodifiableList(cars);
	}
}