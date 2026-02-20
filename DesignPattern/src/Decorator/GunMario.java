package Decorator;

public class GunMario extends ICharacterDecorator{

    GunMario( ICharacter character )
    {
        this.character = character;
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " + Gun";
    }
}
