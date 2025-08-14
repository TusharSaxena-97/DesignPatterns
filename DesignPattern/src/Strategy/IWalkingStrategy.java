package Strategy;

public interface IWalkingStrategy {
    public void Walk();
}

class WalkViaLegs implements IWalkingStrategy
{
    @Override
    public void Walk() {
        System.out.println("Walking via 2 legs");
    }
}

class WalkViaMultiLegs implements  IWalkingStrategy
{
    @Override
    public void Walk()
    {
        System.out.println("Walking via multiple legs");
    }
}

class Swim implements  IWalkingStrategy
{
    @Override
    public void Walk()
    {
        System.out.println("Walking via Swimming in water");
    }
}
