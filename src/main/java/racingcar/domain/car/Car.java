package racingcar.domain.car;

import java.util.Objects;

import racingcar.domain.movement.MovementStrategy;

public class Car implements Comparable<Car> {

    private final Name name;
    private final Position position;

    public Car(String name) {
        this(new Name(name));
    }

    public Car(Name name) {
        this(name, new Position());
    }

    public Car(Name name, Position position) {
        validate(name, position);
        this.name = name;
        this.position = position;
    }

    private void validate(Name name, Position position) {
        if (name == null) {
            throw new IllegalArgumentException("자동차 이름은 null이 될 수 없습니다.");
        }
        if (position == null) {
            throw new IllegalArgumentException("자동차 위치는 null이 될 수 없습니다.");
        }
    }

    public boolean isSamePosition(Car otherCar) {
        return position.equals(otherCar.getPosition());
    }

    public int selectWinner(Car otherCar) {
        return compareTo(otherCar);
    }

    public void move(MovementStrategy strategy) {
        int distance = strategy.move();
        this.position.move(distance);
    }

    @Override
    public int compareTo(Car o) {
        return this.position.compareTo(o.getPosition());
    }

    public String getName() {
        return name.getName();
    }

    public Position getPosition() {
        return position;
    }

    public int getPositionValue() {
        return position.getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name)
            && Objects.equals(position, car.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
