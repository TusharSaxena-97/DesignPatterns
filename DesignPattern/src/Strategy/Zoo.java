package Strategy;

public class Zoo {
    public static void main(String args[])
    {
        IAnimal dog = new Animal( new WalkViaLegs() , new EatFromMouth() , new BreatheViaLungs());
        IAnimal fish = new Animal( new Swim() , new EatFromMouth() , new BreatheViaGills() );
        IAnimal caterpillar= new Animal( new WalkViaMultiLegs() , new EatFromMouth() , new BreatheViaSkin());

        System.out.println(" Dog ");
        dog.breathingStrategy.Breathe();
        dog.walkingStrategy.Walk();
        dog.eatingStrategy.Eat();

        System.out.println(" Fish ");
        fish.eatingStrategy.Eat();
        fish.walkingStrategy.Walk();
        fish.breathingStrategy.Breathe();

        System.out.println(" caterpillar ");
        caterpillar.breathingStrategy.Breathe();
        caterpillar.eatingStrategy.Eat();
        caterpillar.walkingStrategy.Walk();
    }
}
