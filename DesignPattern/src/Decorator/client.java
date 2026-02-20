package Decorator;

public class client {
    public static void main(String args[])
    {
        ICharacter mario = new TallMario(new SuperPunchMario( new GunMario( new Mario() ) ) );
        System.out.println( mario.getAbilities() );
    }
}
