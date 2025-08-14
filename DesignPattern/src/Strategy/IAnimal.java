package Strategy;

public abstract class IAnimal {
    IWalkingStrategy walkingStrategy;
    IEatingStrategy eatingStrategy;
    IBreathingStrategy breathingStrategy;
}

class Animal extends IAnimal {
    Animal(IWalkingStrategy w , IEatingStrategy e , IBreathingStrategy b)
    {
        this.walkingStrategy = w;
        this.eatingStrategy  = e;
        this.breathingStrategy = b;
    }
}


