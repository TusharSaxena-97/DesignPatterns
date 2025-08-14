package Strategy;

public interface IEatingStrategy {
    public void Eat();
}

class EatFromMouth implements IEatingStrategy
{
    @Override
    public void Eat()
    {
        System.out.println("Eating Via Mouth");
    }
}
